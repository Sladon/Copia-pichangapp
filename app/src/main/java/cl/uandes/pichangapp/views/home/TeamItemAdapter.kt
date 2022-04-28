package cl.uandes.pichangapp.views.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.models.Team

class TeamItemAdapter(
    private val teams: MutableList<Team>,
    private val actionListener: ActionListener
  ) : RecyclerView.Adapter<TeamItemAdapter.ViewHolder>() {

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // Buscamos una única vez los views de la vista que contiene nuestro item
    // en este caso, team_item.
    val teamName = itemView.findViewById<TextView>(R.id.teamNameTextView)!!
    val teamType = itemView.findViewById<TextView>(R.id.teamTypeTextView)!!
    val teamImage = itemView.findViewById<ImageView>(R.id.teamImageView)!!
    val teamItem = itemView.findViewById<ViewGroup>(R.id.teamItem)!!
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamItemAdapter.ViewHolder {
    // obtenemos el contexto del parent
    val context = parent.context
    // le pedimos el inflater al parent
    val inflater = LayoutInflater.from(context)

    // aqui "inflamos" la vista del parent con nuestro team item
    val teamView: View = inflater.inflate(R.layout.team_item, parent, false)

    // para crear el viewholder, le pasamos la vista
    return ViewHolder(teamView)
  }

  override fun onBindViewHolder(holder: TeamItemAdapter.ViewHolder, position: Int) {
    // aqui le decimos que hacer al view holder
    // para que podamos editar, manipular o entregar eventos
    // a los elementos de la vista
    val team: Team = teams[position]

    val name = holder.teamName
    val type = holder.teamType
    val image = holder.teamImage
    val detailsButton = holder.teamItem

    name.text = team.fullName
    type.text = teamType(team)
    image.setImageResource(R.mipmap.ic_hero_example)

    detailsButton.setOnClickListener {
      actionListener.goToTeamDetails(team)
    }
  }

  override fun getItemCount(): Int {
    return teams.size
  }

  // esta funcion notifica al adapter cuando cambia el
  // dataset definido al principio, en el constructor
  @SuppressLint("NotifyDataSetChanged")
  fun updateTeams(newTeams: List<Team>) {
    teams.clear()

    teams.addAll(newTeams)
    notifyDataSetChanged()
  }

  // esta funcion obtiene la clase de mi objeto
  private fun teamType(team: Team): String {
    return team.javaClass.simpleName
  }

  // Para que podamos utilizar este adapter en diversos fragments
  // creamos una interfaz, donde el fragment que requiera de este
  // adapter pueda entregar una accion a un elemento. En este caso,
  // el fin de este item es ver los detalles de un team, por lo
  // que definimos dicha funcion. Podria ser un nombre un poco
  // mas generico? a lo mejor si, depende del fin de tu adapter
  interface ActionListener {
    fun goToTeamDetails(team: Team)
  }
}
