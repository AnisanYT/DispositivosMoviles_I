package com.example.devhub

import Data.ManagerMemory
import Data.MemoryManager
import Util.Util
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var txtPassw: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MemoryManager.initialize(this)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtEmail = findViewById(R.id.info_email_login)
        txtPassw = findViewById(R.id.info_passwd_login)

        /*Pasos para que un boton funcione ->
            1. Primero el boton tiene que instanciarse de TIPO BUTTON
            2. ES IGUAL A encontrar por ID <TIPO BUTTON> y el ID :D
            3. Ahora esta istancia la usamos para añadir un setOnClickListener (METODO)
            4. Dentro del metodo se usa View.OnClickListener con una funcion usando
                view.
        */

        val btnRegister: Button = findViewById<Button>(R.id.btn_Register)
        btnRegister.setOnClickListener(View.OnClickListener { view ->
            Util.openActivity(this, register::class.java)
        })

        val btnLogin: Button = findViewById<Button>(R.id.btn_Login)
        btnLogin.setOnClickListener(View.OnClickListener { view ->
            loginFun()
        })

    }

    fun loginFun(){
        val email = txtEmail.text.toString()
        val passw = txtPassw.text.toString()

        val user = ManagerMemory.getUserByEmail(email)
        if(user == null) return Toast.makeText(this, getString(R.string.badcredentials),
                Toast.LENGTH_SHORT).show()

        if(user.password != passw) return Toast.makeText(this, getString(R.string.badcredentials),
                Toast.LENGTH_SHORT).show()
        Toast.makeText(this, getString(R.string.msg_success), Toast.LENGTH_SHORT).show()
        Util.openActivity(this, fypMain::class.java)
    }


}