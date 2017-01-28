package es.iesguadalpea.dptoinformatica.isaias.pasoinformacionactividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnContinuar;
    private EditText etTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnContinuar = (Button)findViewById(R.id.btnContinuar);
        etTexto = (EditText)findViewById(R.id.edNombre);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // aquí debemos de indicar la acción
                // de pasar entre actividades
                // Necesito una INTENCION (Intent)
                //Intent necesita Intent(Actividad_donde_estoy,xxxxx.Class a donde voy)
                Intent siguiente = new Intent(MainActivity.this,Main2Activity.class);
                //para enviar informacion entre Activity usarmos
                // el método putextra de las Intenciones.
                siguiente.putExtra("Nombre",etTexto.getText().toString());
                startActivity(siguiente);
            }
        });


    }
}
