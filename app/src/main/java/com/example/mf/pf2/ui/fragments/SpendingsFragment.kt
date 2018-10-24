package com.example.mf.pf2.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mf.pf2.BaseApplication
import com.example.mf.pf2.R
import com.example.mf.pf2.network.SpendingsAPI
import com.example.mf.pf2.utils.toNumberFormatted
import com.example.mf.pf2.ui.adapters.SpendingsRecyclerViewAdapter
import com.example.mf.pf2.utils.toCurrency
import com.example.mf.pf2.viewmodel.SpendingsState
import com.example.mf.pf2.viewmodel.SpendingsViewModel
import kotlinx.android.synthetic.main.fragment_spendings_list.*
import javax.inject.Inject


class SpendingsFragment : Fragment(), FragmentInteractor {

    private var columnCount = 1
    @Inject
    lateinit var spendingsAPI : SpendingsAPI
    private var listener: FragmentInteractor? = null
    private var spendingsAdapter = SpendingsRecyclerViewAdapter()
    private lateinit var viewModel : SpendingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BaseApplication.activityComponent.inject(this)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // The animation was too slow
        progressBar.speed = 3f

        viewModel = ViewModelProviders.of(this).get(SpendingsViewModel::class.java)

        // Set the adapter

        with(list) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = spendingsAdapter
            list.addItemDecoration(DividerItemDecoration(context,1))
        }

        // Observe List
        viewModel.spendings.observe(this.viewLifecycleOwner, Observer { state ->
            onState(state)
        })
        // Observe totals
        viewModel.total
                .observe(this.viewLifecycleOwner, Observer {
            total_spendings_amount.text = it!!.toCurrency()
            progressBar.visibility = View.GONE
        })

        viewModel.fetchSpendings(spendingsAPI)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_spendings_list, container, false)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentInteractor) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement FragmentContract")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun onState(spendingsState: SpendingsState?){
        val state = spendingsState ?: return
        when(state){
            is SpendingsState.Success -> {
                spendingsAdapter.addSpendings(state.spendings)

                Log.d("SuccessFetch ","size: ${state.spendings}")
            }
            is SpendingsState.Failed -> {
                Snackbar.make(list,state.message.orEmpty(),Snackbar.LENGTH_SHORT)
                        .setAction("Try Again"){ viewModel.fetchSpendings(spendingsAPI)}
            }
        }
    }



    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

    }
}
