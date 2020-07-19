package cl.ucn.disc.pdis.simplescraper;

import cl.ucn.disc.pdis.simplescraper.model.Persona;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Contratos;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.ContratosPrx;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Sexo;
import com.zeroc.Ice.*;
import cl.ucn.disc.pdis.simplescraper.dbformater.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.sql.SQLException;

/**
 * Uploader class to parse Scraper Personas to Server Personas and transfer them.
 *
 * @author Castillo - Condorcet - Pizarro.
 */
public class Uploader {

    /**
     * The logger.
     */
    private static final Logger log = LoggerFactory.getLogger(Uploader.class);

    /**
     * Main to call the method from Server to give all Personas.
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {

        // Initialize the Communicator.
        try (Communicator communicator = Util.initialize(getInitializationData(args))) {

            String name = Contratos.class.getSimpleName();

            // Running in port 8080 same port that the Server.
            ObjectPrx theProxy = communicator.stringToProxy(name + ":tcp -z -t 15000 -p 8080");

            // Instance of Contratos from domain.ice
            ContratosPrx contratos = ContratosPrx.checkedCast(theProxy);

            // DB operations.
            DbInteraction dbInteraction = new DbInteraction();

            // Transfer the Personas to Server through the Contratos.
            for (int i = 1; i < 10; i++) {

                // Scraper Persona.
                cl.ucn.disc.pdis.simplescraper.model.Persona personaScraper = dbInteraction.findPersona(i);

                // Parse from Scraper enum to Zero ice model enum.
                Sexo sexoZeroIce = parseEnum(personaScraper.getSexo());

                // Zero Ice Persona.
                cl.ucn.disc.pdis.simplescraper.zeroice.model.Persona persona =
                        new cl.ucn.disc.pdis.simplescraper.zeroice.model.Persona(
                                // Default ID to zero ice model.
                                i,
                                personaScraper.getWebId(),
                                personaScraper.getNombre(),
                                personaScraper.getRut(),
                                sexoZeroIce,
                                personaScraper.getCargo(),
                                personaScraper.getUnidad(),
                                personaScraper.getEmail(),
                                personaScraper.getTelefono(),
                                personaScraper.getOficina(),
                                personaScraper.getDireccionTrabajo(),
                                personaScraper.getDireccionCasa(),
                                personaScraper.getComuna());

                // Contrato from Server to her DB.
                contratos.crearPersona(persona);

                log.debug("Persona sended: {}", persona.sexo.toString());
            }

            log.debug("Personas upload finished.");
        }
    }

    /**
     * The getter to InitializationData.
     *
     * @param args to use as source.
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
     * Parse from Scraper enum to Zero ice model enum.
     *
     * @param sexo
     * @return Zero ice enum.
     */
    public static Sexo parseEnum(cl.ucn.disc.pdis.simplescraper.model.Persona.Sexo sexo) {

        // enum from Zero ice model.
        Sexo sexoZeroIce;

        // parsing ..
        if (sexo.toString().equals("VAR")) {
            // VAR from domain.ice model.
            sexoZeroIce = Sexo.VAR;
            return sexoZeroIce.values()[0];

        } else if (sexo.toString().equals("MUJ")) {
            // MUJ from domain.ice model.
            sexoZeroIce = Sexo.MUJ;
            return sexoZeroIce.values()[1];

        } else {
            // if you have not obtained records from nombrerutyfirma.com
            return null;

        }
    }

}
