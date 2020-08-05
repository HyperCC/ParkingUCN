package cl.ucn.disc.pdis.appparkingucn;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
import com.zeroc.Ice.*;
import cl.ucn.disc.pdis.appparkingucn.dbinteraction.*;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.*;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String [] args = {"one","two"};

        try {

            updater(args);
        }catch (SQLException e){

        }

        Button busquedaMain = findViewById(R.id.Buscar);
        Button registrarMain = findViewById(R.id.Registrar);

        setUiTransition(busquedaMain, registrarMain);
    }

    private void setUiTransition(Button busquedaMain, Button registrarMain){

        busquedaMain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Buscar.class));
            }
        });

        registrarMain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistroVehiculo.class));
            }
        });
    }

    private void updater(String[] args) throws SQLException  {
        // Initialize the Communicator.
        try (Communicator communicator = Util.initialize(getInitializationData(args))) {

            String name = Contratos.class.getSimpleName();

            // Running in port 8080 same port that the Server.
            ObjectPrx theProxy = communicator.stringToProxy(name + ":tcp -z -t 15000 -p 8080");

            // Instance of Contratos from domain.ice
            ContratosPrx contratos = ContratosPrx.checkedCast(theProxy);

            // DB operations.
            //DbInteraction dbInteraction = new DbInteraction();

        }
    }

    private static InitializationData getInitializationData(String[] args) {

        // Properties
        final Properties properties = Util.createProperties(args);
        properties.setProperty("Ice.Package.model", "cl.ucn.disc.pdis.simplescraper.zeroice");

        // https://doc.zeroc.com/ice/latest/property-reference/ice-trace
        // properties.setProperty("Ice.Trace.Admin.Properties", "1");
        // properties.setProperty("Ice.Trace.Locator", "2");
        // properties.setProperty("Ice.Trace.Network", "3");
        // properties.setProperty("Ice.Trace.Protocol", "1");
        // properties.setProperty("Ice.Trace.Slicing", "1");
        // properties.setProperty("Ice.Trace.ThreadPool", "1");
        // properties.setProperty("Ice.Compression.Level", "9");
        //properties.setProperty("Ice.Plugin.Slf4jLogger.java", "cl.ucn.disc.pdis.simplescraper.zeroice.Slf4jLoggerPluginFactory");

        InitializationData initializationData = new InitializationData();
        initializationData.properties = properties;

        return initializationData;
    }
}