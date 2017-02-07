package es.iesguadalpea.dptoinformatica.isaias.basesdedatossqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by isaias on 31/01/17.
 */

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

    public static final String DBUSUARIOS = "dbUsuarios.sqlite";
    public static final int DB_SCHEME_VERSION = 1;


    public UsuariosSQLiteHelper(Context context) {
        // contexto --> hace alusión a la actividad que la llama (this)
        // name --> Sería el nombre de la base de datos.
        // factory --> Objeto de tipo CursorFactory que siempre pondremos a null
        // version --> la versión del SCHEMA de mi base de datos
        // es decir por la posible modificación que voy en mi base de datos.

        super(context, DBUSUARIOS , null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
