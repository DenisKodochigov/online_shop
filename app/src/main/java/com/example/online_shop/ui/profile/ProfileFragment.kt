package com.example.online_shop.ui.profile

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.transition.TransitionInflater
import com.example.online_shop.R
import com.example.online_shop.databinding.FragmentProfileBinding
import com.example.online_shop.entity.Person
import com.example.online_shop.ui.MyLifecycleObserver
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var observer: MyLifecycleObserver
    private val person = Person()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        enterTransition = inflater.inflateTransition(R.transition.slide_right)

        observer = MyLifecycleObserver(requireActivity().activityResultRegistry)
        lifecycle.addObserver(observer)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar).visibility =
            View.INVISIBLE
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar1).visibility =
            View.VISIBLE
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.nav_view).visibility =
            View.VISIBLE
        person.firstName = arguments?.getString("firstName") ?: ""
        person.password = arguments?.getString("password") ?: ""
        viewModel.setPerson(person)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.ivArrowLeft.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        if (!person.photo.isNullOrEmpty()) binding.ivPhoto.setImageURI(Uri.parse(person.photo))
        binding.tvChangePhoto.setOnClickListener {
            observer.selectImage()
            observer.uri.onEach {
                Log.d("KDS", "onViewCreated $it")
                person.photo = it.toString()
                viewModel.savePerson(person)
                (activity as AppCompatActivity).findViewById<ImageView>(R.id.iv_photo)
                    .setImageURI(it)
                binding.ivPhoto.setImageURI(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}