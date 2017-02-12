package es.iesguadalpea.dptoinformatica.isaias.contactosaplicacion;
/**
 * Created by isaias on 10/02/17.
 */
import android.content.ContentProvider;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edNombre;
    private EditText edTelefono;
    private EditText edNombreBuscar;
    private Button btnInsertar;
    private DatabaseManager manager;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private SimpleCursorAdapter adapter2;
    private ListView listaContactos;
    private Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ------- instanciamos elementos del layout y de mi clase DatabaseManager
        manager = new DatabaseManager(this);
        edNombre = (EditText) findViewById(R.id.edNombre);
        edTelefono = (EditText) findViewById(R.id.edTelefono);
        btnInsertar = (Button) findViewById(R.id.btnAlmacenar);
        listaContactos = (ListView)findViewById(R.id.lvContactos);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        edNombreBuscar = (EditText) findViewById(R.id.edBusqueda);

        //Al iniciar la aplicación cargo en el listview los contactos con el método
        // que me he creado llamado recargoListview()
        buscoTodosContactos();

        //ahora implementamos los click de los dos botones
        //Para no tener mucho código aquí, llamo a los onclicklistener pero el
        //método OnClick lo implemento abajo.

        btnBuscar.setOnClickListener(this);
        btnInsertar.setOnClickListener(this);

        // el método setonlongclicklistener se utiliza cuando hacemos una pulsación larga sobre un elemento.
        //Lo voy a utilizar para que al pulsar durante más tiempo sobre un elemento del listview me aparezca
        //un AlertDialog para permitir borrar de la bd dicho elemento.
        listaContactos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // de la vista me quedo con la columna primera que era el text1
                TextView tv = (TextView)view.findViewById(android.R.id.text1);
                final String nombreContacto = tv.getText().toString(); // este sería el nombre del contacto pulsado

                //Ahora sólo me queda eliminarlo con un Alert , quitarlo de la bd y refrescar el Listview

                AlertDialog.Builder diagInsertarContacto = new AlertDialog.Builder(MainActivity.this);
                diagInsertarContacto.setTitle("Eliminar Contactos"); // título de la alerta
                diagInsertarContacto.setMessage("¿ Deseas eliminar el contacto ?"); //mensaje que le mostraremos
                diagInsertarContacto.setCancelable(false); //para que tenga que contestar obligatoriamente
                diagInsertarContacto.setIcon(android.R.drawable.ic_delete);

                //ahora programamos si acepto o no acepto.
                diagInsertarContacto.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        manager.eliminar(nombreContacto);
                        buscoTodosContactos();
                        Toast.makeText(getApplicationContext(),"Contacto eliminado correctamente",Toast.LENGTH_SHORT).show();
                    }
                });
                diagInsertarContacto.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //si pulsa en cancelar no hago nada.
                    }
                });
                diagInsertarContacto.show();  // al igual que el Toast hay que mostrarlo

                return false;
            }
        });
    }


    public void dialogoInsertarContacto(){

        //Creo un diálogo para cuando pulsemos en agregar contacto.
        //Los diálogos se crean usando AlerDialog
        //creo el diálogo de alerta
        AlertDialog.Builder diagInsertarContacto = new AlertDialog.Builder(this);

        diagInsertarContacto.setTitle("Inserción de Contactos"); // título de la alerta
        diagInsertarContacto.setMessage("¿ Deseas insertar el contacto ?"); //mensaje que le mostraremos
        diagInsertarContacto.setCancelable(false); //para que tenga que contestar obligatoriamente
        diagInsertarContacto.setIcon(android.R.drawable.ic_menu_edit); //icono
        //ahora programamos si acepto o no acepto.
        diagInsertarContacto.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Si llego aquí es porque he pulsado en confirmar para almacenar al cliente
                //llamo al método que me almacena el cliente. Le paso los editext que contienen
                // el nombre y el teléfono. El método almacenarContacto se encargará de guardarlo en la BD.
                almacenarContacto(edNombre,edTelefono);
            }
        });
        diagInsertarContacto.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //si pulsa en cancelar no hago nada.
            }
        });
        diagInsertarContacto.show();  // al igual que el Toast hay que mostrarlo.
    }

    public void buscoTodosContactos(){

        String[] from = new String[] { manager.CN_NOMBRE,manager.CN_TELEFONO};
        int[] to = new int[] {android.R.id.text1,android.R.id.text2};
        cursor = manager.getContactos();
        // Diseño el adapter. Me baso en uno ya creado por google llamado SimpleCursorAdapter.
        // Recuerdo que para usar esto debemos tener una columna llamada _id o puede dar error si no está.
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item,
                cursor,
                from,
                to,
                0);
        listaContactos.setAdapter(adapter);
    }

    public void buscoUnContacto(String nombreContacto){
        String[] from = new String[] { manager.CN_NOMBRE,manager.CN_TELEFONO};
        int[] to = new int[] {android.R.id.text1,android.R.id.text2};
        //hacemos la consulta con getBscaContacto y lo almaceno en un cursor
        Cursor nuevocursor = manager.getBuscaContacto(nombreContacto);
        //Ahora no vuelvo a diseñar el adapter. Basta cambiar el cursor con chageCursor
        adapter.changeCursor(nuevocursor);
        //por último le indico al Listview listacontactos cuál será ahora el adaptador(acomodador) que trae los datos
        listaContactos.setAdapter(adapter);
    }

    public void almacenarContacto(EditText nombre, EditText telefono){
        if (manager.insertar(nombre.getText().toString(),telefono.getText().toString()) != -1 ) {
            Toast.makeText(getApplicationContext(),"Contacto insertado correctamente",Toast.LENGTH_LONG).show();
            nombre.setText(null);
            telefono.setText(null);
            buscoTodosContactos();
        }
    }

    @Override
    public void onClick(View v) {
        //aquí entrarían cualquier pulsación de un botón o de cualquier cosa donde haga click
        //capturo qué botón ha sido pulsado
        if (v.getId() == R.id.btnBuscar){
            //Cuando pulso en Buscar llamo al método que realiza la búsqueda y la muestra en el Listview
            // Si busco estando vacío lo que quiero buscar muestro todos los contactos
            if (edNombreBuscar.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Mostrando todos los contactos",Toast.LENGTH_SHORT).show();
                buscoTodosContactos();
            } else {
                //si escribo algo, entonces busco ese nombre
                Toast.makeText(getApplicationContext(),"Buscando el nombre del contacto",Toast.LENGTH_SHORT).show();

                buscoUnContacto(edNombreBuscar.getText().toString());
            }

        } else if (v.getId() == R.id.btnAlmacenar) {
            //Cuando pulse en almacenar, llamo al método que me muestra el dialogo de alerta y me hace unas preguntas.
            if (edNombre.getText().toString().equals("") || edTelefono.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Los campos de nombre y Tfno han de estar rellenos",Toast.LENGTH_SHORT).show();
            }else{
                dialogoInsertarContacto();
            }


        } else if (v.getId() == R.id.lvContactos){
            //obtengo qué línea he seleccionado.


        }



    }


}