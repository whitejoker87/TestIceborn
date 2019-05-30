package ru.orehovai.testiceborn

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private lateinit var binding: ru.orehovai.testiceborn.databinding.ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel.setFragmentLaunch("list")

        mainViewModel.fragmentLaunch.observe(this, Observer { aString ->
            if (aString != null) {
                when (aString) {
                    "list" -> setFragment(ListCountrysFragment())

                    else -> {
                    }
                }
            }
        })
    }

    private fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_create_applic, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
