package cl.uandes.pichangapp.views.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.databinding.FragmentTeamListBinding
import cl.uandes.pichangapp.models.Team
import cl.uandes.pichangapp.models.Teams

class TeamListFragment : Fragment(), TeamItemAdapter.ActionListener {

  // declaramos el adapter
  private lateinit var teamItemAdapter: TeamItemAdapter
  private val allTeams = Teams.createTeamList()
  private var _binding: FragmentTeamListBinding? = null
  private val binding get() = _binding!!

  private var mLastClickTime = System.currentTimeMillis()
  private val CLICK_TIME_INTERVAL: Long = 300

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentTeamListBinding.inflate(inflater, container, false)

    // definimos el adapter, pasandole la mutable list y quien va a entregar eventos a
    // los elementos de la vista que contiene el adapter
    teamItemAdapter = TeamItemAdapter(allTeams.toMutableList(), this)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val teamListView = binding.teamListView

    // aqui al layout manager del recycler view le decimos
    // que tipo de layout va a contener nuestros items.
    teamListView.layoutManager = LinearLayoutManager(context)

    // aqui le entregamos el adapter
    teamListView.adapter = teamItemAdapter
  }

  override fun onResume() {
    super.onResume()
    binding.editTextSearchTeam.addTextChangedListener(object: TextWatcher{
      override fun afterTextChanged(p0: Editable?) {  }

      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        var searchText = p0.toString().lowercase()
        filterTeams(searchText)
      }
    })
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }

  private fun filterTeams(searchText: String) {
    val filteredTeams = allTeams.filter {
      it.fullName!!
        .lowercase()
        .contains(searchText)
    }

    teamItemAdapter.updateTeams(filteredTeams)
  }

  override fun goToTeamDetails(team: Team) {
    Toast.makeText(context, "hola! hiciste click en el ${team.fullName}", Toast.LENGTH_LONG).show()
    /* ir al Fragment Team Details */
    // usando safe args...

    /* -> El siguiente codigo es para que al apretar dos items, no se caiga la app */
    val now = System.currentTimeMillis()
    if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
      return;
    }

    mLastClickTime = now;
    /* -> hasta aqui <- */

    // aqui mandamos el fullName del team seleccionado
    val bundle = bundleOf("teamName" to team.fullName.toString())
    // llamamos la accion para navegar y el argumento entregado
    findNavController().navigate(R.id.teamListToTeamDetails, bundle)
  }
}
