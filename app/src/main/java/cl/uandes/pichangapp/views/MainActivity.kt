package cl.uandes.pichangapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

  private var _binding: ActivityMainBinding? = null
  private val binding get() = _binding
  private lateinit var navigationController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    _binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding?.root)

    val navigationView: BottomNavigationView = binding?.navigationView!!

    navigationController = findNavController(R.id.nav_host_fragment_activity_main)
    NavigationUI.setupActionBarWithNavController(this, navigationController)

    val appBarConfiguration = AppBarConfiguration(
      setOf(R.id.nav_graph)
    )
    setupActionBarWithNavController(navigationController, appBarConfiguration)
    navigationView.setupWithNavController(navigationController)

    if (Global.user == null) {
      navigationController.navigate(R.id.loginFragment)
    }
  }

  override fun onSupportNavigateUp(): Boolean {
    return NavigationUI.navigateUp(navigationController, null)
  }
}
