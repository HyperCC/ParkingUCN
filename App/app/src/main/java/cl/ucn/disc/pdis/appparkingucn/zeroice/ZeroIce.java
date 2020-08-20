/*
 * Copyright (c) 2020. Castillo - Condorcet - Pizarro Engineering Students.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cl.ucn.disc.pdis.appparkingucn.zeroice;

import cl.ucn.disc.pdis.simplescraper.zeroice.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zeroc.Ice.*;

/**
 * ZeroIce Class
 */
@SuppressWarnings("Singleton")
public final class ZeroIce {

    // The logger
    private static final Logger log = LoggerFactory.getLogger(ZeroIce.class);

    // Server IP (MUST BE SETTED BY ADMINISTRATOR)
    private String serverIP = "192.168.0.7";

    // Singleton
    private static final ZeroIce ZERO_ICE = new ZeroIce();

    // The communicator
    private Communicator communicator;

    // The contratos
    private ContratosPrx contratosPrx;

    /**
     * The constructor
     */
    public ZeroIce() {
        //nothing here
    }

    /**
     * Get instance
     * @return ZeroIce
     */
    public static ZeroIce getInstance() {

        return ZERO_ICE;
    }

    /**
     * Get Contratos
     * @return contratos
     */
    public ContratosPrx getContratosPrx() {

        return this.contratosPrx;
    }

    /**
     * Communication starts
     */
    public void start() {

        // checks if the communicator was already started
        if(this.communicator != null) {

            log.warn("The communicator was already initialized?");
            return;
        }

        String[] args = {"one", "two"};
        this.communicator = Util.initialize(getInitializationData(args));

        // The name of contratos
        String nameContratos = Contratos.class.getSimpleName();

        // The proxy
        ObjectPrx theProxy = this.communicator.stringToProxy(nameContratos + ":tcp -h "+serverIP+" -t 15000 -p 8080");
        this.contratosPrx = ContratosPrx.checkedCast(theProxy);
    }

    /**
     * Initialization of ZeroIce
     * @param args to use as a source
     * @return the {@link InitializationData}.
     */
    private static InitializationData getInitializationData(String[] args) {

        // Properties
        final Properties properties = Util.createProperties(args);
        properties.setProperty("Ice.Package.model", "cl.ucn.disc.pdis.simplescraper.zeroice");

        // https://doc.zeroc.com/ice/latest/property-reference/ice-trace
        // properties.setProperty("Ice.Trace.Admin.Properties", "1");
        // properties.setProperty("Ice.Trace.Locator", "2");
        // properties.setProperty("Ice.Trace.Network", "3");
        // properties.setProperty("Ice.Trace.Protocol", "1");
        // properties.setProperty("Ice.Trace.Slicing", "1");
        // properties.setProperty("Ice.Trace.ThreadPool", "1");
        // properties.setProperty("Ice.Compression.Level", "9");
        //properties.setProperty("Ice.Plugin.Slf4jLogger.java", "cl.ucn.disc.pdis.simplescraper.zeroice.Slf4jLoggerPluginFactory");

        InitializationData initializationData = new InitializationData();
        initializationData.properties = properties;

        return initializationData;
    }

    /**
     * Communication stopped
     */
    public void stop() {

        // Checks if the communicator was already stopped
        if (this.communicator == null) {

            log.warn("The communicator was already stopped?");
            return;
        }
        this.contratosPrx = null;
        this.communicator.destroy();
    }
}