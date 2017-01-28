package com.example.isaias.prjnotificaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnBoton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBoton1 = (Button)findViewById(R.id.btnBoton1);

        btnBoton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Para mostrar mensajes usamos el objeto Toast
                Toast mensaje1 = Toast.makeText(getApplicationContext(),"He pulsado el botón 1",Toast.LENGTH_SHORT);
                mensaje1.show();
            }
        });

    }

    public void ejecutar(View view){

        Toast mensaje1 = Toast.makeText(getApplicationContext(),"He pulsado el botón 2",Toast.LENGTH_SHORT);
        mensaje1.show();

    }
}
