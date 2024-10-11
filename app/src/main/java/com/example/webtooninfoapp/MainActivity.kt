package com.example.webtooninfoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.webtooninfoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        replaceFragment(HomeFragment(), getString(R.string.home_fragment_tag))
        binding.bottomNavigationBar.selectedItemId = R.id.home

        binding.bottomNavigationBar.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.home -> { replaceFragment(HomeFragment(), getString(R.string.home_fragment_tag))
                    true}
                R.id.favorites -> {    replaceFragment(FavoritesFragment(), getString(R.string.favorites_fragment_tag))
                    true}
                else -> false

            }
        }

        // Add a back stack listener to handle bottom navigation highlighting
        supportFragmentManager.addOnBackStackChangedListener {
            updateBottomNavHighlight()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
            updateBottomNavHighlight()
        }
        else showExitAppDialog()
    }

    private fun showExitAppDialog() {
        AlertDialog.Builder(this)
            .setTitle("Exit App")
            .setMessage("Do you really want to exit the app?")
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun replaceFragment(fragment: Fragment, tag:String){
        val fragmentManager = supportFragmentManager
        val existingFragment = fragmentManager.findFragmentByTag(tag)
        if (existingFragment !=null){
            fragmentManager.popBackStack(tag, 0)
        }else{
            fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragment, tag)
                .addToBackStack(tag)
                .commit()
        }
    }

    private fun updateBottomNavHighlight() {
        when (supportFragmentManager.findFragmentById(R.id.frame_layout)) {
            is HomeFragment -> binding.bottomNavigationBar.selectedItemId = R.id.home
            is FavoritesFragment -> binding.bottomNavigationBar.selectedItemId = R.id.favorites
            else -> {
                Log.d("MainActivity","null fragment" )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateBottomNavHighlight()
    }
}