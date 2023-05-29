package co.edu.uniandes.vinylsg12.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import co.edu.uniandes.vinylsg12.HomeActivity
import co.edu.uniandes.vinylsg12.common.session.UserSession
import co.edu.uniandes.vinylsg12.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.collector.observe(this) {
            val collector = it ?: return@observe
            val currentCollector = collector
            UserSession.collector = currentCollector
            val intent = Intent(this, HomeActivity::class.java)
            binding.progressBar.visibility = View.GONE
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val collectorIdText = binding.collectorId.text.toString()
            val collectorId = collectorIdText.toIntOrNull()
            binding.btnLogin.isEnabled = false
            if (collectorId != null) {
                // The collectorId is a valid integer value
                viewModel.recoverCollector(collectorId,
                    onError = {
                        binding.btnLogin.isEnabled = true
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                )
            } else {
                // The collectorId is not a valid integer
                Toast.makeText(this, "Invalid collector ID", Toast.LENGTH_LONG).show()
                binding.btnLogin.isEnabled = true
            }
        }
    }
}