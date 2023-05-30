package co.edu.uniandes.vinylsg12

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import co.edu.uniandes.vinylsg12.databinding.ActivityHomeBinding
import co.edu.uniandes.vinylsg12.ui.about_us.AboutActivity
import co.edu.uniandes.vinylsg12.ui.collectors.collected_albums.CollectedAlbumsActivity
import co.edu.uniandes.vinylsg12.ui.prizes.PrizesActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navController: NavController
    private val destinationChangedListener =
        NavController.OnDestinationChangedListener { _, _, _ ->
            toggle.syncState()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navView: BottomNavigationView = binding.navView
        drawerLayout = binding.drawerLayout

        val navMenuView = binding.navMenuView

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_albums, R.id.navigation_collectors, R.id.navigation_musicians
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navMenuView.setNavigationItemSelectedListener(this)

       toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navController.addOnDestinationChangedListener(destinationChangedListener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(destinationChangedListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var handled = false

        when (item.itemId) {
            R.id.action_my_albums -> {
                CollectedAlbumsActivity.start(context = this)
                handled = true
            }
            R.id.action_about_us -> {
                AboutActivity.start(context = this)
                handled = true
            }
        }

        return if (handled) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when(item.itemId) {
            R.id.action_my_albums -> CollectedAlbumsActivity.start(context=this)
            R.id.action_prizes -> PrizesActivity.start(context=this)
            R.id.action_about_us -> AboutActivity.start(this)
        }
        return true
    }

    override fun onBackPressed() {
        // Do nothing
    }
}