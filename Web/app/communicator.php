<?php
require_once 'Ice.php';
require_once 'domain.php';

try {
    echo 'Hola Mundo';

    $data = new Ice\InitializationData;
    $data->properties = Ice\getProperties();
    $communicator = Ice\initialize($data);
    $proxy = $communicator->stringToProxy("Contratos:tcp -z -t 15000 -p 8080");
    $contratosCall = \model\ContratosPrxHelper::checkedCast($proxy);

    // llamado a un contrato
    $per = $contratosCall->obtenerPersona("3.915.487-0");
    //$contratosCall->obtenerPersona((string)("5.357.633-8"));
    echo $per->nombre;


} catch (Ice\LocalException $ex) {
    echo 'erro: '.$ex;

}


if ($communicator) {
    try {
        $communicator->destroy();
    } catch (Ice\LocalException $ex) {
        // Ignore.
    }
}

// references guides.
// https://doc.zeroc.com/ice/3.7/language-mappings/php-mapping/client-side-slice-to-php-mapping/application-notes-for-php
// https://doc.zeroc.com/ice/3.7/client-side-features/proxies/obtaining-proxies

?>
