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
