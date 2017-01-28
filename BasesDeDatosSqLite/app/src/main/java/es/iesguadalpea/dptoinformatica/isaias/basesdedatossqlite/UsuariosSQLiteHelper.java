package es.iesguadalpea.dptoinformatica.isaias.basesdedatossqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by isaias on 27/01/17.
 */

public class UsuariosSQLiteHelper extends SQLiteOpenHelper{

    String sqlCrear = "CREATE TABLE tbUsuarios (" +
            "DNI TEXT PRIMARY KEY," +
            "Nombre TEXT, " +
            "Apellidos TEXT, " +
            "Edad Integer, " +
            "Tfno TEXT, " +
            "Email TEXT" +
            ")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        // aquí ejecutamos la instrucción que permite crear la tabla.


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
