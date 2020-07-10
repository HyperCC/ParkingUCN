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
 * @author Castillo - Condorcet - Pizarro.
 */
@DatabaseTable(tableName = "functionary")
public final class Functionary {

    /**
     * The id: Primary Key.
     */
    @DatabaseField(generatedId = true)
    private int id;

    /**
     * The web id from UCN directory.
     */
    @DatabaseField(canBeNull = false)
    private int webId;

    /**
     * The Nombre.
     */
    @DatabaseField(canBeNull = false)
    private String nombre;

    /**
     * The RUT.
     */
    @DatabaseField(canBeNull = true)
    private String rut;

    /**
     * The sexo.
     */
    @DatabaseField(canBeNull = true)
    private String sexo;

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
     * The work address.
     */
    @DatabaseField(canBeNull = true)
    private String direccionTrabajo;

    /**
     * The home address.
     */
    @DatabaseField(canBeNull = true)
    private String direccionCasa;

    /**
     * The City.
     */
    @DatabaseField(canBeNull = true)
    private String comuna;

    /**
     * Empty contructor; Default visivility + empty body.
     */
    public Functionary( ) {
        // nothing here.
    }

    /**
     * The constructor to the model
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
     */
    public Functionary(int webId, String nombre, String rut, String sexo, String cargo, String unidad, String email, String telefono, String oficina, String direccionTrabajo, String direccionCasa, String comuna) {
        this.webId = webId;
        this.nombre = nombre;
        this.rut = rut;
        this.sexo = sexo;
        this.cargo = cargo;
        this.unidad = unidad;
        this.email = email;
        this.telefono = telefono;
        this.oficina = oficina;
        this.direccionTrabajo = direccionTrabajo;
        this.direccionCasa = direccionCasa;
        this.comuna = comuna;
    }

    /**
     * Id getter
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * webId getter
     *
     * @return
     */
    public int getWebId() {
        return webId;
    }

    /**
     * nombre getter
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * RUT getter
     *
     * @return
     */
    public String getRut() {
        return rut;
    }

    /**
     * Sexo getter
     *
     * @return
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * cargo getter
     *
     * @return
     */
    public String getCargo() {
        return cargo;
    }
    /**
     *
     * @return
     */
    public String getUnidad() {
        return unidad;
    }
    /**
     * e-mail getter
     *
     * @return
     */
    public String getEmail() {
        return email;
    }
    /**
     * telefono getter
     *
     * @return
     */
    public String getTelefono() {
        return telefono;
    }
    /**
     * oficina getter
     *
     * @return
     */
    public String getOficina() {
        return oficina;
    }
    /**
     * DireccionTrabajo getter
     *
     * @return
     */
    public String getDireccionTrabajo() {
        return direccionTrabajo;
    }
    /**
     * DireccionCasa getter
     *
     * @return
     */
    public String getDireccionCasa() {
        return direccionCasa;
    }
    /**
     * Comuna getter
     *
     * @return
     */
    public String getComuna() {
        return comuna;
    }
}
