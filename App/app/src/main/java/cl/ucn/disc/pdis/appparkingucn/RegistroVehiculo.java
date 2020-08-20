package cl.ucn.disc.pdis.appparkingucn;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import cl.ucn.disc.pdis.appparkingucn.fragment.*;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Persona;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Vehiculo;

public class RegistroVehiculo extends AppCompatActivity {

    Fragment fragmentInicioRegistro;

    Persona persona;
    Vehiculo vehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehiculo);

        fragmentInicioRegistro = new RegistrarInicio();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, fragmentInicioRegistro).commit();
    }

    public void limpiarPatente(String dato) {

        // Sample AA-SS-DD
        String[] parts = dato.split("[-+*/=.<>@#$%^&)(]");
        String datoTemp = "";

        for (String split : parts) {

            datoTemp = datoTemp + split;
        }

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

            // TODO: Fragment transaction and deploy DATA
            /*
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedorFragments, fragmentInicioRegistro).addToBackStack(null).commit();*/
        }else{

            Toast.makeText(this, "PERSONA CON RUT: "+rut+" NO ENCONTRADA", Toast.LENGTH_SHORT).show();
        }
    }
}