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


public class Buscar extends AppCompatActivity {

    private Fragment fragmentInicioBuscar;
    private Fragment fragmentPersonaResultado;

    private Persona persona;
    private Registro registro;
    private String tipoDato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        fragmentInicioBuscar = new BuscarInicio();
        fragmentPersonaResultado = new BuscarResultado();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, fragmentInicioBuscar).commit();

    }

    public void reestructurarRut(String dato){

        // Sample 5.808.054-3
        String[] parts = dato.split("[-+*/=.<>]");
        String datoTemp = "";

        for (String split: parts) {

            datoTemp = datoTemp+split;
        }

        datoTemp = datoTemp.toUpperCase();

        int len = datoTemp.length();

        if(len > 1){

            char[] updatedArr = new char[len + 1];
            datoTemp.getChars(0, len-1, updatedArr, 0);
            updatedArr[len-1] = '-';
            datoTemp.getChars(len-1, len, updatedArr, (len-1) + 1);

            datoTemp = "";

            for (char split: updatedArr) {

                datoTemp = datoTemp+split;
            }

            len = datoTemp.length();
        }

        int verificadorPunto = 4;
        int multiploPunto = 1;

        if(len>1+(verificadorPunto*multiploPunto)){

            int count = 0;
            int i = len;
            char[] updatedArr;

            while(i>0){

                if(len > 1+(verificadorPunto*multiploPunto)){

                    updatedArr = new char[len + 1];

                    if(count == 3 && i >= 2){

                        datoTemp.getChars(0, i-2, updatedArr, 0);
                        updatedArr[i-2] = '.';
                        datoTemp.getChars(i-2, len, updatedArr, (i-2) + 1);

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

        consultarRegistro(datoTemp);
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

        consultarRegistro(datoTemp);
    }

    public void consultarRegistro(String datoTemp){

        Communicator communicator = new Communicator();

        registro = communicator.obtenerUltimoRegistro(datoTemp, this.tipoDato);

        if(registro != null){

            persona = communicator.obtenerPersona(registro.responsable);
            buscarResultado();
        }else{

            Toast.makeText(this, "NO SE HA ENCONTRADO REGISTRO CON DATO INGRESADO", Toast.LENGTH_SHORT).show();
        }
    }

    public Registro getRegistro(){

        return this.registro;
    }

    public Persona getPersona(){

        return this.persona;
    }

    public void buscarResultado() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorFragments, fragmentPersonaResultado).commit();
    }

    public void setTipoDato(String tipoDato){

        this.tipoDato = tipoDato;
    }

    public String getTipoDato(){

        return this.tipoDato;
    }
}