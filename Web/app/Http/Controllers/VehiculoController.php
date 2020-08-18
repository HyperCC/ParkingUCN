<?php

namespace App\Http\Controllers;

use App\InitializeConnection;
use Illuminate\Http\Request;
use model\Vehiculo;

require 'Ice.php';
require_once base_path() . './domain.php';

class VehiculoController extends Controller
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
        return view('VehiculoOperations.create');
    }

    /**
     * Store a newly Vehiculo created in storage.
     *
     * @param \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store()
    {
        $vehiculo = new Vehiculo(
        // UID se cambiara en el Servidor
            10000,
            request('patente'),
            request('marca'),
            request('modelo'),
            request('anio'),
            request('observacion'),
            request('responsable')
        );

        // instancia de ICE.
        $connection = new InitializeConnection();
        $contratos = $connection->getContratos();

        // envio de la vehiculo al servidor.
        $contratos->crearVehiculo($vehiculo);

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
