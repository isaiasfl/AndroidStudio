package es.iesguadalpea.dptoinformatica.isaias.basesdedatossqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.attr.button;

public class InformacionProfesorado extends AppCompatActivity {

    private Button btnVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_profesorado);
        btnVolver = (Button)findViewById(R.id.btnProfesores);
    }


    public void volver(View v){
        Intent intent = new Intent(v.getContext(),MainActivity.class);
        startActivity(intent);
    }
}
