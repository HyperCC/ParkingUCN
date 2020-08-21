<?php

namespace App\Http\Controllers;

use App\Http\Requests\SaveVehiculoRequest;
use App\IdentifierValidator;
use App\InitializeConnection;
use App\User;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Validation\ValidationException;
use model\Vehiculo;

require 'Ice.php';
require_once base_path() . './domain.php';

class VehiculoController extends Controller
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
    public function create()
    {
        return view('VehiculoOperations.create');
    }

    /**
     * Store a newly Vehiculo created in storage.
     *
     * @param SaveVehiculoRequest $request
     * @return Response
     * @throws ValidationException
     */
    public function store(SaveVehiculoRequest $request)
    {
        // validacion de parametros en form request
        $request->validate([]);

        // dar formato a la patente
        $validador = new IdentifierValidator();
        $patenteValida = $validador->validarPatente(\request('patente'));

        $rutValidado = $validador->validarRut(\request('responsable'));
        if (!$rutValidado) {
            throw ValidationException::withMessages([
                'El formato del Rut proporcionado no es valido, debe utilizar uno de los formatos validos: 12223334, 1222333-4 ó 1.222.333-4',
            ]);
        }

        $vehiculo = new Vehiculo(
        // UID se cambiara en el Servidor
            1,
            $patenteValida,
            request('marca'),
            request('modelo'),
            request('anio'),
            request('observacion'),
            $rutValidado
        );

        return $vehiculo;

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
