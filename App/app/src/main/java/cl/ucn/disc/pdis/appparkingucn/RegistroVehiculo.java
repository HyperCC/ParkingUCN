/*
 * Copyright (c) 2020. Castillo - Condorcet - Pizarro Engineering Students.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cl.ucn.disc.pdis.appparkingucn;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cl.ucn.disc.pdis.appparkingucn.fragment.*;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.*;


/**RegistroVehiculo class.
 *
 * The activity of registrar fragments.
 */
public class RegistroVehiculo extends AppCompatActivity {

    // Variable declarations.
    private Fragment fragmentRegistrarInicio;
    private Fragment fragmentRegistrarConfirmacion;

    private Persona persona;
    private Vehiculo vehiculo;
    private String tipoRegistro;

    private String fechaRegistro;
    private String horaRegistro;

    /**onCreate Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehiculo);

        // Variable initialization.
        fragmentRegistrarInicio = new RegistrarInicio();

        // Change to RegistrarInicio fragment.
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, fragmentRegistrarInicio).commit();
    }

    /**Method limpiarPatente.
     *
     * @param dato is a cleaner for patente.
     */
    public void limpiarPatente(String dato) {

        //  Separate essential characters from trash.
        String[] parts = dato.split("[-+*/=.<>@#$%^&)(]");
        String datoTemp = "";

        // Add the pure data to String.
        for (String split : parts) {

            datoTemp = datoTemp + split;
        }

        // Set all the char to Upper.
        datoTemp = datoTemp.toUpperCase();

        // Data inserted must be 6 characters.
        if (datoTemp.length() == 6) {

            // reestructurarPatente call sending pure data of patente.
            reestructurarPatente(datoTemp);
        } else {

            // Notification if patente is not correct format.
            Toast.makeText(this, "La patente debe ser unicamente de 6 digitos.", Toast.LENGTH_SHORT).show();
        }
    }

    /**Method reestructurarPatente.
     *
     * @param datoTemp is restructured to the form: XX-XX-XX
     */
    public void reestructurarPatente(String datoTemp) {

        /* Starting variables.
         * That is for infinite data length from String.
         */
        int len = datoTemp.length();
        int verificadorPunto = 2;
        int multiploPunto = 1;

        // If the temporary data length is longer than...
        if(len>(verificadorPunto*multiploPunto)){

            // 'count' is a digit counter.
            int count = 0;

            // 'i' will travel from last to first character of a String.
            int i = len;

            // 'updateArr' is an array for character insertion.
            char[] updatedArr;

            // Digit travel initialization.
            while(i>0){

                // If data length is longer (In that case: minimum 3 digit).
                if(len > (verificadorPunto*multiploPunto)){

                    // Prepare updateArr for receive a new '-'
                    updatedArr = new char[len + 1];

                    /* 'count' set 3th digit to insert a '-' in that position.
                     * 'i' must be > 0 to avoid overflow.
                     */
                    if(count == 2 && i > 0){

                        // Insert first char until char in 'i' in the char array.
                        datoTemp.getChars(0, i, updatedArr, 0);

                        // Insert in that position of the array this char.
                        updatedArr[i] = '-';

                        // Insert the rest of char after '.' in the array.
                        datoTemp.getChars(i, len, updatedArr, i + 1);

                        datoTemp = "";

                        // Insert all the char to String.
                        for (char split: updatedArr) {

                            datoTemp = datoTemp+split;
                        }

                        // Increase multiplier.
                        multiploPunto+=1;

                        // Get the new length.
                        len = datoTemp.length();

                        // Reset count for a new recheck.
                        count = 0;
                    }
                }

                // Check the next digit.
                count++;

                // Travel until the first char.
                i--;
            }
        }

        // consultarRegistro call sending structured patente.
        consultarPatente(datoTemp);
    }

    /**Method consultarPatente.
     *
     * @param datoTemp is a structured patente.
     */
    public void consultarPatente(String datoTemp){

        // Starting the Communicator.
        Communicator communicator = new Communicator();

        // Get a Vehiculo with communicator.obtenerVehiculo call.
        vehiculo = communicator.obtenerVehiculo(datoTemp);

        // If a 'vehiculo' exist...
        if(vehiculo != null){

            // Start consultarRut call sending the vehiculo's responsable.
            consultarRut(vehiculo.responsable);
        }else{

            // Notification if patente is not found.
            Toast.makeText(this, "Patente: "+datoTemp+" no encontrada.", Toast.LENGTH_SHORT).show();
        }
    }

    /**Method consultarRut.
     *
     * @param rut is the responsable's rut
     */
    public void consultarRut(String rut){

        // Starting the Communicator.
        Communicator communicator = new Communicator();

        // Get a Persona with communicator.obtenerPersona call.
        persona = communicator.obtenerPersona(rut);

        // If a 'persona' exist...
        if(persona != null){

            // Declaring fragment.
            fragmentRegistrarConfirmacion = new RegistrarConfirmacion();

            // Start generarFecha call.
            generarFecha();

            // Change to RegistrarConfirmacion fragment.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedorFragments, fragmentRegistrarConfirmacion).commit();
        }else{

            // Notification if persona is not found.
            Toast.makeText(this, "Persona con RUT: "+rut+" no encontrada.", Toast.LENGTH_SHORT).show();
        }
    }

    /**Method generarFecha.
     *
     * Set the System date.
     */
    private void generarFecha(){

        // Intializating and declaring the calendar.
        Calendar calendar = Calendar.getInstance();

        // Initializating and declaring the simple date format to date.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        // Insert that simple date format to a String.
        this.fechaRegistro = simpleDateFormat.format(calendar.getTime());

        // Initializating and declaring the simple date format to timeZone.
        simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

        // Insert that simple date format to a String.
        this.horaRegistro = simpleDateFormat.format(calendar.getTime());
    }

    /**Method generarRegistro.
     *
     * Create a Registro instanced.
     */
    public void generarRegistro(){

        // Starting the Communicator.
        Communicator communicator = new Communicator();

        // Initializating a registro.
        Registro registro = new Registro();

        // Fill all the attributes of registro.
        registro.patente = vehiculo.patente;
        registro.responsable = persona.rut;
        registro.fecha = fechaRegistro;
        registro.hora = horaRegistro;
        registro.estado = transformarTipoRegistro();

        // Create a temporary Registro to check success inserting to database.
        Registro registroVerificar = communicator.crearRegistro(registro);

        // If insertion is successfull...
        if(registroVerificar != null){

            // Notification if insertion is successfull.
            Toast.makeText(this, "El registro fue ingresado con exito.", Toast.LENGTH_SHORT).show();

            // Go back to RegistrarInicio fragment.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedorFragments, fragmentRegistrarInicio).commit();

        }else{

            // Go back to RegistrarInicio fragment.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedorFragments, fragmentRegistrarInicio).commit();

            // Notification if insertion is missed.
            Toast.makeText(this, "El registro no pudo ser ingresado.", Toast.LENGTH_SHORT).show();
        }
    }

    /**Method transformarTipoRegistro.
     *
     * @return Estado that can be "SALIDA" or "ENTRADA".
     */
    private Estado transformarTipoRegistro(){

        // Conditional for output.
        if(this.tipoRegistro == "Entrada"){

            return Estado.ENTRADA;
        }else{

            return Estado.SALIDA;
        }
    }

    /**Method setTipoRegistro.
     *
     * @param tipoRegistro can be "Entrada" or "Salida".
     */
    public void setTipoRegistro(String tipoRegistro){

        this.tipoRegistro = tipoRegistro;
    }

    /**Method getTipoRegistro.
     *
     * @return tipoRegistro.
     */
    public String getTipoRegistro(){

        return this.tipoRegistro;
    }

    /**Method getPersona.
     *
     * @return persona.
     */
    public Persona getPersona(){

        return this.persona;
    }

    /**Method getVehiculo.
     *
     * @return vehiculo.
     */
    public Vehiculo getVehiculo(){

        return this.vehiculo;
    }

    /**Method getFechaRegistro.
     *
     * @return fechaRegistro.
     */
    public String getFechaRegistro(){

        return this.fechaRegistro;
    }

    /**Method getHoraRegistro.
     *
     * @return horaRegistro.
     */
    public String getHoraRegistro(){

        return this.horaRegistro;
    }
}