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

    }

    /**
     * Asignador de formato correcto para patente antes de consultar en el Server.
     *
     * @param string $patente
     */
    public function validarPatente(string $patente)
    {

    }

}
