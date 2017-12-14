package ilyatkachev.github.com.mycinema.data.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ilyatkachev.github.com.mycinema.data.local.db.sql.Tables;

public class SqlConnector extends SQLiteOpenHelper {

    private static final String NAME = "application.db";

    private static final int VERSION = 1;

    public SqlConnector(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase pSQLiteDatabase) {
        pSQLiteDatabase.execSQL(Tables.MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase pSQLiteDatabase, int pI, int pI1) {

    }
}
