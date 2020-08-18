<?php

namespace App\Http\Controllers;

use App\InitializeConnection;
use Illuminate\Http\Request;
use model\Estado;
use model\Registro;

require 'Ice.php';
require_once base_path() . './domain.php';

class RegistroController extends Controller
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
    public function createEntrada()
    {
        return view('RegistroOperations.createentrada');
    }

    /**
     * Show the form for create a new Registro.
     *
     * @return \Illuminate\Http\Response
     */
    public function createSalida()
    {
        return view('RegistroOperations.createsalida');
    }

    /**
     * Store a newly Registro created in storage.
     *
     * @param \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store()
    {
        // date_default_timezone_set('America/Santiago');
        $fecha = getdate();

        // fecha actual en formato dd-mm-yyyy
        $fecha_formated = strval($fecha['mday'] . '-' . $fecha['mon'] . '-' . $fecha['year']);

        // hora actual en formato hh:mm:ss
        $hora_formated = strval(($fecha['hours']) . ':' . $fecha['minutes'] . ':' . $fecha['seconds']);

        $registro = new Registro(
        // the UID will change in the Server
            10000,
            request('patente'),
            request('rut'),
            $fecha_formated,
            $hora_formated,
            // enum of Estado.
            (request('estado') == 'Entrada') ? Estado::ENTRADA : Estado::SALIDA
        );

        // instancia de ICE.
        $connection = new InitializeConnection();
        $contratos = $connection->getContratos();

        // envio de la registro al servidor.
        $contratos->crearRegistro($registro);

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
