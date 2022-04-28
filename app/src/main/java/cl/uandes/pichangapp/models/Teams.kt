package cl.uandes.pichangapp.models

class Teams {
  companion object {
    fun createTeamList() : ArrayList<Team> {
      val teams = ArrayList<Team>()
      teams.add(Team("A", email = "a@a.a", category = "A", password = "a"))
      teams.add(Team("B", email = "b@b.b", category = "B", password = "b"))
      teams.add(Team("C", email = "c@c.c", category = "C", password = "c"))
      teams.add(Team("D", email = "d@d.d", category = "D", password = "d"))

      return teams
    }
  }
}