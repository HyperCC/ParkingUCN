<?php


namespace App;


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
     */
    public function identifyRutOrPatente(string $identificador)
    {

        if (strlen($identificador) > 8 & strlen($identificador) <= 12) {

            $this->validarRut($identificador);

        } elseif (strlen($identificador) == 8) {

            if (($identificador[2] & $identificador[5]) == '-' & ctype_alnum(str_replace('-', '', $identificador))) {
                echo 'SI ES UNA PATENTE VALIDA';
                //DEVOLVER PATENTE LISTA

            } else {
                echo ' la palabra puede ser rut';
                $this->validarRut($identificador);
                //BUSCAR RUT
            }

        } elseif (strlen($identificador) < 8 & strlen($identificador) >= 6) {

            $this->validarPatente($identificador);

        } else {
            echo 'IDENTIFICADOR NO VALIDA';
            return null;

        }
    }

    /**
     * Asignador de formato correcto para rut antes de consultar en el Server.
     *
     * @param string $rut
     */
    public function validarRut(string $rut)
    {
        $formatedRut = null;

        if (strlen($rut) == 12) {

            if (($rut[2] & $rut[6]) == '.'
                & $rut[10] == '-'
                // verificar que los characteres que no son '.' o '-' son numericos, excepto el primero.
                & ctype_digit(str_replace('.', '', substr($rut, 0, (strlen($rut) - 3))))
            ) {

                echo "EL RUT PROPORCIONADO ES BUENO 12 CHAR \n";
            }

        } elseif (strlen($rut) == 11) {

            if (($rut[1] & $rut[5]) == '.'
                & $rut[9] == '-'
            ) {

                echo "EL RUT ESTA BUENO 11 CHAR \n";
            }

        } elseif (strlen($rut) == 10) {

            if ($rut[8] == '-' & ctype_digit(substr($rut, 0, (strlen($rut) - 3)))) {

                for ($i = 0; $i < 10; $i++) {
                    $formatedRut .= (($i == 2 || $i == 5) ? '.' : '') . $rut[$i];
                }
            }

        } elseif (strlen($rut) == 9) {

            if ($rut[7] == '-' & ctype_digit(substr($rut, 0, (strlen($rut) - 3)))) {

                for ($i = 0; $i < 9; $i++) {
                    $formatedRut .= (($i == 1 || $i == 4) ? '.' : '') . $rut[$i];
                }
            }

        } elseif (strlen($rut) == 8) {

            if (ctype_digit(substr($rut, 0, (strlen($rut) - 3)))) {
                echo "RUT CORRECTO EN FORMATO\n";
            }

        } else {
            echo "rut no identificado";

        }
    }

    /**
     * Asignador de formato correcto para patente antes de consultar en el Server.
     *
     * @param string $patente
     */
    public function validarPatente(string $patente)
    {

        $formatedPatente = null;

        if (strlen($patente) == 8) {

            if (($patente[2] & $patente[5]) == '-' & ctype_alnum(str_replace('-', '', $patente))) {
                echo 'SI ES UNA PATENTE VALIDA';
                //DEVOLVER PATENTE LISTA
            }

        } elseif (strlen($patente) == 6 & ctype_alnum($patente)) {

            for ($i = 0; $i < 6; $i++) {
                $formatedPatente .= (($i == 2 || $i == 4) ? '-' : '') . $patente[$i];
            }
            $patente = $formatedPatente;
            //DEVOLVER PATENTE LISTA

        } else {
            echo 'patente NO VALIDA';

        }

    }

}
