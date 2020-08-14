package cl.ucn.disc.pdis.appparkingucn;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.pdis.appparkingucn.zeroice.ZeroIce;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.ContratosPrx;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Persona;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class ContratosTest {

    public static Logger logger = LoggerFactory.getLogger(ContratosTest.class);


    @Test
    public void verificarPersona() {

        ZeroIce ice = new ZeroIce();

        ice.start();

        ContratosPrx contratosPrx = ice.getContratosPrx();

        Persona persona = contratosPrx.obtenerPersona("5.808.054-3");

        logger.info("Waiting for person: 193982336");
        assertNotNull(persona);
        logger.debug("DONE: Persona found and returned! -> {} {} {}",
                persona.nombre,
                persona.rut,
                persona.sexo);
    }
}
