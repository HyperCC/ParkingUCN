<?php


namespace App;

use model\ContratosPrxHelper;

require_once 'Ice.php';
require_once base_path() . './domain.php';

/**
 * Class InitializeConnection to create the instance with the Server.
 * @package App
 */
class InitializeConnection
{
    /**
     * Initialize the communicator to get the Contratos
     *
     * @return ContratosPrxHelper
     */
    function getContratos()
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

// references guides.
// https://doc.zeroc.com/ice/3.7/language-mappings/php-mapping/client-side-slice-to-php-mapping/application-notes-for-php
// https://doc.zeroc.com/ice/3.7/client-side-features/proxies/obtaining-proxies

}
