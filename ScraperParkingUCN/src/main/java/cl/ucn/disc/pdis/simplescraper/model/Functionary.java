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
 * The Functionary model class.
 *
 * @author Charlie Condorcet.
 */
@DatabaseTable(tableName = "functionary")
public final class Functionary {

    /**
     * The id: Primary Key.
     */
    @DatabaseField(generatedId = true)
    private int id;

    /**
     * The Nombre.
     */
    @DatabaseField(canBeNull = false)
    private String nombre;

    /**
     * The Cargo.
     */
    @DatabaseField(canBeNull = true)
    private String cargo;

    /**
     * The Unidad.
     */
    @DatabaseField(canBeNull = true)
    private String unidad;

    /**
     * The E-Mail.
     */
    @DatabaseField(canBeNull = true)
    private String email;

    /**
     * The Telefono.
     */
    @DatabaseField(canBeNull = true)
    private String telefono;

    /**
     * The Oficina.
     */
    @DatabaseField(canBeNull = true)
    private String oficina;

    /**
     * The Direccion.
     */
    @DatabaseField(canBeNull = true)
    private String direccion;

    /**
     * Empty contructor; Default visivility + empty body.
     */
    public Functionary() {
        // nothing here.
    }

    /**
     * Principal Constructor.
     *
     * @param nombre
     * @param cargo
     * @param unidad
     * @param email
     * @param telefono
     * @param oficina
     * @param direccion
     */
    public Functionary(String nombre, String cargo, String unidad, String email, String telefono, String oficina, String direccion) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.unidad = unidad;
        this.email = email;
        this.telefono = telefono;
        this.oficina = oficina;
        this.direccion = direccion;
    }

    /**
     * Getter to ID.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter to Nombre.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter to Cargo.
     *
     * @return cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Getter to Unidad.
     *
     * @return unidad
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * Getter to E-mail.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter to Telefono.
     *
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Getter to Oficina.
     *
     * @return oficina
     */
    public String getOficina() {
        return oficina;
    }

    /**
     * Getter to Direccion.
     *
     * @return direccion
     */
    public String getDireccion() {
        return direccion;
    }

}
