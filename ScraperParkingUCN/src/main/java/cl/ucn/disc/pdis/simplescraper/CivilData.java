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

        DbInteraction DB = new DbInteraction();

        try {

            for (int i = 0; i < DB.GetLengthFunctionary(); i++) {

                String actualUrl = "https://www.nombrerutyfirma.com/buscar?term=" + DB.GetFunctionaryById(i+1);
                Document document = null;

                try {

                    document = Jsoup.connect(actualUrl).get();
                } catch (SocketTimeoutException e) {

                    log.error("Timeout for http request. Details: {}", e.getMessage());
                }

                ArrayList<String> variablesCompletas = new ArrayList<>();

                //  get all data with 'td' tag
                Elements enteraPo =document.getElementsByTag("td");

                // parting of 'td' tags
                for (int j = 0; j < 5; j++) {

                    variablesCompletas.add(enteraPo.first().text().toString());
                    enteraPo.remove(enteraPo.first());
                }

                // Here we already got all the data from nombrerutyfirma separated by position.
                /*
                for (int j = 0; j < variablesCompletas.size(); j++) {

                    // All data of one person.
                    System.out.println(variablesCompletas.get(j));
                }*/

                // Data extracted from telephone directory.
                String nombre = variablesCompletas.get(0);
                String rut = variablesCompletas.get(1);
                String sexo = variablesCompletas.get(2);
                String direccion = variablesCompletas.get(3);
                String comuna = variablesCompletas.get(4);

                // Add new valid functionary to database.
                boolean notExistInDb = DB.formatPublicInfo(nombre, rut, sexo, direccion, comuna);

                if (notExistInDb) {

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

        } catch (Exception e) {

            e.printStackTrace();
        }

        //connectionSource.close();
        log.info("End of insertions.");
        DB.CloseDBConnection();
    }
}
