package cl.uandes.pichangapp.views

import android.app.Application
import cl.uandes.pichangapp.models.Team
import cl.uandes.pichangapp.models.Teams

public class Global : Application() {
    companion object {
        @JvmField
        var user: Team? = null
        var users = Teams.createTeamList()
    }
}