package Util


import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import java.util.UUID

class Util {

    companion object{
        fun openActivity(context: Context, objClass: Class<*>,
                         keyName: String="", value: String?=""){
            val intent = Intent(context, objClass).apply { putExtra(keyName, value) }
            startActivity(context, intent, null)
        }
    }

    fun generateUniqueId(): String {
        return UUID.randomUUID().toString()
    }

    fun isValidEmail(email: String): Boolean {
        return email.contains("@")
    }
}
