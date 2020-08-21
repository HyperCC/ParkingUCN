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

import cl.ucn.disc.pdis.appparkingucn.fragment.*;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Persona;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Registro;

/**Buscar class.
 *
 * The Activity of buscar fragments.
 */
public class Buscar extends AppCompatActivity {

    // Variable declarations.
    private Fragment fragmentInicioBuscar;
    private Fragment fragmentPersonaResultado;

    private Persona persona;
    private Registro registro;
    private String tipoDato;

    /**onCreate Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        // Variable initialization
        fragmentInicioBuscar = new BuscarInicio();
        fragmentPersonaResultado = new BuscarResultado();

        // Change to InicioBuscar fragment
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, fragmentInicioBuscar).commit();

    }

    /**Method reestructurarRut.
     *
     * @param dato is restructured to the form: xx.xxx.xxx-Y
     */
    public void reestructurarRut(String dato){

        // Separate essential characters from trash.
        String[] parts = dato.split("[-+*/=.<>]");
        String datoTemp = "";

        // Add the pure data to String.
        for (String split: parts) {

            datoTemp = datoTemp+split;
        }

        datoTemp = datoTemp.toUpperCase();

        // Get the length of that String for '-' insertion.
        int len = datoTemp.length();

        // When the length is 2 or more.
        if(len > 1){

            // Prepare a temperary array.
            char[] updatedArr = new char[len + 1];

            // Insert in that array string's char until the last position -1.
            datoTemp.getChars(0, len-1, updatedArr, 0);

            // In the last position -1 insert an '-' in the array.
            updatedArr[len-1] = '-';

            // Then, insert the rest of string's char in the array (digit verifier).
            datoTemp.getChars(len-1, len, updatedArr, (len-1) + 1);

            datoTemp = "";

            // Insert all the char to String.
            for (char split: updatedArr) {

                datoTemp = datoTemp+split;
            }

            // Get the new length of temporary String.
            len = datoTemp.length();
        }

        /* Starting variables.
         * That is for infinite data length from String.
         */
        int verificadorPunto = 4;
        int multiploPunto = 1;

        // If the temporary data length is longer than...
        if(len>1+(verificadorPunto*multiploPunto)){

            // 'count' is a digit counter.
            int count = 0;

            // 'i' will travel from last to first character of a String.
            int i = len;

            // 'updateArr' is an array for character insertion.
            char[] updatedArr;

            // Digit travel initialization.
            while(i>0){

                // If data length is longer (In that case: minimum 6 digit).
                if(len > 1+(verificadorPunto*multiploPunto)){

                    // Prepare updateArr for receive a new '.'
                    updatedArr = new char[len + 1];

                    /* 'count' set 4th digit to insert a '.' in that position.
                     * 'i' must be >=2 to avoid overflow.
                     */
                    if(count == 3 && i >= 2){

                        /* Insert first char until char in i-2 in the char array.
                         * Where 2 are digits ignored (in that case: '-' and digit verifier)
                         */
                        datoTemp.getChars(0, i-2, updatedArr, 0);

                        // Insert in that position of the array this char.
                        updatedArr[i-2] = '.';

                        // Insert the rest of char after '.' in the array.
                        datoTemp.getChars(i-2, len, updatedArr, (i-2) + 1);

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

        // consultarRegistro call sending structured rut.
        consultarRegistro(datoTemp);
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
        consultarRegistro(datoTemp);
    }

    /**Method consultarRegistro.
     *
     * @param datoTemp is a Structured data.
     *
     * datoTemp can be a rut or patente.
     */
    public void consultarRegistro(String datoTemp){

        // Starting the Communicator.
        Communicator communicator = new Communicator();

        // Get a Registro with communicator.obtenerUltimoregistro call.
        registro = communicator.obtenerUltimoRegistro(datoTemp, this.tipoDato);

        // If a 'registro' exist...
        if(registro != null){

            // Get a Persona with communicator.obtenerPersona call.
            persona = communicator.obtenerPersona(registro.responsable);

            // Start buscarResultado call.
            buscarResultado();
        }else{

            // Notification if registro is not found.
            Toast.makeText(this, "No se ha encontrado registro con dato ingresado.", Toast.LENGTH_SHORT).show();
        }
    }

    /**Method getRegistro.
     *
     * @return a Registro.
     */
    public Registro getRegistro(){

        return this.registro;
    }

    /**Method getPersona.
     *
     * @return a Persona.
     */
    public Persona getPersona(){

        return this.persona;
    }

    /**Method buscarResultado.
     *
     * Change to fragment buscarPersonaResultado.
     */
    public void buscarResultado() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorFragments, fragmentPersonaResultado).commit();
    }

    /**Method setTipoDato.
     *
     * @param tipoDato es "responsable" o "patente"
     */
    public void setTipoDato(String tipoDato){

        this.tipoDato = tipoDato;
    }

    /**Method getTipoDato
     *
     * @return the current tipoDato.
     */
    public String getTipoDato(){

        return this.tipoDato;
    }
}