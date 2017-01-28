package com.example.isaias.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etNombre;
    private Button btnBoton1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Para apuntar un objeto a un elemento del diseño gráfico
        // usamos el findViewById

        etNombre = (EditText)findViewById(R.id.etNombre);
        btnBoton1 = (Button)findViewById(R.id.btn1);

        btnBoton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNombre.setText("Hola mundo");
            }
        });


    }
}
