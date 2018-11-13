package com.example.mf.pf2.ui.activities

import android.animation.Animator
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.mf.pf2.R
import com.example.mf.pf2.Spendings
import com.example.mf.pf2.di.Injectable
import com.example.mf.pf2.utils.hideKeyboard
import com.example.mf.pf2.utils.launchActivity
import com.example.mf.pf2.viewmodel.SpendingsViewModel
import kotlinx.android.synthetic.main.activity_add_spendings.*
import kotlinx.android.synthetic.main.content_add_spendings.*
import javax.inject.Inject

class AddSpendingsActivity : AppCompatActivity(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SpendingsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_spendings)
        initToolbar()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SpendingsViewModel::class.java)
        setupViews()

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroy()
    }

    private fun setupViews() {
        // LOADERS
        add_spendings_loader.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                launchActivity<MainActivity> { }
            }
        })
        // BUTTONS
        add_spendings_button.setOnClickListener {
            it.hideKeyboard()
            add_spendings_content.visibility = View.INVISIBLE
            add_spendings_loader.visibility = View.VISIBLE
            viewModel.postSpending(Spendings(
                    type = input_type.text.toString(),
                    amount = input_amount.text.toString().toInt()
            ))
            actionBar?.hide()
            add_spendings_loader.playAnimation()
        }

    }


    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
            launchActivity<MainActivity>(options = options.toBundle()) { }
        }
    }

}
