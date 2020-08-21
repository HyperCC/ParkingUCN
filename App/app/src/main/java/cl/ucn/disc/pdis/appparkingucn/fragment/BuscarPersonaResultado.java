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
import android.widget.TextView;

import cl.ucn.disc.pdis.appparkingucn.Buscar;
import cl.ucn.disc.pdis.appparkingucn.R;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Estado;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Persona;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Registro;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarPersonaResultado extends Fragment {

    // Variable declaration.
    Registro registro;
    Persona persona;

    // Header view variables.
    TextView tituloBuscarResultado;
    TextView tipoDatoBuscado;

    // Variables for patente format.
    TextView datoBuscado;
    TextView datoRegistroBuscado;

    // Variable for Persona display name.
    TextView nombrePersonaBuscada;

    // Variable for date and time format design.
    TextView tipoFechaRegistro;
    TextView tipoHoraRegistro;

    // Variable for date and time.
    TextView fechaRegistro;
    TextView horaRegistro;

    // Variable for view transition.
    View vista;

    /**
     *  Fragment constructor
     */
    public BuscarPersonaResultado() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_buscar_persona_resultado, container, false);

        // Header data display.
        tituloBuscarResultado = (TextView) vista.findViewById(R.id.tituloBuscarResultado);
        tipoDatoBuscado = (TextView) vista.findViewById(R.id.tipoDatoBuscado);

        // Display for the Persona name.
        nombrePersonaBuscada = (TextView) vista.findViewById(R.id.nombrePersonaBuscada);

        // display for rut or patente.
        datoBuscado = (TextView) vista.findViewById(R.id.datoBuscado);
        datoRegistroBuscado = (TextView) vista.findViewById(R.id.datoRegistroBuscado);

        // Date format display.
        tipoFechaRegistro = (TextView) vista.findViewById(R.id.tipoFechaRegistro);
        tipoHoraRegistro = (TextView) vista.findViewById(R.id.tipoHoraRegistro);

        // Date display.
        fechaRegistro = (TextView) vista.findViewById(R.id.fechaRegistro);
        horaRegistro = (TextView) vista.findViewById(R.id.horaRegistro);

        // Gets a Registro from activity Buscar.
        registro = ((Buscar)getActivity()).getRegistro();

        // Gets a Persona from activity Buscar.
        persona = ((Buscar)getActivity()).getPersona();

        // Sets the name of Persona.
        nombrePersonaBuscada.setText(persona.nombre);

        // if estado equals entrada.
        if(registro.estado == Estado.ENTRADA){

            // Sets the date and time display format to entrada.
            tipoFechaRegistro.setText("Fecha Entrada");
            tipoHoraRegistro.setText("Hora Entrada");
        }else{

            // Sets the date and time display format to salida.
            tipoFechaRegistro.setText("Fecha Salida");
            tipoHoraRegistro.setText("Hora Salida");
        }

        // Sets the date and time.
        fechaRegistro.setText(registro.fecha);
        horaRegistro.setText(registro.hora);

        // if tipoDato equals responsable.
        if(((Buscar)getActivity()).getTipoDato() == "responsable"){

            // Sets the header display to RUT format and displays the patente in the table.
            tituloBuscarResultado.setText("RESULTADO PARA RUT");
            tipoDatoBuscado.setText(registro.responsable);
            datoBuscado.setText("Patente: ");
            datoRegistroBuscado.setText(registro.patente);
        }else{

            // Sets the header display to Patente format and displays the RUT in the table.
            tituloBuscarResultado.setText("RESULTADO PARA PATENTE");
            tipoDatoBuscado.setText(registro.patente);
            datoBuscado.setText("RUT: ");
            datoRegistroBuscado.setText(registro.responsable);
        }

        // View return.
        return vista;
    }
}