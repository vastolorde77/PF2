package com.example.mf.pf2.ui.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.mf.pf2.R
import com.example.mf.pf2.di.Injectable
import com.example.mf.pf2.ui.fragments.BlankFragment
import com.example.mf.pf2.ui.fragments.DashboardFragment
import com.example.mf.pf2.ui.fragments.FragmentInteractor
import com.example.mf.pf2.ui.fragments.SpendingsListFragment
import com.example.mf.pf2.utils.launchActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), FragmentInteractor, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchAndroidInjector

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                changeFragment(DashboardFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_spendings -> {
                changeFragment(SpendingsListFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                changeFragment(BlankFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun changeFragment(f: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        if (f is Injectable) {
            dispatchAndroidInjector.inject(f)
        }
        if (f is SpendingsListFragment) {
            fab.show()
        } else {
            fab.hide()
        }
        ft.replace(R.id.activity_main_content, f)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val currentSelectedItem = navigation.selectedItemId
        if(currentSelectedItem != R.id.navigation_home){
            changeFragment(DashboardFragment())
            navigation.selectedItemId = R.id.navigation_home
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) changeFragment(DashboardFragment())

        fab.setOnClickListener {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
            this.launchActivity<AddSpendingsActivity>(options = options.toBundle()) {

            }
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
