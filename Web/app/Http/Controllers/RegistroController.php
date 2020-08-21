<?php

namespace App\Http\Controllers;

use App\Http\Requests\SaveBusquedaRequest;
use App\Http\Requests\SaveVehiculoRequest;
use App\IdentifierValidator;
use App\InitializeConnection;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Validation\ValidationException;
use model\Estado;
use model\Registro;

require 'Ice.php';
require_once base_path() . './domain.php';

class RegistroController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return Response
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return Response
     */
    public function createEntrada()
    {
        return view('RegistroOperations.createentrada', [
            'registro' => new Registro
        ]);
    }

    /**
     * Show the form for create a new Registro.
     *
     * @return Response
     */
    public function createSalida()
    {
        return view('RegistroOperations.createsalida', [
            'registro' => new Registro
        ]);
    }

    /**
     * Store a newly Registro created in storage.
     *
     * @param SaveBusquedaRequest $request
     * @return Response
     * @throws ValidationException
     */
    public function store(SaveVehiculoRequest $request)
    {
        // validacion de parametros en form request
        $request->validate([]);

        // validaciones de formato
        $validador = new IdentifierValidator();
        // validar formato de rut
        $rutValidado = $validador->validarRut(\request('responsable'));

        // verificar si el formato es valido
        if (!$rutValidado) {
            throw ValidationException::withMessages([
                'El formato del Rut proporcionado no es valido, debe utilizar uno de los formatos validos: 12223334, 1222333-4 ó 1.222.333-4',
            ]);
        }

        // validar formato de patente
        $patenteValida = $validador->validarPatente(\request('patente'));

        // date_default_timezone_set('America/Santiago');
        $fecha = getdate();

        // fecha actual en formato dd-mm-yyyy
        $fecha_formated = strval($fecha['mday'] . '-' . $fecha['mon'] . '-' . $fecha['year']);

        // hora actual en formato hh:mm:ss
        $hora_formated = strval(($fecha['hours']) . ':' . $fecha['minutes'] . ':' . $fecha['seconds']);

        $registro = new Registro(
        // the UID will change in the Server
            1,
            $patenteValida,
            $rutValidado,
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
     * @return Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param int $id
     * @return Response
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
     * @return Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param int $id
     * @return Response
     */
    public function destroy($id)
    {
        //
    }
}
