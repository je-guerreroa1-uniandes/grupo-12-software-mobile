package co.edu.uniandes.vinylsg12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class sign_up_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        //Vincular btn_sign con la vista Launch::
        val btn_signup = findViewById<AppCompatButton>(R.id.btn_signup)

        btn_signup.setOnClickListener() {
            val intent = Intent(this, activityLaunchScreen::class.java)
            startActivity(intent)
        }
    }
}