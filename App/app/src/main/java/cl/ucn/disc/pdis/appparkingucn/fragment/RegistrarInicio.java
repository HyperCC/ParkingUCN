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

import cl.ucn.disc.pdis.appparkingucn.R;
import cl.ucn.disc.pdis.appparkingucn.RegistroVehiculo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {RegistrarInicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarInicio extends Fragment {

    // Variable declaration.
    Button botonRegistrar;
    RadioGroup tipoRegistro;
    EditText datoPatente;

    // View transition variable.
    View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_registrar_inicio, container, false);

        // Variable initialization.
        botonRegistrar = (Button) vista.findViewById(R.id.botonRegistrar);
        tipoRegistro = (RadioGroup) vista.findViewById(R.id.tipoRegistro);
        datoPatente = (EditText) vista.findViewById(R.id.datoRegistroVehicular);

        // On button click.
        botonRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // If the Registro its a Entrada.
                if (tipoRegistro.getCheckedRadioButtonId() == R.id.entrada) {

                    // Sets the tipoRegistro as Entrada and calls the method limpiarPatente.
                    ((RegistroVehiculo)getActivity()).setTipoRegistro("Entrada");
                    ((RegistroVehiculo)getActivity()).limpiarPatente(datoPatente.getText().toString());
                }

                // If the Registro its a Salida.
                if (tipoRegistro.getCheckedRadioButtonId() == R.id.salida) {

                    // Sets the tipoRegistro as Salida and calls the method limpiarpatente.
                    ((RegistroVehiculo)getActivity()).setTipoRegistro("Salida");
                    ((RegistroVehiculo)getActivity()).limpiarPatente(datoPatente.getText().toString());
                }
            }
        });

        // Returns the view.
        return vista;
    }
}