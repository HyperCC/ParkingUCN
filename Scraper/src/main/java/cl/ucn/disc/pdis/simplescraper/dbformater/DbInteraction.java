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

import cl.ucn.disc.pdis.simplescraper.model.Persona;
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
 * @author Castillo - Condorcet - Pizarro.
 */
public final class DbInteraction {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(DbInteraction.class);

    /**
     * The database URL.
     */
    private String databaseURL;

    /**
     * The Connection Source.
     */
    private ConnectionSource connectionSource;

    /**
     * The Dao to Persona model.
     */
    private Dao<Persona, String> functionaryDao;

    /**
     * Constructor to initialize the database.
     */
    public DbInteraction() throws SQLException {

        // Use sqlite database to replace h2.
        databaseURL = "jdbc:sqlite:Database/funcionarios.db";

        // Create a connection source to our database.
        this.connectionSource = new JdbcConnectionSource(databaseURL);

        // Instance the DAO to Persona.
        functionaryDao = DaoManager.createDao(connectionSource, Persona.class);

        // if you need to create the ’accounts’ table make this call.
        TableUtils.createTableIfNotExists(connectionSource, Persona.class);
    }

    /**
     * Make format to save the Functionaries in database.
     *
     * @param webId
     * @param nombre
     * @param rut
     * @param sexo
     * @param cargo
     * @param unidad
     * @param email
     * @param telefono
     * @param oficina
     * @param direccionTrabajo
     * @param direccionCasa
     * @param comuna
     * @return
     */

    public boolean formatToFunctionary(int webId, String nombre, String rut, String sexo, String cargo, String unidad,
                                       String email, String telefono, String oficina, String direccionTrabajo,
                                       String direccionCasa, String comuna) {

        // Save variables like null if is empty.
        nombre = EmptyToNull(nombre);
        rut = EmptyToNull(rut);
        sexo = EmptyToNull(sexo);
        cargo = EmptyToNull(cargo);
        unidad = EmptyToNull(unidad);
        email = EmptyToNull(email);
        telefono = EmptyToNull(telefono);
        oficina = EmptyToNull(oficina);
        direccionTrabajo = EmptyToNull(direccionTrabajo);
        direccionCasa = EmptyToNull(direccionCasa);
        comuna = EmptyToNull(comuna);

        // Add new valid persona to database.
        Persona persona = new Persona(
                webId,
                nombre,
                rut,
                sexo,
                cargo,
                unidad,
                email,
                telefono,
                oficina,
                direccionTrabajo,
                direccionCasa,
                comuna);

        // Duplicaded Functionaries.
        try {
            this.functionaryDao.createIfNotExists(persona);

        } catch (SQLException e) {
            log.error("New Persona {} no added. Details: {}", nombre, e.getMessage());
            return false;
        }

        log.debug("Added the new persona to database.");
        return true;
    }

    /**
     * Change the empty data for null.
     *
     * @param var
     * @return a whole data or null.
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
     * Get name of Persona by id.
     *
     * @param id
     * @return the name by id.
     * @throws SQLException
     */
    public String GetFunctionaryById(int id) throws SQLException {

        String val = Integer.toString(id);

        // Build a query for get results from funcionarios.db
        QueryBuilder<Persona, String> consulta = this.functionaryDao.queryBuilder();
        Persona persona = consulta.where().eq("id", val).queryForFirst();

        return persona.getNombre();
    }

    /**
     * Get length of records from DB.
     *
     * @return length of records from DB.
     * @throws SQLException
     */
    public long GetLengthFunctionary() throws SQLException {

        // Build a query for get the length from funcionarios.db
        QueryBuilder<Persona, String> consulta = this.functionaryDao.queryBuilder();
        long lengthReg = consulta.countOf();

        return lengthReg;
    }

}
