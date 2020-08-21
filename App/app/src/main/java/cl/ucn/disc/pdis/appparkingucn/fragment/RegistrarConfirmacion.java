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

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cl.ucn.disc.pdis.appparkingucn.R;
import cl.ucn.disc.pdis.appparkingucn.RegistroVehiculo;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Persona;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Vehiculo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {RegistrarConfirmacion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarConfirmacion extends Fragment {

    // Variable declaration
    private Persona persona;
    private Vehiculo vehiculo;

    // View declaration
    private View vista;

    // Variable for Registro data display
    private TextView nomDispConfReg;
    private TextView rutDispConfReg;
    private TextView patenteDispConfReg;

    // Variable for date and time type (Entrada/Salida)
    private TextView fechaConfreg;
    private TextView horaConfReg;

    // Variable for date and time data
    private TextView fechaDispConfReg;
    private TextView horaDispConfReg;

    // Submit button
    private Button botonConfirmar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment.
        vista = inflater.inflate(R.layout.fragment_registrar_confirmacion, container, false);

        // Registro data display initialization
        nomDispConfReg = (TextView) vista.findViewById(R.id.nomDispConfReg);
        rutDispConfReg = (TextView) vista.findViewById(R.id.rutDispConfReg);
        patenteDispConfReg = (TextView) vista.findViewById(R.id.patenteDispConfReg);

        // Date and time type display initialization (Entrada/Salida)
        fechaConfreg = (TextView) vista.findViewById(R.id.fechaConfReg);
        horaConfReg = (TextView) vista.findViewById(R.id.horaConfReg);

        // Date and time data initialization
        fechaDispConfReg = (TextView) vista.findViewById(R.id.fechaDispConfReg);
        horaDispConfReg = (TextView) vista.findViewById(R.id.horaDispConfReg);

        // Submit button initialization
        botonConfirmar = (Button) vista.findViewById(R.id.botonConfirmar);


        // Gets the Persona and Vehiculo data.
        persona = ((RegistroVehiculo)getActivity()).getPersona();
        vehiculo = ((RegistroVehiculo)getActivity()).getVehiculo();

        // Sets the Registro data
        nomDispConfReg.setText(persona.nombre);
        rutDispConfReg.setText(persona.rut);
        patenteDispConfReg.setText(vehiculo.patente);

        // Sets the date and time type display of the Registro (Entrada/Salida)
        fechaConfreg.setText("Fecha "+((RegistroVehiculo)getActivity()).getTipoRegistro()+":  ");
        horaConfReg.setText("Hora "+((RegistroVehiculo)getActivity()).getTipoRegistro()+":  ");

        // Sets the date and time data
        fechaDispConfReg.setText(((RegistroVehiculo)getActivity()).getFechaRegistro());
        horaDispConfReg.setText(((RegistroVehiculo)getActivity()).getHoraRegistro());

        // On button click
        botonConfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Submits the data and creates a new Registro
                ((RegistroVehiculo)getActivity()).generarRegistro();
            }
        });

        // View return
        return vista;
    }
}