package es.iesguadalpea.dptoinformatica.isaias.contactosaplicacion;

/**
 * Created by isaias on 10/02/17.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactosHelper extends SQLiteOpenHelper {

    public static final String BDCONTACTOS = "dbContactos.sqlite";
    public static final int DB_VERSION = 1;

    public ContactosHelper(Context context) {
        super(context, BDCONTACTOS, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseManager.NOMBRE_TABLA);
        onCreate(db);
    }
}