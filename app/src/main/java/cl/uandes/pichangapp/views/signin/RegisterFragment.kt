package cl.uandes.pichangapp.views.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.databinding.RegisterFragmentBinding
import cl.uandes.pichangapp.models.Teams
import cl.uandes.pichangapp.models.Team
import cl.uandes.pichangapp.views.Global


class RegisterFragment : Fragment() {

  private var _binding: RegisterFragmentBinding? = null
  private val binding get() = _binding!!
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val bottomNavigationView = activity?.findViewById<View>(R.id.bottom)
    bottomNavigationView?.visibility = View.INVISIBLE
    _binding = RegisterFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val userNameInput = binding.editTextUserName
    val userEmailInput = binding.editTextEmailAddress
    val userPasswordInput = binding.editTextPassword
    val userPassword2Input = binding.editTextPassword2

    registerAction(userNameInput, userEmailInput, userPasswordInput, userPassword2Input)
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }

  private fun registerAction(userNameInput: EditText, userEmailInput: EditText, userPasswordInput: EditText, userPassword2Input: EditText) {
    val loginButton = _binding?.registerButton

    loginButton?.setOnClickListener {

      if (userEmailInput.text.toString().isNotEmpty() && userPasswordInput.text.toString().isNotEmpty()) {

        if (emailDoesntExist(userEmailInput.text.toString())) {
          registerUser(
            userNameInput.text.toString(),
            userPasswordInput.text.toString(),
            userEmailInput.text.toString()
          )
        }

        findNavController().navigate(R.id.registerToLogin)
      }
    }
  }
  private fun registerUser(name: String, password: String, email: String) {
    val newUser = Team(email = email ,fullName = name, password = password , category = null)
    Global.users.add(newUser)
    Toast.makeText(context, "Usuario ingresado correctamente", Toast.LENGTH_LONG).show()

  }

  private fun emailDoesntExist(email: String): Boolean {
    Global.users.find { it.email == email } ?: return true
    return false
  }
}
