package com.example.devhub

import Util.Util
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
            Util.openActivity(this, fypMain::class.java)
        })

    }


}