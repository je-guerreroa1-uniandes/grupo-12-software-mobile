package co.edu.uniandes.vinylsg12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton

class activityLaunchScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launch_screen_activity)

        //Vincular btn_login con la vista:
        val btn_login = findViewById<AppCompatButton>(R.id.btn_login)
        val btn_signup = findViewById<AppCompatButton>(R.id.btn_signup)

        btn_login.setOnClickListener() {
            val intent = Intent(this, log_in_activity::class.java)
            startActivity(intent)
        }

        btn_signup.setOnClickListener() {
            val intent = Intent(this, sign_up_activity::class.java)
            startActivity(intent)
        }

    }
}