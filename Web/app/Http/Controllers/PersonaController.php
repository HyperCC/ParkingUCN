<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use model\ContratosPrxHelper;
use model\Persona;
use model\Sexo;
use function Composer\Autoload\includeFile;

require 'Ice.php';
require_once base_path() . './domain.php';


class PersonaController extends Controller
{
    /**
     * Initialize the communicator to get the Contratos
     *
     * @return ContratosPrxHelper
     */
    public function getContratos()
    {
        try {
            syslog(LOG_INFO, 'Initializing the connection with the Server ..');

            $communicator = \Ice\Initialize();

            // declare the Proxy
            $proxy = $communicator->stringToProxy("Contratos:tcp -z -t 15000 -p 8080");

            syslog(LOG_INFO, 'Returning the Contratos connected with the server ..');

            // return the operations from the Contratos
            return \model\ContratosPrxHelper::checkedCast($proxy);

        } catch (Ice\LocalException $ex) {
            syslog(LOG_WARNING, 'Error connecting to the Server: ' . $ex);
            return null;

        }
    }

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
     * Store a newly created resource in storage.
     *
     * @param \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $persona = new Persona(
        // UID y WEBID se modificaran en el servidor para concordar con los registros.
            0,
            0,
            request('name'),
            request('rut'),
            \request('sexo') == 'VAR' ? Sexo::_VAR : Sexo::MUJ,
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
        $contratos = $this->getContratos();

        // envio de la persona al servidor.
        $per = $contratos->crearPersona($persona);

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
