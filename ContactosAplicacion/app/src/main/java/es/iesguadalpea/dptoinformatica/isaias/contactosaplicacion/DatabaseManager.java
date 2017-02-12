package es.iesguadalpea.dptoinformatica.isaias.contactosaplicacion;

/**
 * Created by isaias on 10/02/17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    //Aqui diseñar tabla base de datos
    public static final String NOMBRE_TABLA = "contactos";

    public static final String CN_ID = "_id";
    public static final String CN_NOMBRE = "nombre";
    public static final String CN_TELEFONO = "telefono";

    public static final String CREATE_TABLE = "CREATE TABLE " + NOMBRE_TABLA + " ( "
            + CN_ID + " integer primary key autoincrement, "
            + CN_NOMBRE + " text not null, "
            + CN_TELEFONO + " text );";


    private ContactosHelper helper;
    private SQLiteDatabase db;

    public DatabaseManager(Context contexto){
        helper = new ContactosHelper(contexto);
        db = helper.getWritableDatabase();
    }
    public long insertar (String Nombre, String Telefono){
        ContentValues valores = new ContentValues();
        valores.put(CN_NOMBRE,Nombre);
        valores.put(CN_TELEFONO,Telefono);

        return db.insert(NOMBRE_TABLA,null,valores);
    }

    // Un cursor es como hoja de EXCEL
    // cada fila es un elemento o registro
    // cada columna son las columnas de la tabla.
    public Cursor getContactos(){
        String[] columnas = new String[]{CN_ID,CN_NOMBRE,CN_TELEFONO};
        return db.query(NOMBRE_TABLA,columnas,null,null,null,null,null);
    }

    public Cursor getBuscaContacto(String nombre){
        //buscamos por el nombre en la base de datos y generamos una tabla tipo excel que
        // es un cursor.
        String[] columnas = new String[]{CN_ID,CN_NOMBRE,CN_TELEFONO};
        return db.query(NOMBRE_TABLA,columnas,CN_NOMBRE + "=?", new String[]{nombre},null,null,null);
    }

    public void eliminar(String nombre){
        //Para eliminar un elemento de la BD usaremos bd.delete(LaTabla, la cláusula WHERE, y los argumentos where)
        db.delete(NOMBRE_TABLA,CN_NOMBRE + "=?",new String[]{nombre});
    }

}
