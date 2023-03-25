package com.example.online_shop.ui.signin

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.online_shop.R
import com.example.online_shop.databinding.FragmentSignInBinding
import com.example.online_shop.entity.Person
import com.example.online_shop.entity.Plug
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignInFragment: Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignInViewModel by viewModels()
    private val person = Person()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.slide_left)
        enterTransition = inflater.inflateTransition(R.transition.slide_right)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar)?.visibility = View.INVISIBLE
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar1)?.visibility = View.INVISIBLE
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.INVISIBLE
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            etFirstName.setText(Plug.person().firstName)
            etLastName.setText(Plug.person().lastName)
            etEmail.setText(Plug.person().email)
            etPassword.setText(Plug.person().password)

            btSignIn.setOnClickListener {

                person.firstName = etFirstName.text.toString()
                person.lastName = etLastName.text.toString()
                person.email = etEmail.text.toString()
                person.password = etPassword.text.toString()

                viewModel.checkPerson(person)
                viewModel.checkPerson.onEach { item ->
                    if (item == null){
                    } else if (item) {
                        Toast.makeText(context, context?.getString(R.string.exist_person),
                            Toast.LENGTH_SHORT).show()
                    } else {
                        val bundle = Bundle().apply {
                            putString("firstName", etFirstName.text.toString())
                            putString("password", etPassword.text.toString())
                        }
                        findNavController().navigate(R.id.action_nav_sign_to_nav_home, bundle)
                    }
                }.launchIn(viewLifecycleOwner.lifecycleScope)
            }

            etEmail.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus){
                    if (! validateEmail(etEmail.text.toString()) ){
                        Toast.makeText(context, context?.getString(R.string.validate_email),
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }

            etPassword.setOnTouchListener { _, event ->
                val drawableRight = 2
                if (event.action == MotionEvent.ACTION_DOWN) {
                    if (event.rawX >= (etPassword.right -
                                etPassword.compoundDrawables[drawableRight].bounds.width() -
                                etPassword.paddingEnd)) {
                        if (etPassword.inputType == InputType.TYPE_CLASS_TEXT){
                            etPassword.inputType =
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        } else {
                            etPassword.inputType = InputType.TYPE_CLASS_TEXT
                        }
                    }
                }
                false
            }

            tvLogin.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("firstName", etFirstName.text.toString())
                    putString("password", etPassword.text.toString())
                }
                findNavController().navigate(R.id.action_nav_sign_to_nav_login, bundle)
            }

            ivGoogle.setOnClickListener {
                Toast.makeText(
                    context, context?.getString(R.string.google_account), Toast.LENGTH_SHORT).show()
            }

            ivApple.setOnClickListener {
                Toast.makeText(context,
                    context?.getString(R.string.apple_account), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateEmail(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}