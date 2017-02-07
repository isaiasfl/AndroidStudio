package es.iesguadalpea.dptoinformatica.isaias.basesdedatossqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnInsertar;
    private EditText etDni;
    private EditText etNombre;
    private EditText etApellidos;
    private Spinner spnCursos;
    private DataBaseManager manager;
    private Button btnProfesores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProfesores = (Button)findViewById(R.id.btnProfesores);
        manager = new DataBaseManager(this);
        btnInsertar = (Button)findViewById(R.id.btnInsertar);
        etDni = (EditText)findViewById(R.id.etDni);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etApellidos = (EditText)findViewById(R.id.etApellidos);
        spnCursos = (Spinner)findViewById(R.id.spnCursos);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Cursos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCursos.setAdapter(adapter);


        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                // aquí llamo al método que introduzca los valores.
               if (manager.insertar(etDni.getText().toString(),
                        etNombre.getText().toString(),
                        etApellidos.getText().toString(),
                        spnCursos.getSelectedItem().toString(),
                        "Asignatura1"
                        ) != -1) {
                   Toast.makeText(getApplicationContext(),"Usuario insertado correctamente",
                           Toast.LENGTH_SHORT).show();

                }else {
                   Toast.makeText(getApplicationContext(), "Usuario NO insertado",
                           Toast.LENGTH_SHORT).show();
               }
               */

               // Toast.makeText(getApplicationContext(), spnCursos.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                if (manager.insertar("1111A","Antonio","Perez","1cfgs","aso,iso") != -1) {
                    Toast.makeText(getApplicationContext(),
                            "Correcto",
                            Toast.LENGTH_SHORT).show();
                };

                if (manager.insertar("1111B","Carlos","Sánchez","1cfgs","aso,iso") != -1) {
                    Toast.makeText(getApplicationContext(),
                            "Correcto",
                            Toast.LENGTH_SHORT).show();
                };


                if (manager.eliminar("1111A") != 0 ){
                    Toast.makeText(getApplicationContext(),
                            "Eliminación correcta",
                            Toast.LENGTH_SHORT).show();
                }

                if (manager.modificarNombre("1111B","Andresito") != 0 ){
                    Toast.makeText(getApplicationContext(),
                            "Usuario cambiado por Andresito",
                            Toast.LENGTH_SHORT).show();
                }





            }
        });




    }


    public void irModificarCursos(View v){
        Intent intent = new Intent(v.getContext(),ModificarCursos.class);
        startActivity(intent);
    }










    public void mostrarProfesores(View v){
        Intent intent = new Intent(v.getContext(),InformacionProfesorado.class);
        startActivityForResult(intent,0);
    }
}
