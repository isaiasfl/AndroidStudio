package es.iesguadalpea.dptoinformatica.isaias.pasoinformacionactividades;

/**
 * Created by isaias on 24/01/17.
 */

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends  AppCompatActivity{
    private TextView txtNombre;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtNombre = (TextView)findViewById(R.id.txtInformacion);
        // para recoger la informacion de la otra actividad necesito:
        // una Intencion y con el metodo getIntent() capturarla
        // Bundle y rellenarlo del metodo getExtras() de la intencion
        Intent intent = getIntent();
        Bundle extraEntreActividades = intent.getExtras();
        if (extraEntreActividades != null){
            String texto = extraEntreActividades.getString("Nombre");
            txtNombre.setText("Bienvenido D/Dña: " + texto);
        }

    }
}



