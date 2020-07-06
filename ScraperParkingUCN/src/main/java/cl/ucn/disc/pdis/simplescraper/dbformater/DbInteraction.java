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

package cl.ucn.disc.pdis.simplescraper.dbformater;

import cl.ucn.disc.pdis.simplescraper.App;
import cl.ucn.disc.pdis.simplescraper.model.Functionary;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Class to format the data and insert to db.
 *
 * @author Charlie Condorcet.
 */
public final class DbInteraction {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(App.class);

    /**
     * The database URL.
     */
    private String databaseURL;

    /**
     * The Connection Source.
     */
    private ConnectionSource connectionSource;

    /**
     * The Dao to Functionary model.
     */
    private Dao<Functionary, String> functionaryDao;

    /**
     * Constructor to initializate the database.
     */
    public DbInteraction() throws SQLException {

        // Use sqlite database to replace h2.
        databaseURL = "jdbc:sqlite:Database/funcionarios.db";

        // Create a connection source to our database.
        this.connectionSource = new JdbcConnectionSource(databaseURL);

        // Instance the DAO.
        functionaryDao = DaoManager.createDao(connectionSource, Functionary.class);

        // if you need to create the ’accounts’ table make this call.
        TableUtils.createTableIfNotExists(connectionSource, Functionary.class);
    }

    /**
     * Formater to save data in database.
     *
     * @param nombre
     * @param cargo
     * @param unidad
     * @param email
     * @param telefono
     * @param oficina
     * @param direccion
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public boolean formatToDatabase(int webId, String nombre, String cargo, String unidad, String email, String telefono,
                                    String oficina, String direccion) {

        // Save variables like null if is empty.
        cargo = EmptyToNull(cargo);
        unidad = EmptyToNull(unidad);
        email = EmptyToNull(email);
        telefono = EmptyToNull(telefono);
        oficina = EmptyToNull(oficina);
        direccion = EmptyToNull(direccion);

        // Add new valid functionary to database.
        Functionary functionary = new Functionary(
                webId,
                nombre,
                cargo,
                unidad,
                email,
                telefono,
                oficina,
                direccion);

        // Duplicaded Functionaries.
        try {
            this.functionaryDao.createIfNotExists(functionary);

        } catch (SQLException e) {
            log.error("New Functionary {} no added. Details: {}", nombre, e.getMessage());
            return false;
        }

        log.debug("Added the new functionary to database.");
        return true;
    }

    /**
     * Change the empty data for null.
     *
     * @param var
     * @return
     */
    public String EmptyToNull(String var) {
        return var.isEmpty() ? null : var;
    }

    /**
     * End the database connection.
     *
     * @throws IOException
     */
    public void CloseDBConnection() throws IOException {
        this.connectionSource.close();
    }

    /**
     * Get name of Functionary by id.
     *
     * @param id
     * @return the name by id.
     * @throws SQLException
     */
    public String GetFunctionaryById(int id) throws SQLException {

        String val = Integer.toString(id);
        QueryBuilder<Functionary, String> consulta = this.functionaryDao.queryBuilder();
        Functionary functionary = consulta.where().eq("id", val).queryForFirst();

        return functionary.getNombre();
    }

    /**
     * Get length of records from DB.
     *
     * @return length of records from DB.
     * @throws SQLException
     */
    public long GetLengthFunctionary() throws SQLException {

        QueryBuilder<Functionary, String> consulta = this.functionaryDao.queryBuilder();
        long lengthReg = consulta.countOf();
        return lengthReg;
    }

}
