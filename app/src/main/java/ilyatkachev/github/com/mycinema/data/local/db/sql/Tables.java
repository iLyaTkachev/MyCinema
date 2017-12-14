package ilyatkachev.github.com.mycinema.data.local.db.sql;

import ilyatkachev.github.com.mycinema.data.local.db.models.MoviesDb;

public class Tables {

    public static final String MOVIES_TABLE
            = "CREATE TABLE IF NOT EXISTS " +
            MoviesDb.TABLE + "(" +
            MoviesDb.ID + " INTEGER PRIMARY KEY NOT NULL," +
            MoviesDb.TITLE + " VARCHAR (500))";
}
