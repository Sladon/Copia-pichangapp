package cl.uandes.pichangapp.views.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.databinding.TeamDetailsFragmentBinding
import cl.uandes.pichangapp.models.Teams

class TeamDetails : Fragment() {

  private val allTeams = Teams.createTeamList()
  private var teamName = "0"
  private var _binding: TeamDetailsFragmentBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = TeamDetailsFragmentBinding.inflate(inflater, container, false)
    return binding?.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    arguments?.let {
      // obtenemos los parametros que me pase el otro fragment
      teamName = TeamDetailsArgs.fromBundle(it).teamName
      setTeamAttributes(teamName)
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }

  private fun setTeamAttributes(teamName: String) {
    val name = _binding?.teamNameTextView
    val email = _binding?.teamEmailTextView
    val password = _binding?.teamPasswordTextView
    val category = _binding?.teamCategoryTextView
    val teamImage = _binding?.teamImageView

    val team = allTeams.find { it.fullName.toString() === teamName }

    if (team != null) {
      name?.text = team.fullName.toString()
      email?.text = team.email.toString()
      password?.text = team.password.toString()
      category?.text = team.category.toString()
      teamImage?.setImageResource(R.mipmap.ic_hero_example)
    }
  }

}
