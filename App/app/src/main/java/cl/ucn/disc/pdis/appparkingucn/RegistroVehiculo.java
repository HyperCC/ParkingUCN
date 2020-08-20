package cl.ucn.disc.pdis.appparkingucn;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cl.ucn.disc.pdis.appparkingucn.fragment.*;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.*;



public class RegistroVehiculo extends AppCompatActivity {

    Fragment fragmentRegistrarInicio;
    Fragment fragmentRegistrarConfirmacion;

    private Persona persona;
    private Vehiculo vehiculo;
    private String tipoRegistro;

    private String fechaRegistro;
    private String horaRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehiculo);

        fragmentRegistrarInicio = new RegistrarInicio();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, fragmentRegistrarInicio).commit();
    }

    public void limpiarPatente(String dato) {

        // Sample AA-SS-DD
        String[] parts = dato.split("[-+*/=.<>@#$%^&)(]");
        String datoTemp = "";

        for (String split : parts) {

            datoTemp = datoTemp + split;
        }

        datoTemp = datoTemp.toUpperCase();

        if (datoTemp.length() == 6) {

            reestructurarPatente(datoTemp);
        } else {

            Toast.makeText(this, "La patente debe ser unicamente de 6 digitos.", Toast.LENGTH_SHORT).show();
        }
    }

    public void reestructurarPatente(String datoTemp) {

        int len = datoTemp.length();

        int verificadorPunto = 2;
        int multiploPunto = 1;

        if(len>(verificadorPunto*multiploPunto)){

            int count = 0;
            int i = len;
            char[] updatedArr;

            while(i>0){

                if(len > (verificadorPunto*multiploPunto)){

                    updatedArr = new char[len + 1];

                    if(count == 2 && i > 0){

                        datoTemp.getChars(0, i, updatedArr, 0);
                        updatedArr[i] = '-';
                        datoTemp.getChars(i, len, updatedArr, i + 1);

                        datoTemp = "";

                        for (char split: updatedArr) {

                            datoTemp = datoTemp+split;
                        }

                        multiploPunto+=1;
                        len = datoTemp.length();
                        count = 0;
                    }
                }

                count++;
                i--;
            }
        }

        consultarPatente(datoTemp);
    }

    public void consultarPatente(String datoTemp){


        Communicator communicator = new Communicator();
        vehiculo = communicator.obtenerVehiculo(datoTemp);

        if(vehiculo != null){

            consultarRut(vehiculo.responsable);
        }else{

            Toast.makeText(this, "PATENTE: "+datoTemp+" NO ENCONTRADA", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarRut(String rut){


        Communicator communicator = new Communicator();
        persona = communicator.obtenerPersona(rut);

        if(persona != null){

            fragmentRegistrarConfirmacion = new RegistrarConfirmacion();
            generarFecha();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedorFragments, fragmentRegistrarConfirmacion).addToBackStack(null).commit();
        }else{

            Toast.makeText(this, "PERSONA CON RUT: "+rut+" NO ENCONTRADA", Toast.LENGTH_SHORT).show();
        }
    }

    private void generarFecha(){

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        this.fechaRegistro = simpleDateFormat.format(calendar.getTime());

        simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

        this.horaRegistro = simpleDateFormat.format(calendar.getTime());
    }

    // TODO: REQUIERE CONEXION
    public void generarRegistro(){

        Communicator communicator = new Communicator();

        Registro registro = new Registro();

        registro.patente = vehiculo.patente;
        registro.responsable = persona.rut;
        registro.fecha = fechaRegistro;
        registro.hora = horaRegistro;
        registro.estado = transformarTipoRegistro();

        Registro registroVerificar = communicator.crearRegistro(registro);

        if(registroVerificar != null){

            Toast.makeText(this, "EL REGISTRO FUE INGRESADO CON EXITO", Toast.LENGTH_SHORT).show();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedorFragments, fragmentRegistrarInicio).addToBackStack(null).commit();

        }else{

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedorFragments, fragmentRegistrarInicio).addToBackStack(null).commit();
            Toast.makeText(this, "EL REGISTRO NO PUDO SER INGRESADO", Toast.LENGTH_SHORT).show();
        }
    }

    private Estado transformarTipoRegistro(){

        if(this.tipoRegistro == "Entrada"){

            return Estado.ENTRADA;
        }else{

            return Estado.SALIDA;
        }
    }

    public void setTipoRegistro(String tipoRegistro){

        this.tipoRegistro = tipoRegistro;
    }

    public String getTipoRegistro(){

        return this.tipoRegistro;
    }

    public Persona getPersona(){

        return this.persona;
    }

    public Vehiculo getVehiculo(){

        return this.vehiculo;
    }

    public String getFechaRegistro(){

        return this.fechaRegistro;
    }

    public String getHoraRegistro(){

        return this.horaRegistro;
    }
}