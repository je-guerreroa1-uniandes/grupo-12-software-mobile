package co.edu.uniandes.vinylsg12.ui.prize

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import co.edu.uniandes.vinylsg12.R
import co.edu.uniandes.vinylsg12.databinding.ActivityPrizeFormBinding
import co.edu.uniandes.vinylsg12.databinding.ActivityPrizesBinding
import co.edu.uniandes.vinylsg12.ui.prizes.PrizesActivity
import co.edu.uniandes.vinylsg12.ui.prizes.PrizesViewModel

class PrizeFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrizeFormBinding
    private lateinit var viewModel: PrizeFormViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PrizeFormViewModel::class.java)
        binding = ActivityPrizeFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveActionButton.setOnClickListener {
            save()
        }
    }

    private fun save() {
        var name: String = binding.name.text.toString()
        var description: String = binding.description.text.toString()
        var organization: String = binding.organization.text.toString()
        if (name.isNotEmpty() && description.isNotEmpty() && organization.isNotEmpty()) {
            Toast.makeText(this, "Attempt to create a Prize", Toast.LENGTH_SHORT).show()// TODO: Remove this
            viewModel.save(
                name = name,
                description = description,
                organization = organization,
                onSuccess = {
                    Toast.makeText(this, "Prize created", Toast.LENGTH_SHORT).show()// TODO: incomplete form
                    finish()
                },
                onError = {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()// TODO: incomplete form
                }
            )
        } else {
            Toast.makeText(this, "Incomplete information", Toast.LENGTH_SHORT).show()// TODO: incomplete form
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, PrizeFormActivity::class.java)
            context.startActivity(intent)
        }
    }
}