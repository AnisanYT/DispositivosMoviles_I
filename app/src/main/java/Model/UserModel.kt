package Model

import Data.DatabaseHelper
import Entities.User
import Interfaces.CrudOperations
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.ContactsContract.Data
import java.io.ByteArrayOutputStream


class UserModel(context: Context) : CrudOperations<User> {
    private val dbHelper = DatabaseHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    override fun add(item: User) {
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_USER_ID, item.id)
            put(DatabaseHelper.COLUMN_USER_USERNAME, item.username)
            put(DatabaseHelper.COLUMN_USER_EMAIL, item.email)
            put(DatabaseHelper.COLUMN_USER_PASSWORD, item.password)
            put(DatabaseHelper.COLUMN_USER_PROFILE_IMAGE, item.image?.toByteArray())
        }
        db.insert(DatabaseHelper.TABLE_USERS, null, values)
    }

    fun Bitmap.toByteArray(): ByteArray{
        val stream = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun ByteArray.toBitmap(): Bitmap{
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }

    override fun getById(id: String): User? {
        val cursor = db.query(DatabaseHelper.TABLE_USERS, null,
            "${DatabaseHelper.COLUMN_USER_ID} = ?", arrayOf(id),
            null, null, null)
        cursor?.moveToFirst()?.let {
            val user = cursorToUser(cursor)
            cursor.close()
            return user
        }
        cursor?.close()
        return null
    }

    private fun cursorToUser(cursor: Cursor): User {
        return User(
            cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_ID)),
            cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_EMAIL)),
            cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_PASSWORD)),
            cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_USERNAME)),
            cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_PROFILE_IMAGE))?.toBitmap()
        )
    }

    override fun getAll(): List<User> {
        val users = mutableListOf<User>()
        val cursor = db.query(DatabaseHelper.TABLE_USERS, null, null, null, null, null, null )
        if (cursor.moveToFirst()){
            do {
                val user = cursorToUser(cursor)
                users.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return users
    }

    fun getByEmail(email: String): User? {
        val cursor = db.query(DatabaseHelper.TABLE_USERS, null, "${DatabaseHelper.COLUMN_USER_EMAIL} = ?", arrayOf(email), null, null, null)
        cursor?.moveToFirst()?.let {
            val user = cursorToUser(cursor)
            cursor.close()
            return user
        }
        cursor?.close()
        return null
    }

    override fun update(item: User) {
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_USER_USERNAME, item.username)
            put(DatabaseHelper.COLUMN_USER_EMAIL, item.email)
            put(DatabaseHelper.COLUMN_USER_PASSWORD, item.password)
            put(DatabaseHelper.COLUMN_USER_PROFILE_IMAGE, item.image?.toByteArray())
        }
        db.update(DatabaseHelper.TABLE_USERS, values, "${DatabaseHelper.COLUMN_USER_ID} = ?", arrayOf(item.id))
    }

    override fun remove(id: String) {
        db.delete(DatabaseHelper.TABLE_USERS, "${DatabaseHelper.COLUMN_USER_ID} = ?", arrayOf(id))
    }
}
