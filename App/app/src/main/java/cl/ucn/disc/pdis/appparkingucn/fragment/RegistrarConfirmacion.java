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

    // Declarar variables.
    private Persona persona;
    private Vehiculo vehiculo;

    private View vista;

    private TextView nomDispConfReg;
    private TextView rutDispConfReg;
    private TextView patenteDispConfReg;

    private TextView fechaConfreg;
    private TextView horaConfReg;

    private TextView fechaDispConfReg;
    private TextView horaDispConfReg;

    private Button botonConfirmar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment.
        vista = inflater.inflate(R.layout.fragment_registrar_confirmacion, container, false);

        // Inicializar variables.
        nomDispConfReg = (TextView) vista.findViewById(R.id.nomDispConfReg);
        rutDispConfReg = (TextView) vista.findViewById(R.id.rutDispConfReg);
        patenteDispConfReg = (TextView) vista.findViewById(R.id.patenteDispConfReg);

        fechaConfreg = (TextView) vista.findViewById(R.id.fechaConfReg);
        horaConfReg = (TextView) vista.findViewById(R.id.horaConfReg);

        fechaDispConfReg = (TextView) vista.findViewById(R.id.fechaDispConfReg);
        horaDispConfReg = (TextView) vista.findViewById(R.id.horaDispConfReg);

        botonConfirmar = (Button) vista.findViewById(R.id.botonConfirmar);


        // Llenar variables.
        persona = ((RegistroVehiculo)getActivity()).getPersona();
        vehiculo = ((RegistroVehiculo)getActivity()).getVehiculo();

        nomDispConfReg.setText(persona.nombre);
        rutDispConfReg.setText(persona.rut);
        patenteDispConfReg.setText(vehiculo.patente);

        fechaConfreg.setText("Fecha "+((RegistroVehiculo)getActivity()).getTipoRegistro()+":  ");
        horaConfReg.setText("Hora "+((RegistroVehiculo)getActivity()).getTipoRegistro()+":  ");

        fechaDispConfReg.setText(((RegistroVehiculo)getActivity()).getFechaRegistro());
        horaDispConfReg.setText(((RegistroVehiculo)getActivity()).getHoraRegistro());

        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((RegistroVehiculo)getActivity()).generarRegistro();
            }
        });

        return vista;
    }
}