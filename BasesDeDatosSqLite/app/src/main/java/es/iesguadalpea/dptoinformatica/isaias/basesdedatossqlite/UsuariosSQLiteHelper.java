package es.iesguadalpea.dptoinformatica.isaias.basesdedatossqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by isaias on 27/01/17.
 */

public class UsuariosSQLiteHelper extends SQLiteOpenHelper{

    String sqlCrear = "CREATE TABLE tbProfesores (" +
            "DNI TEXT PRIMARY KEY," +
            "Nombre TEXT, " +
            "Apellidos TEXT, " +
            "Curso TEXT, " +
            "Asignaturas TEXT, " +
            ")";

    //Constructor
    public UsuariosSQLiteHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version){
        super(context,nombre,factory,version);
        //cuando creemos un objeto basado en esta clase pasaremos:
        //El contexto --> contexto de la aplicación o una referencia a la actividad principal
        //El nombre   --> Será el nombre de la base de datos.
        //El factory  --> objeto de tipo CursorFactory que nosotros lo pasaremos como null
        //El version  --> que será una versión de la base de datos.

        //Cuando creemos el objeto podrán ocurrir tres cosas:
        //  1.- Si la base de datos ya existe entonces se conecta a la base de datos.
        //  2.- Si la base de datos ya existe pero es anterior a la creada, se llama al método
        //      onUpgrade que lo que hace es actualizar la base de datos (borra y crea de nuevo)
        //  3.- Si la base de datos no existe, entonces llama al método onCreate()
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // aquí ejecutamos la instrucción que permite crear la tabla.
        db.execSQL(sqlCrear);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Para el Upgrade se puede hacer de varias formas.
        // La más tonta sería eliminar la tabla y crearla de nuevo.
        // Este método se lanza cuando es necesaria una actualización de la estructura de la base de datos
        // recuerdo que el upgrade sería para la primera vez que creamos nuestra tabla.

        //borramos la tabla antigua.
        db.execSQL("DROP TABLE IF EXIST tbProfesores");
        // y ahora la volvemos a crear.
        db.execSQL(sqlCrear);
    }
}
