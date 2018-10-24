package com.example.mf.pf2.ui.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.mf.pf2.R
import com.example.mf.pf2.ui.fragments.BlankFragment
import com.example.mf.pf2.ui.fragments.DashboardFragment
import com.example.mf.pf2.ui.fragments.FragmentInteractor
import com.example.mf.pf2.ui.fragments.SpendingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FragmentInteractor {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                changeFragment(DashboardFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_spendings -> {
                changeFragment(SpendingsFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                changeFragment(BlankFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun changeFragment(f: Fragment){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.activity_main_content,f)
        ft.addToBackStack(null)
        ft.commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) changeFragment(DashboardFragment())

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
