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

package cl.ucn.disc.pdis.simplescraper;

import cl.ucn.disc.pdis.simplescraper.dbformater.DbInteraction;
import cl.ucn.disc.pdis.simplescraper.model.Persona;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Principal App to do scraping.
 *
 * @author Castillo - Condorcet - Pizarro.
 */
public class Scraper {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(Scraper.class);

    /**
     * Principal Main class.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, SQLException {

        /*
        El "cod" o el numero id al final de la url, en primer semestre de 2020, no sobrepasa los 29800.
        Esto ultimo verificado buscando ultimos profesores agregados este semestre con id 29700 aprox.
        */

        // Variable to create registers in .txt.
        PrintWriter printWriter = new PrintWriter("records.txt", "UTF-8");

        // The database instance.
        DbInteraction theDatabase = new DbInteraction();

        log.info("Initialization of scraping..");

        // Auxiliaries.
        int id = 1;
        int maxCod = 300;

        for (int i = 1; i < maxCod; i++) {

            // Connection to directory ucn.
            Document docDirectoryUcn = null;

            // try to connect, possible timeout in server.
            try {
                docDirectoryUcn = Jsoup.connect("http://online.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod=" + i).get();

            } catch (SocketTimeoutException e) {
                log.error("Timeout for http request. Details: {}", e.getMessage());

            }

            String nombre = "";

            // Verify the index value. The COD may not contain data.
            try {
                nombre = docDirectoryUcn.getElementById("lblNombre").text();

            } catch (NullPointerException e) {
                log.error("Value null for timout recent. Details: {}", e);
                nombre = "";

            }

            // if is data founded.
            if (!nombre.isEmpty()) {

                // Get variables from URL.
                String cargo = docDirectoryUcn.getElementById("lblCargo").text();
                String unidad = docDirectoryUcn.getElementById("lblUnidad").text();
                String email = docDirectoryUcn.getElementById("lblEmail").text();
                String telefono = docDirectoryUcn.getElementById("lblTelefono").text();

                // Format to phone number.
                if (!telefono.isEmpty()) {
                    telefono = telefono.substring(5, telefono.length());

                }

                String oficina = docDirectoryUcn.getElementById("lblOficina").text();
                String direccionTrabajo = docDirectoryUcn.getElementById("lblDireccion").text();


                Document docNomRutFirm = null;

                // Try to connect with nombrerutyfirma.com, possible timeout in server.
                try {
                    docNomRutFirm = Jsoup.connect("https://www.nombrerutyfirma.com/buscar?term=" + nombre).get();

                } catch (SocketTimeoutException e) {
                    log.error("Timeout for http request. Details: {}", e.getMessage());

                }

                ArrayList<String> allTrObtained = new ArrayList<>();

                // Get all data with 'td' tag
                // Must be only 5, if there are several results with the same name.
                Elements trLabels = docNomRutFirm.getElementsByTag("td");

                // Parting of 'td' tags
                for (int j = 0; j < 5; j++) {

                    try {
                        // Get the content from the first 'td'.
                        allTrObtained.add(trLabels.first().text().toString());

                        // Delete the content from the 'td' stack.
                        trLabels.remove(trLabels.first());

                    } catch (NullPointerException e) {
                        log.debug("No data found. Details: {}", e.getMessage());
                        allTrObtained.add("");
                    }
                }

                String nombreOrdenado = allTrObtained.get(0);
                String rut = allTrObtained.get(1);


                // Parse the sexo from String to Sexo.
                String sexo = allTrObtained.get(2);
                Persona.Sexo sexoEnum;

                if (sexo.equals("VAR")) {
                    sexoEnum = Persona.Sexo.VAR;
                } else if (sexo.equals("MUJ")) {
                    sexoEnum = Persona.Sexo.MUJ;
                } else {
                    sexoEnum = null;
                }

                String direccionCasa = allTrObtained.get(3);
                String comuna = allTrObtained.get(4);

                // Add the Nombre in format UTF-8 from nombrerutyfirma.
                nombre = nombreOrdenado.equals("") ? nombre : nombreOrdenado;

                // Concatenation of Persona data.
                StringBuilder sbPersona = new StringBuilder();
                sbPersona.append(id).append(",")
                        // The id original from the web contacts.
                        .append(i).append(",")
                        .append(nombre).append(",")
                        .append(rut).append(",")
                        // Para el txt se recibe el sexo como String.
                        .append(sexo).append(",")
                        .append(cargo).append(",")
                        .append(unidad).append(",")
                        .append(email).append(",")
                        .append(telefono).append(",")
                        .append(oficina).append(",")
                        .append(direccionTrabajo).append(",")
                        .append(direccionCasa).append(",")
                        .append(comuna);

                log.debug("New identified: {}", sbPersona.toString());

                // Add new valid functionary to database.
                boolean notExistInDb = theDatabase.formatToPersona(
                        i,
                        nombre,
                        rut,
                        sexoEnum,
                        cargo,
                        unidad,
                        email,
                        telefono,
                        oficina,
                        direccionTrabajo,
                        direccionCasa,
                        comuna);

                // Check if the new functionary is added. The DB and csv must be same.
                if (notExistInDb) {

                    // Add new valid functionary to csv file.
                    printWriter.println(sbPersona.toString());

                    // Random variable to interleave time.
                    Random random = new Random();

                    // Time to wait not to do DDoS.
                    try {
                        Thread.sleep(1000 + random.nextInt(1000));

                    } catch (InterruptedException e) {
                        log.error("Thread is interrupted either before or during the activity. Details: {}", e.getMessage());

                    }

                    // ID real to csv file.
                    id++;

                } else {
                    log.info("The new functionary is not added.");

                }

            }

            log.info("No result found for the searched Cod.");

        }

        // End of record insertion.
        printWriter.close();

        //connectionSource.close();
        log.info("End of insertions.");
        theDatabase.closeDbConnection();

    }

}
