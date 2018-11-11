package com.example.mf.pf2.ui.activities

import android.animation.Animator
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.mf.pf2.R
import com.example.mf.pf2.utils.hideKeyboard
import com.example.mf.pf2.utils.launchActivity
import kotlinx.android.synthetic.main.activity_add_spendings.*
import kotlinx.android.synthetic.main.content_add_spendings.*

class AddSpendingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_spendings)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
        toolbar.setNavigationOnClickListener {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
            launchActivity<MainActivity>(options = options.toBundle()){  }
        }
        add_spendings_button.setOnClickListener {
            it?.hideKeyboard()
            add_spendings_content.visibility = View.INVISIBLE
            add_spendings_loader.visibility = View.VISIBLE
            add_spendings_loader.playAnimation()
            add_spendings_loader.addAnimatorListener(object: Animator.AnimatorListener{
                override fun onAnimationRepeat(p0: Animator?) {
                }
                override fun onAnimationStart(p0: Animator?) {
                }
                override fun onAnimationCancel(p0: Animator?) {
                }
                override fun onAnimationEnd(p0: Animator?) {
                    launchActivity<MainActivity> {  }
                }
            })
        }
    }

}
