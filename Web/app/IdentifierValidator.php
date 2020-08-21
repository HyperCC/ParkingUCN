<?php


namespace App;

use Illuminate\Validation\ValidationException;

require 'Ice.php';
require_once base_path() . './domain.php';

/*
casos posibles rut:

max char: 12
min char: 8

11.222.333-4
1.222.333-4
11222333-4
1222333-4
112223334
12223334

no se considero el formato 1.222.3334
solo se toma como incorrecto por no usarse recurrentemente

casos posbiles patente:(ignorar mayusculas)

maximo char: 8
min char: 6

XX-YY-ZZ
XXYYZZ

*/

/**
 * Class IdentifierValidator para identificar y validar formatos.
 *
 * @package App
 */
class IdentifierValidator
{
    /**
     * The default Constructor
     */
    public function IdentifierValidator()
    {
        // default void
    }

    /**
     * Funcion para identificar si el parametro es Rut o Patente. Verificador exclusivo de la busqueda.
     *
     * @param string $identificador
     * @return null
     */
    public function identificarRutOPatente(string $identificador)
    {


        $identificador = strtoupper($identificador);

        // instancia de ICE.
        $connection = new InitializeConnection();
        $contratos = $connection->getContratos();

        // envio de la vehiculo al servidor.

        //return $contratos->obtenerVehiculo(null);

        // si el largo pertenece a un rut
        if (strlen($identificador) > 8 & strlen($identificador) <= 12) {

            $result = $this->validarRut($identificador);
            return $contratos->obtenerPersona($result);

            // el identificador aca puede responder a un rut o patente segun el largo
        } elseif (strlen($identificador) == 8) {

            // patente formateada completamente?
            if (($identificador[2] & $identificador[5]) == '-' & ctype_alnum(str_replace('-', '', $identificador))) {

                return $contratos->obtenerVehiculo($identificador);

                // si no es patente, podria ser un rut de largo 8
            } else {

                $result = $this->validarRut($identificador);
                return $contratos->obtenerPersona($result);

            }

            // posiblemente el identificador es una patente
        } elseif (strlen($identificador) < 8 & strlen($identificador) >= 6) {

            return $contratos->obtenerVehiculo($this->validarPatente($identificador));

            // el identificador no tiene un valor valido.
        } else {

            return null;

        }
    }

    /**
     * Asignador de formato correcto para rut antes de consultar en el Server. Verificador generico.
     *
     * @param string $rut
     * @return null
     * @throws ValidationException
     */
    public function validarRut(string $rut)
    {
        // siempre debe ir el upper de llamarse este metodo independientemente.
        $rut = strtoupper($rut);
        $formatedRut = null;

        // verificar el ultimo digito, debe ser numerico o K
        if (ctype_digit(substr($rut, -1)) == false & substr($rut, -1) != 'K')
            throw ValidationException::withMessages([
                'El formato del Rut proporcionado no es valido, debe terminar en un número o K',
            ]);

        // rut con formato 11.222.333-4
        if (strlen($rut) == 12) {

            // verificar ue el rut tenga correcto formato
            if (($rut[2] & $rut[6]) == '.'
                & $rut[10] == '-'
                // verificar que los characteres que no son '.' o '-' son numericos, excepto el primero.
                & ctype_digit(str_replace('.', '', substr($rut, 0, (strlen($rut) - 2))))
            ) {

                // dado el formato inicial correcto, solo podria ser rechazado por el servidor de no existir.
                return $rut;
            }

            // rut con formato 1.222.333-4
        } elseif (strlen($rut) == 11) {

            // verificar el formato, dado el largo 11, no hay caso favorable para modificar.
            if (($rut[1] & $rut[5]) == '.'
                & $rut[9] == '-'
                & ctype_digit(str_replace('.', '', substr($rut, 0, (strlen($rut) - 2))))
            ) {

                return $rut;
            }

            // rut con formato 11222333-4
        } elseif (strlen($rut) == 10) {

            // verificar formato con guion unico antes de DV
            if ($rut[8] == '-' & ctype_digit(substr($rut, 0, (strlen($rut) - 2)))) {

                // dar formato correcto con puntos antes de enviar al servidor
                for ($i = 0; $i < 10; $i++) {
                    $formatedRut .= (($i == 2 || $i == 5) ? '.' : '') . $rut[$i];
                }

                return $formatedRut;
            }

            // rut con formato 1222333-4 o 112223334
        } elseif (strlen($rut) == 9) {

            // verificar rut con formato 1222333-4
            if ($rut[7] == '-' & ctype_digit(substr($rut, 0, (strlen($rut) - 2)))) {

                // dar formato correcto con puntos antes de enviar al servidor
                for ($i = 0; $i < 9; $i++) {
                    $formatedRut .= (($i == 1 || $i == 4) ? '.' : '') . $rut[$i];

                }
                return $formatedRut;

                // verificar rut con formato 112223334
            } elseif (ctype_digit(substr($rut, 0, (strlen($rut) - 1)))) {

                // dar formato correcto con puntos antes de enviar al servidor
                for ($i = 0; $i < 9; $i++) {

                    $formatedRut .= (($i == 2 || $i == 5) ? '.' : '') . $rut[$i];
                    if ($i == 7) $formatedRut .= '-';

                }

                return $formatedRut;

                // rut de formato no valido
            } else {
                return null;

            }

            // rut con formato 12223334
        } elseif (strlen($rut) == 8) {

            // verificar validez del formato
            if (ctype_digit(substr($rut, 0, (strlen($rut) - 1)))) {

                // dar formato correcto con puntos antes de enviar al servidor
                for ($i = 0; $i < 8; $i++) {

                    $formatedRut .= (($i == 1 || $i == 4) ? '.' : '') . $rut[$i];
                    if ($i == 6) $formatedRut .= '-';

                }
                return $formatedRut;

            }

            // rut en formato no aceptable
        } else {
            return null;

        }
    }

    /**
     * Asignador de formato correcto para patente antes de consultar en el Server. Verificador generico.
     *
     * @param string $patente
     * @return null
     */
    public function validarPatente(string $patente)
    {
        $patente = strtoupper($patente);
        $formatedPatente = null;

        // patente con formato XX-YY-ZZ
        if (strlen($patente) == 8) {

            // verificar el formato correcto
            if (($patente[2] & $patente[5]) == '-' & ctype_alnum(str_replace('-', '', $patente))) {

                // no hay necesidad de modificacion
                return $patente;
            }

            // patente con formato XXYYZZ
        } elseif (strlen($patente) == 6) {

            // verificar validez de contenido (numeros y texto)
            if (ctype_alnum($patente)) {

                // asignar formato correcto a patente segun la DB del server.
                for ($i = 0; $i < 6; $i++) {
                    $formatedPatente .= (($i == 2 || $i == 4) ? '-' : '') . $patente[$i];
                }
                return $formatedPatente;
            }

            // se recibio un parametro no aceptable.
        } else {
            return null;

        }

    }

}
