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

    Registro registro;
    Persona persona;

    // Diseño vista
    TextView tituloBuscarResultado;
    TextView tipoDatoBuscado;
    TextView datoBuscado;
    TextView datoRegistroBuscado;



    TextView nombrePersonaBuscada;

    TextView tipoFechaRegistro;
    TextView tipoHoraRegistro;

    TextView fechaRegistro;
    TextView horaRegistro;

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

        // Encabezado
        tituloBuscarResultado = (TextView) vista.findViewById(R.id.tituloBuscarResultado);
        tipoDatoBuscado = (TextView) vista.findViewById(R.id.tipoDatoBuscado);


        nombrePersonaBuscada = (TextView) vista.findViewById(R.id.nombrePersonaBuscada);

        datoBuscado = (TextView) vista.findViewById(R.id.datoBuscado);
        datoRegistroBuscado = (TextView) vista.findViewById(R.id.datoRegistroBuscado);

        tipoFechaRegistro = (TextView) vista.findViewById(R.id.tipoFechaRegistro);
        tipoHoraRegistro = (TextView) vista.findViewById(R.id.tipoHoraRegistro);

        fechaRegistro = (TextView) vista.findViewById(R.id.fechaRegistro);
        horaRegistro = (TextView) vista.findViewById(R.id.horaRegistro);

        //--
        registro = ((Buscar)getActivity()).getRegistro();
        persona = ((Buscar)getActivity()).getPersona();

        //--

        nombrePersonaBuscada.setText(persona.nombre);

        if(registro.estado == Estado.ENTRADA){

            tipoFechaRegistro.setText("Fecha Entrada");
            tipoHoraRegistro.setText("Hora Entrada");
        }else{

            tipoFechaRegistro.setText("Fecha Salida");
            tipoHoraRegistro.setText("Hora Salida");
        }

        fechaRegistro.setText(registro.fecha);
        horaRegistro.setText(registro.hora);

        if(((Buscar)getActivity()).getTipoDato() == "responsable"){

            tituloBuscarResultado.setText("RESULTADO PARA RUT");
            tipoDatoBuscado.setText(registro.responsable);
            datoBuscado.setText("Patente: ");
            datoRegistroBuscado.setText(registro.patente);
        }else{

            tituloBuscarResultado.setText("RESULTADO PARA PATENTE");
            tipoDatoBuscado.setText(registro.patente);
            datoBuscado.setText("RUT: ");
            datoRegistroBuscado.setText(registro.responsable);
        }

        // Inflate the layout for this fragment
        return vista;
    }
}