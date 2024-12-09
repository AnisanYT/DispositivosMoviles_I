package com.example.devhub

import Data.ManagerMemory
import Entities.User
import androidx.appcompat.app.AlertDialog
import Util.Util
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.*
import android.provider.MediaStore
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class register : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var txtPassw: EditText
    private lateinit var txtRPass: EditText
    private lateinit var txtUserN: EditText
    private lateinit var imgProfi: ImageView
    private var profileBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                CAMERA_REQUEST_CODE
            )
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtEmail = findViewById(R.id.info_email_register)
        txtPassw = findViewById(R.id.info_passwd_register_retype)
        txtRPass = findViewById(R.id.info_passwd_register_retype_)
        txtUserN = findViewById(R.id.txtUserName_insert)
        imgProfi = findViewById(R.id.profileIMG_)

        imgProfi.setOnClickListener{
            showImageDialog()
        }

        val btn: Button = findViewById<Button>(R.id.btn_Register_register)
        btn.setOnClickListener(View.OnClickListener { view ->
            registerUser()
        })
    }

    private fun showImageDialog(){
        val options = arrayOf<CharSequence>(getString(R.string.option_gallery), getString(R.string.option_camera))
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.choice_option))
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> openGallery()
                1 -> openCamera()
            }
        }
        builder.show()
    }

    private fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    private fun openCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    data?.data?.let { uri ->
                        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                        profileBitmap = bitmap
                        imgProfi.setImageBitmap(bitmap)
                    }
                }
                CAMERA_REQUEST_CODE -> {
                    val bitmap = data?.extras?.get("data") as Bitmap
                    profileBitmap = bitmap
                    imgProfi.setImageBitmap(bitmap)
                }
            }
        }
    }

    fun registerUser(){
        val email = txtEmail.text.toString().trim()
        val passd = txtPassw.text.toString().trim()
        val rePsw = txtRPass.text.toString().trim()
        val userN = txtUserN.text.toString().trim()
        if(userN.isEmpty()) return Toast.makeText(this, getString(R.string.msg_wrong_userN),
            Toast.LENGTH_SHORT).show()
        if(email.isEmpty()) return Toast.makeText(this, getString(R.string.msg_wrong_email),
            Toast.LENGTH_SHORT).show()
        if(!Util.isValidEmail(email)) return Toast.makeText(this, getString(R.string.msg_wrong_email),
            Toast.LENGTH_SHORT).show()
        if (ManagerMemory.getUserByEmail(email) != null) return Toast.makeText(this,
            getString(R.string.duplicateTXTemail), Toast.LENGTH_SHORT).show()
        if (passd.isEmpty()) return Toast.makeText(this, getString(R.string.msg_wrong_pswd),
            Toast.LENGTH_SHORT).show()
        if (passd.isEmpty() || rePsw != passd)
            return Toast.makeText(this, getString(R.string.msg_wrong_pswd),
                Toast.LENGTH_SHORT).show()

        val userId = Util.generateUniqueId()
        val newUser = User(userId, passd, email, userN, profileBitmap)
        ManagerMemory.addUser(newUser)

        Toast.makeText(this, getString(R.string.msg_success), Toast.LENGTH_SHORT).show()
        Util.openActivity(this, MainActivity::class.java)
    }


    companion object {
        private const val GALLERY_REQUEST_CODE = 1001
        private const val CAMERA_REQUEST_CODE = 1002
    }
}