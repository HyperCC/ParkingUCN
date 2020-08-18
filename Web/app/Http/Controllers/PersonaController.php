<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use model\ContratosPrxHelper;
use model\Persona;
use model\Sexo;
use App\InitializeConnection;

require 'Ice.php';
require_once base_path() . './domain.php';

class PersonaController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('PersonaOperations.create');

    }

    /**
     * Store a newly Persona created in storage.
     *
     * @param \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store()
    {
        $persona = new Persona(
        // UID y WEBID se modificaran en el servidor para concordar con los registros.
            10001,
            null,
            request('name'),
            request('rut'),
            request('sexo') == 'VAR' ? Sexo::_VAR : Sexo::MUJ,
            request('cargo'),
            request('unidad'),
            request('email'),
            request('telefono'),
            request('oficina'),
            request('direccion_trabajo'),
            request('direccion_casa'),
            request('comuna')
        );

        // instancia de ICE.
        $connection = new InitializeConnection();
        $contratos = $connection->getContratos();

        // envio de la persona al servidor.
        $contratos->crearPersona($persona);

        return redirect(route('home'));
    }

    /**
     * Display the specified resource.
     *
     * @param int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param \Illuminate\Http\Request $request
     * @param int $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
