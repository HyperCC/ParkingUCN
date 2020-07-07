package cl.ucn.disc.pdis.simplescraper;

import cl.ucn.disc.pdis.simplescraper.dbformater.DbInteraction;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

/**
 * App to do scraping at nombrerutyfirma.cl
 *
 * @author Castillo - Condorcet - Pizarro.
 */
public class CivilData {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(CivilData.class);

    /**
     * Principal Main Class
     *
     * @param args
     * @throws IOException
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException, SQLException {

        // The singleton database instance.
        DbInteraction DB = new DbInteraction();

        for (int i = 0; i < DB.GetLengthFunctionary(); i++) {

            String actualUrl = "https://www.nombrerutyfirma.com/buscar?term=" + DB.GetFunctionaryById(i + 1);
            Document document = null;

            // Timeout in server.
            try {
                document = Jsoup.connect(actualUrl).get();

            } catch (SocketTimeoutException e) {
                log.error("Timeout for http request. Details: {}", e.getMessage());

            }

            ArrayList<String> allTrObtained = new ArrayList<>();

            // Get all data with 'td' tag
            // Must be only 5, if there are several results with the same name.
            Elements trLabels = document.getElementsByTag("td");

            // Parting of 'td' tags
            for (int j = 0; j < 5; j++) {

                try {
                    // Get the content from the first 'td'.
                    allTrObtained.add(trLabels.first().text().toString());

                    // Delete the content from the 'td' stack.
                    trLabels.remove(trLabels.first());

                }catch (NullPointerException e) {
                    log.debug("No data found. Details: {}",e.getMessage());
                    allTrObtained.add("");
                }

            }

            // Add new valid CivilFunctionary to database.
            boolean notExistInDb = DB.formatToCivilFunctionary(
                    // Nombre.
                    allTrObtained.get(0),
                    // Rut.
                    allTrObtained.get(1),
                    // Sexo.
                    allTrObtained.get(2),
                    // Direccion.
                    allTrObtained.get(3),
                    // ComunaCiudad
                    allTrObtained.get(4));

            // Check if the new functionary is added correctly.
            if (notExistInDb) {

                // Random variable to interleave time.
                Random random = new Random();

                // Time to wait not to do DDoS.
                try {
                    Thread.sleep(1000 + random.nextInt(1000));

                } catch (InterruptedException e) {
                    log.error("Thread is interrupted either before or during the activity. Details: {}", e.getMessage());

                }

            } else {
                log.info("The new functionary is not added.");

            }

        }

        //connectionSource.close();
        log.info("End of insertions to CivilFunctionary.");
        DB.CloseDBConnection();

    }
}
