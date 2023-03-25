package com.example.online_shop.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
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
import com.example.online_shop.databinding.FragmentLoginBinding
import com.example.online_shop.entity.Person
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    private val person = Person()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        enterTransition = inflater.inflateTransition(R.transition.slide_right)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar)?.visibility =
            View.INVISIBLE
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar1)?.visibility =
            View.INVISIBLE
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.nav_view).visibility =
            View.INVISIBLE
        viewModel.presentToNull()
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            etFirstName.setText(arguments?.getString("firstName") ?: "") //= firstName
            etPassword.setText(arguments?.getString("password") ?: "") //= firstName

            btLogin.setOnClickListener {
                person.firstName = binding.etFirstName.text.toString()
                person.password = binding.etPassword.text.toString()
                if (person.firstName != "" && person.password != "") {
                    viewModel.loginPerson(person)
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.error_login_enter),
                        Toast.LENGTH_SHORT
                    ).show()
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
                        true
                    }
                }
                false
            }

            viewModel.present.onEach { person ->
                if (person == null) {
                } else if (person == Person()) {
                    Toast.makeText(
                        context, context?.getString(R.string.error_login),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val bundle = Bundle().apply {
                        putString("firstName", etFirstName.text.toString())
                        putString("password", etPassword.text.toString())
                    }
                    findNavController().navigate(R.id.action_nav_login_to_nav_home, bundle)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}