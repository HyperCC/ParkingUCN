package cl.ucn.dics.pdis.appparkingucn;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}