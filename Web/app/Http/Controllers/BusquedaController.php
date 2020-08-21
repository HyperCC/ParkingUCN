<?php

namespace App\Http\Controllers;

use App\Http\Requests\SaveBusquedaRequest;
use App\IdentifierValidator;
use Illuminate\Http\Request;
use model\Persona;
use model\Vehiculo;
use function GuzzleHttp\Psr7\str;

class BusquedaController extends Controller
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
     * @param SaveBusquedaRequest $request
     * @return \Illuminate\Http\Response
     */
    public function search(SaveBusquedaRequest $request)
    {
        // validacion de parametros en form request
        $request->validate([]);

        // generar un identificador generico
        $validarBuscado = new IdentifierValidator();
        $validado = $validarBuscado->identificarRutOPatente(request('buscado'));

        // si el resultado generado existe
        if ($validado) {

            // reconocimiento del identificador como Vehiculo
            if (get_class($validado) == Vehiculo::class)

                return view('VehiculoOperations.show', [
                    'vehiculo' => $validado
                ]);

            // reconocimiento del identificador como Persona
            return view('PersonaOperations.show', [
                'persona' => $validado
            ]);

        }
        // el resultado del formateo no pudo corregirse, dato no identificado
        return view('templates.void', [
            'buscado' => request('buscado')
        ]);

    }

    /**
     * Store a newly created resource in storage.
     *
     * @param \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
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
