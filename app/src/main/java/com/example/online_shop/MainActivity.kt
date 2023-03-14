package com.example.online_shop

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.online_shop.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList = null
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment_activity_main ) as NavHostFragment
        navController = navHostFragment.navController
        navView.setupWithNavController(navController)

        setSupportActionBar(binding.includeToolbar1.toolbar1)
        supportActionBar?.title = ""
        binding.includeToolbar1.toolbar1.visibility=View.INVISIBLE
        binding.includeToolbar.toolbar.visibility = View.INVISIBLE
        binding.includeToolbar1.toolbar1.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.includeToolbar1.toolbar1.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed() }
        val text = getString(R.string.toolbar_title1)
        val text2 = text + " " + getString(R.string.toolbar_title2)
        val spannable: Spannable = SpannableString(text2)
        spannable.setSpan(ForegroundColorSpan(Color.BLUE), text.length, text2.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.includeToolbar.tvTitleToolbar.setText(spannable, TextView.BufferType.SPANNABLE)
        binding.includeToolbar.menuToolbar.setOnClickListener {
            Log.d("KDS", "MainActivity.onCreate")
            val popup = PopupMenu(this, binding.includeToolbar.menuToolbar)
            popup.inflate(R.menu.bottom_nav_menu)
            popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
                when (item!!.itemId) {
                    R.id.nav_home -> {
                        Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_SHORT).show()
                    }
                    R.id.nav_favourite -> {
                        Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_SHORT).show()
                    }
                    R.id.nav_box -> {
                        Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_SHORT).show()
                    }
                    R.id.nav_note -> {
                        Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_SHORT).show()
                    }
                    R.id.nav_profile -> {
                        Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_SHORT).show()
                    }
                }
                true
            })
            popup.show()
        }
    }
}