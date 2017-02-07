package es.iesguadalpea.dptoinformatica.isaias.basesdedatossqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by isaias on 30/01/17.
 */

public class DataBaseManager {
    //clase que se encarga del esquema de la base de datos y de las operaciones
    //insertar, crear, borrar, eliminar, actualizar, etc...

    public static final String TABLE_NAME ="profesores";
    //ahora el nombre de los campos.
    public static final String CN_DNI = "DNI";
    public static final String CN_NOMBRE = "nombre";
    public static final String CN_APELLIDOS = "apellidos";
    public static final String CN_CURSO = "curso";
    public static final String CN_ASIGNATURAS = "asignaturas";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "
            + CN_DNI + " TEXT PRIMARY KEY, "
            + CN_NOMBRE + " TEXT NOT NULL, "
            + CN_APELLIDOS + " TEXT NOT NULL, "
            + CN_CURSO + " TEXT, "
            + CN_ASIGNATURAS + " TEXT ) ;";

    private UsuariosSQLiteHelper helper;
    private SQLiteDatabase db;

    //CONSTRUCTOR **********************************
    public DataBaseManager(Context contexto) {

        //CREO UNA INSTANCIA DE LA CLASE UsuariosSQLITEHELPER
        helper = new UsuariosSQLiteHelper(contexto);
        //ahora me creo la base de datos llamando a SQLiteDatabase.
        db = helper.getWritableDatabase(); //si la base de datos no existe
        // getwritableDatabase la crea y la devuelve en modo escritura.
        // si ya exite, sólamente la devuelve.
    }

    public long insertar(String dni, String nombre, String apellidos, String curso, String asignaturas){
        // INSERCIÓN DE VALORES CON EL MÉTODO INSERT DE ANDROID
        //db.insert(necesita la tabla, NullColumnHack , ContentValues )
        // NullColumnHack --> Añadido para hacer compatible sqlite con otras bases de datos.
        // en sqlite el NullColumnHack es el campo que puede ser nulo (por ejemplo el campo asignaturas
        // la mayoría de las veces ponemos null
        ContentValues valores = new ContentValues();
        valores.put(CN_DNI, dni);
        valores.put(CN_NOMBRE, nombre);
        valores.put(CN_APELLIDOS, apellidos);
        valores.put(CN_CURSO, curso);
        valores.put(CN_ASIGNATURAS, asignaturas);


        return db.insert(TABLE_NAME,null,valores); //<-- si devuelve -1 es que ha habido un problema.

    }

    public void insertar2(String dni, String nombre, String apellidos, String curso, String asignaturas){

        //insert into xxxx values xxxx
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('" + dni + "','" + nombre + "','" + apellidos + "','" );
    }


    public int eliminar(String dni ){
        //Método para eliminar por dni.
        //Delete from tabla where DNI='dni' ;
        return db.delete(TABLE_NAME,CN_DNI+ "=?",new String[]{dni});


    }

    public void eliminarMultiple(String dn1,String dn2){

        db.delete(TABLE_NAME,CN_DNI + "=(?,?)",new String[]{dn1,dn2});
    }


    public int modificarNombre(String dni,String nombre){
        // update table nombreTabla set columna=valor where condicion
        // Update table TABLE_NAME set "Nombre"="pepito" where "DNI"="11Q"
        ContentValues values = new ContentValues();
        values.put(CN_NOMBRE,nombre);

        return db.update(TABLE_NAME,values,CN_DNI + "=?" ,new String[]{dni});
    }

    public void modificarCurso(){

    }



}
