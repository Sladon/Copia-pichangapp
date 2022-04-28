package cl.uandes.pichangapp.views.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.databinding.LoginFragmentBinding
import cl.uandes.pichangapp.views.Global


class LoginFragment : Fragment() {

  private var _binding: LoginFragmentBinding? = null
  private val binding get() = _binding!!
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val bottomNavigationView = activity?.findViewById<View>(R.id.navigation_view)
    bottomNavigationView?.visibility = View.INVISIBLE

    _binding = LoginFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val userEmailInput = binding.editTextEmailAddress
    val userPasswordInput = binding.editTextPassword

    loginAction(userEmailInput, userPasswordInput)
    registerAction()
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }

  private fun loginAction(userEmailInput: EditText, userPasswordInput: EditText) {
    val loginButton = _binding?.loginButton

    loginButton?.setOnClickListener {
      var verifiedCredentials: Boolean? = null

      if (userEmailInput.text.toString().isNotEmpty() && userPasswordInput.text.toString().isNotEmpty()) {
        verifiedCredentials = verifyUser(userEmailInput.text.toString(), userPasswordInput.text.toString())

        if (verifiedCredentials) {
          Global.user = Global.users.find { it.email == userEmailInput.text.toString() }
          findNavController().navigate(R.id.loginToHome)
        } else {
          Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_LONG).show()

        }

      }
    }
  }

  private fun registerAction() {
    val registerButton = _binding?.registerText

    registerButton?.setOnClickListener {
      findNavController().navigate(R.id.loginToRegister)
    }
  }
  private fun verifyUser(email: String, password: String) : Boolean {
    val user = Global.users.find { it.email == email }

    if (user?.password == password) {
      return true
    }
    return false
  }
}
