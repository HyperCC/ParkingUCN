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


public class Buscar extends AppCompatActivity {

    Fragment fragmentInicioBuscar;
    Fragment fragmentPersonaResultado;
    Fragment fragmentVehiculoResultado;

    Persona persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        fragmentInicioBuscar = new BuscarInicio();
        fragmentPersonaResultado = new BuscarPersonaResultado();
        fragmentVehiculoResultado = new BuscarVehiculoResultado();

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

        consultarRut(datoTemp);
    }

    public void consultarRut(String datoTemp){


        Communicator communicator = new Communicator();
        persona = communicator.obtenerPersona(datoTemp);

        if(persona != null){

            resultadoPersona();
        }else{

            Toast.makeText(this, "PERSONA CON RUT: "+datoTemp+" NO ENCONTRADA", Toast.LENGTH_SHORT).show();
        }
    }

    public Persona getRegistro(){

        return this.persona;
    }

    public void resultadoPersona() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorFragments, fragmentPersonaResultado).addToBackStack(null).commit();
    }

    public void resultadoVehiculo() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorFragments, fragmentVehiculoResultado).addToBackStack(null).commit();
    }
}