package com.example.isaias.prjlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnAceptar;
    private Button btnCancelar;
    private TextView lblAcceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Método para inicializar la actividad
        super.onCreate(savedInstanceState); //al sobreescrir un método siempre se debe llamar al método padre .
        setContentView(R.layout.activity_main); // <- esto indica cuál es la vista o Layout que se va a llamar por defecto.
        etUsername = (EditText)findViewById(R.id.etNombre);
        etPassword = (EditText)findViewById(R.id.etPasword);
        lblAcceso = (TextView) findViewById(R.id.lblAcceso);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        // Limpiar los campos.

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUsername.setText("");
                etPassword.setText("");
            }
        });



    }
}
