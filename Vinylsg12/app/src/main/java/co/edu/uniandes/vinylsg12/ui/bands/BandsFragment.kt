package co.edu.uniandes.vinylsg12.ui.bands

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.edu.uniandes.vinylsg12.R

class BandsFragment : Fragment() {

    companion object {
        fun newInstance() = BandsFragment()
    }

    private lateinit var viewModel: BandsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bands, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BandsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}