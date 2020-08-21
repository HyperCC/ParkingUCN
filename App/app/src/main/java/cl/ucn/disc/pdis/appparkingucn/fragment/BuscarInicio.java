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

package cl.ucn.disc.pdis.appparkingucn.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import cl.ucn.disc.pdis.appparkingucn.Buscar;
import cl.ucn.disc.pdis.appparkingucn.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarInicio extends Fragment {


    // Variable declaration.
    Button botonBuscar;
    RadioGroup seleccion;
    EditText editText;

    // View for fragment transition.
    View vista;

    /**
     *  Fragment constructor
     */
    public BuscarInicio() {
        // Required empty public constructor.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Variable inicialization.
        vista = inflater.inflate(R.layout.fragment_buscar_inicio, container, false);

        botonBuscar = (Button) vista.findViewById(R.id.botonBuscar);
        seleccion = (RadioGroup) vista.findViewById(R.id.seleccion);
        editText = (EditText) vista.findViewById(R.id.editText);

        // On button click.
        botonBuscar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // If rut search is selected.
                if (seleccion.getCheckedRadioButtonId() == R.id.botonRut) {

                    /*
                     * sets the tipoDato to "responsable" because it holds the "rut" of a certain persona.
                     * and calls the method reestructurarRut.
                     */
                    ((Buscar)getActivity()).setTipoDato("responsable");
                    ((Buscar)getActivity()).reestructurarRut(editText.getText().toString());
                }

                // If patente search is selected.
                if (seleccion.getCheckedRadioButtonId() == R.id.botonPatente) {

                    // sets the tipoDato to "patente" and calls the method limpiarPatente.
                    ((Buscar)getActivity()).setTipoDato("patente");
                    ((Buscar)getActivity()).limpiarPatente(editText.getText().toString());
                }
            }
        });

        // Returns fragment view.
        return vista;
    }
}