<?php
require_once 'Ice.php';
require_once 'domain.php';

try {

    syslog(0, 'Inicializacion del Communicator');

    // inicializacion de propiedades
    $data = new Ice\InitializationData;
    $data->properties = Ice\getProperties();
    $communicator = Ice\initialize($data);

    // declaracion del proxy
    $proxy = $communicator->stringToProxy("Contratos:tcp -z -t 15000 -p 8080");
    $contratosCall = \model\ContratosPrxHelper::checkedCast($proxy);

    // llamado a un contrato de prueba
    $per = $contratosCall->obtenerPersona("3.915.487-0");

    //$contratosCall->obtenerPersona((string)("5.357.633-8"));
    // TODO: perfeccionar los contratos, metodo Find no funcion con rut.
    syslog(0, 'El nombre recibido por el servidor: ' . $per->nombre);


} catch (Ice\LocalException $ex) {
    syslog(0, 'Error capturado: ' . $ex);

}


if ($communicator) {
    try {
        // finalizacion del Communicator.
        $communicator->destroy();
    } catch (Ice\LocalException $ex) {
        // Ignore.
    }
}

// references guides.
// https://doc.zeroc.com/ice/3.7/language-mappings/php-mapping/client-side-slice-to-php-mapping/application-notes-for-php
// https://doc.zeroc.com/ice/3.7/client-side-features/proxies/obtaining-proxies

?>
