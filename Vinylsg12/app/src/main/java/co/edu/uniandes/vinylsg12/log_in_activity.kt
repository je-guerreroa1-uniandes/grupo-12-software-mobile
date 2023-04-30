package co.edu.uniandes.vinylsg12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class log_in_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_activity)

        //Vincular btn_login con la vista Launch:
        val btn_login = findViewById<AppCompatButton>(R.id.btn_login)

        btn_login.setOnClickListener() {
            val intent = Intent(this, activityLaunchScreen::class.java)
            startActivity(intent)
        }
    }
}