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
import com.example.online_shop.App
import com.example.online_shop.R
import com.example.online_shop.databinding.FragmentSignInBinding
import com.example.online_shop.entity.Person
import com.example.online_shop.entity.Plug
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.slide_left)
        enterTransition = inflater.inflateTransition(R.transition.slide_right)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar)?.visibility =
            View.INVISIBLE
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar1)?.visibility =
            View.INVISIBLE
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.nav_view).visibility =
            View.INVISIBLE
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            btSignIn.setOnClickListener {

                if (etFirstName.text.toString() != "" && etLastName.text.toString() != "" &&
                    etPassword.text.toString() != ""
                ) {
                    viewModel.checkPerson( etFirstName.text.toString(), etLastName.text.toString(),
                                            etPassword.text.toString(), etEmail.text.toString() )
                    viewModel.checkPerson.onEach { item ->
                        if (item) {
                            Toast.makeText(context, context?.getString(R.string.exist_person),
                                Toast.LENGTH_SHORT).show()
                        } else {
                            App.person.firstName = etFirstName.text.toString()
                            App.person.lastName = etLastName.text.toString()
                            App.person.password = etPassword.text.toString()
                            App.person.email = etEmail.text.toString()

                            findNavController().navigate(R.id.action_nav_sign_to_nav_home)
                        }
                    }.launchIn(viewLifecycleOwner.lifecycleScope)
                } else {
                    Toast.makeText(context, context?.getString(R.string.enter_data), Toast.LENGTH_SHORT).show()
                }
            }

            etEmail.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    if (!validateEmail(etEmail.text.toString())) {
                        Toast.makeText(
                            context, context?.getString(R.string.validate_email),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            etPassword.setOnTouchListener { _, event ->
                val drawableRight = 2
                if (event.action == MotionEvent.ACTION_DOWN) {
                    if (event.rawX >= (etPassword.right -
                                etPassword.compoundDrawables[drawableRight].bounds.width() -
                                etPassword.paddingEnd)
                    ) {
                        if (etPassword.inputType == InputType.TYPE_CLASS_TEXT) {
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
                findNavController().navigate(R.id.action_nav_sign_to_nav_login)
            }

            ivGoogle.setOnClickListener {
                Toast.makeText(
                    context, context?.getString(R.string.google_account), Toast.LENGTH_SHORT
                ).show()
            }

            ivApple.setOnClickListener {
                Toast.makeText(
                    context,
                    context?.getString(R.string.apple_account), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}