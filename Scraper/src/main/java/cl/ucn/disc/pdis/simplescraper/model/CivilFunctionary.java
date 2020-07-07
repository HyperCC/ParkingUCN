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

package cl.ucn.disc.pdis.simplescraper.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The CivilFunctionary model class.
 *
 * @author Castillo - Condorcet - Pizarro.
 */
@DatabaseTable(tableName = "civil_functionary")
public final class CivilFunctionary {

    /**
     * The id: Primary Key.
     */
    @DatabaseField(generatedId = true)
    private int id;

    /**
     * The Nombre.
     */
    @DatabaseField()
    private String nombre;

    /**
     * The RUT.
     */
    @DatabaseField()
    private String rut;

    /**
     * The Sexo.
     */
    @DatabaseField()
    private String sexo;

    /**
     * The Direccion.
     */
    @DatabaseField()
    private String direccion;

    /**
     * The Comuna.
     */
    @DatabaseField()
    private String comuna;

    /**
     * Empty contructor; Default visivility + empty body.
     */
    public CivilFunctionary() {
        // nothing here.
    }

    /**
     * Constructor Principal
     *
     * @param nombre
     * @param rut
     * @param sexo
     * @param direccion
     * @param comuna
     */
    public CivilFunctionary(String nombre, String rut, String sexo, String direccion, String comuna) {
        this.nombre = nombre;
        this.rut = rut;
        this.sexo = sexo;
        this.direccion = direccion;
        this.comuna = comuna;
    }

    /**
     * Get id from DB
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Get nombre from DB
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Get rut from DB
     *
     * @return rut
     */
    public String getRut() {
        return rut;
    }

    /**
     * Get sexo from DB
     *
     * @return sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Get direccion from DB
     *
     * @return direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Get comuna from DB
     *
     * @return comuna
     */
    public String getComuna() {
        return comuna;
    }
}
