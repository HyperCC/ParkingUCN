<?php


namespace App;

/*
casos rut:

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

casos patente:(ignorar mayusculas)

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
     * Funcion para identificar si el parametro es Rut o Patente.
     *
     * @param string $identificador
     * @return null
     */
    public function identificarRutOPatente(string $identificador)
    {
        // si el largo pertenece a un rut
        if (strlen($identificador) > 8 & strlen($identificador) <= 12) {

            $this->validarRut($identificador);

            // el identificador aca puede responder a un rut o patente segun el largo
        } elseif (strlen($identificador) == 8) {

            // patente formateada
            if (($identificador[2] & $identificador[5]) == '-' & ctype_alnum(str_replace('-', '', $identificador))) {
                echo 'SI ES UNA PATENTE VALIDA';
                //DEVOLVER PATENTE LISTA

                // posible rut encontrado
            } else {
                echo ' la palabra puede ser rut';
                $this->validarRut($identificador);
                //BUSCAR RUT
            }

            // posiblemente el identificador es una patente
        } elseif (strlen($identificador) < 8 & strlen($identificador) >= 6) {

            $this->validarPatente($identificador);

            // el identificador no tiene un valor valido.
        } else {
            echo 'IDENTIFICADOR NO VALIDA';
            return null;

        }
    }

    /**
     * Asignador de formato correcto para rut antes de consultar en el Server.
     *
     * @param string $rut
     * @return null
     */
    public function validarRut(string $rut)
    {
        $formatedRut = null;

        // rut con formato 11.222.333-4
        if (strlen($rut) == 12) {

            if (($rut[2] & $rut[6]) == '.'
                & $rut[10] == '-'
                // verificar que los characteres que no son '.' o '-' son numericos, excepto el primero.
                & ctype_digit(str_replace('.', '', substr($rut, 0, (strlen($rut) - 3))))
            ) {

                echo "EL RUT PROPORCIONADO ES BUENO 12 CHAR \n";
            }

            // rut con formato 1.222.333-4
        } elseif (strlen($rut) == 11) {

            if (($rut[1] & $rut[5]) == '.'
                & $rut[9] == '-'
            ) {

                echo "EL RUT ESTA BUENO 11 CHAR \n";
            }

            // rut con formato 11222333-4
        } elseif (strlen($rut) == 10) {

            if ($rut[8] == '-' & ctype_digit(substr($rut, 0, (strlen($rut) - 3)))) {

                for ($i = 0; $i < 10; $i++) {
                    $formatedRut .= (($i == 2 || $i == 5) ? '.' : '') . $rut[$i];
                }
            }

            // rut con formato 1222333-4 o 112223334
        } elseif (strlen($rut) == 9) {

            // verificar rut con formato 1222333-4
            if ($rut[7] == '-' & ctype_digit(substr($rut, 0, (strlen($rut) - 3)))) {

                for ($i = 0; $i < 9; $i++) {
                    $formatedRut .= (($i == 1 || $i == 4) ? '.' : '') . $rut[$i];
                }

                // verificar rut con formato 112223334
            } elseif (ctype_digit(substr($rut, 0, (strlen($rut) - 3)))) {
                // OK

            } else {
                return null;

            }

            // rut con formato 12223334
        } elseif (strlen($rut) == 8) {

            if (ctype_digit(substr($rut, 0, (strlen($rut) - 3)))) {
                echo "RUT CORRECTO EN FORMATO\n";

            }

        } else {
            echo "rut no identificado";
            return null;

        }
    }

    /**
     * Asignador de formato correcto para patente antes de consultar en el Server.
     *
     * @param string $patente
     * @return null
     */
    public function validarPatente(string $patente)
    {

        $formatedPatente = null;

        if (strlen($patente) == 8) {

            if (($patente[2] & $patente[5]) == '-' & ctype_alnum(str_replace('-', '', $patente))) {
                echo 'SI ES UNA PATENTE VALIDA';
                //DEVOLVER PATENTE LISTA
            }

        } elseif (strlen($patente) == 6) {

            if (ctype_alnum($patente)) {
                for ($i = 0; $i < 6; $i++) {
                    $formatedPatente .= (($i == 2 || $i == 4) ? '-' : '') . $patente[$i];
                }
                $patente = $formatedPatente;
                //DEVOLVER PATENTE LISTA
            }

        } else {
            echo 'patente NO VALIDA';
            return null;

        }

    }

}
