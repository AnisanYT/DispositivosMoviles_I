package Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "devHub.db"
        private const val DATABASE_VERSION = 1

        // Table Names
        const val TABLE_USERS = "users"
        const val TABLE_POSTS = "posts"
        const val TABLE_COMMENTS = "comments"

        // User Table Columns
        const val COLUMN_USER_ID = "id"
        const val COLUMN_USER_PASSWORD = "name"
        const val COLUMN_USER_EMAIL = "email"
        const val COLUMN_USER_USERNAME = "username"
        const val COLUMN_USER_PROFILE_IMAGE = "profile_image"

        // Create Table Statements
        private const val CREATE_TABLE_USERS = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_USER_ID TEXT PRIMARY KEY,
                $COLUMN_USER_PASSWORD TEXT,
                $COLUMN_USER_EMAIL TEXT,
                $COLUMN_USER_USERNAME TEXT,
                $COLUMN_USER_PROFILE_IMAGE BLOB
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_USERS)
        // Añadir aquí las sentencias CREATE TABLE para Posts y Comments
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        // Añadir aquí las sentencias DROP TABLE para Posts y Comments
        onCreate(db)
    }
}
