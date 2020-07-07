/*
 * Copyright (c) 2020. Castillo - Condorcet - Pizarro Engineering Students.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cl.ucn.disc.pdis.simplescraper.dao;

import cl.ucn.disc.pdis.simplescraper.model.Functionary;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * The Test to SQLite.
 *
 * @author Charlie Condorcet.
 */
public final class StorageTest {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(StorageTest.class);

    /**
     * Testing de ORMLite + H2 (database).
     */
    @Test
    public void testDatabase() throws SQLException {

        // The database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:functionary";

        // Connection source: autoclose with the try/catch
        try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

            // Ctrate the table from the Functionary annotations
            TableUtils.createTableIfNotExists(connectionSource, Functionary.class);

            // The dao of Functionary
            Dao<Functionary, Long> daoFunctionary = DaoManager.createDao(connectionSource, Functionary.class);

            // New Functionary
            Functionary functionary = new Functionary(32234,"Andres Cervantes", "Profesor", "Informatica", "andres@ucn.cl", "9 99998888", "depto. informatica", "angamos 675");

            // Insert Functionary into the database
            int tuples = daoFunctionary.create(functionary);
            log.debug("Id: {}", functionary.getId());

            Assertions.assertEquals(1, tuples, "Save tuples !=1");

            // Get from db
            Functionary functionarydb = daoFunctionary.queryForId((long) functionary.getId());

            Assertions.assertEquals(functionary.getNombre(), functionarydb.getNombre(), "Names not equals!");
            Assertions.assertEquals(functionary.getCargo(), functionarydb.getCargo(), "Cargo not equals!");
            Assertions.assertEquals(functionary.getUnidad(), functionarydb.getUnidad(), "Unidad not equals!");

            // Search by rut: Select * FROM  'functionary' WHERE 'nombre' = 'Andres Cervantes'
            List<Functionary> functionaryListList = daoFunctionary.queryForEq("nombre", "Andres Cervantes");
            Assertions.assertEquals(1, functionaryListList.size(), "More than one Functionary!");

            // Not found by nombre
            Assertions.assertEquals(0, daoFunctionary.queryForEq("nombre", "Hola Mundo").size(), "Found somethings!");

        } catch (IOException e) {
            log.error("Error", e);
        }


    }

}
