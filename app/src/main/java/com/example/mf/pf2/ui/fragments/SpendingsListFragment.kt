package com.example.mf.pf2.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
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
import com.example.mf.pf2.R
import com.example.mf.pf2.di.Injectable
import com.example.mf.pf2.ui.adapters.SpendingsRecyclerViewAdapter
import com.example.mf.pf2.utils.toCurrency
import com.example.mf.pf2.viewmodel.SpendingsState
import com.example.mf.pf2.viewmodel.SpendingsViewModel
import kotlinx.android.synthetic.main.fragment_spendings_list.*
import org.jetbrains.anko.toast
import javax.inject.Inject


class SpendingsListFragment : Fragment(), FragmentInteractor, Injectable {
    private var columnCount = 1
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var spendingsAdapter = SpendingsRecyclerViewAdapter()
    private lateinit var viewModel: SpendingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // The animation was too slow
        progressBar.speed = 3f

        // Set ViewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SpendingsViewModel::class.java)

        // Set the adapter
        setAdapter()

        // Handle Refresh
        refresh_spendings.setOnRefreshListener {
            viewModel.fetchSpendings()
            context!!.toast("REFRESHED")
        }
        // Observe Changes
        observe()

        // Fetch Spendings
        viewModel.fetchSpendings()
    }

    private fun observe() {
        // Observe List
        viewModel.spendingState.observe(this.viewLifecycleOwner, Observer { state ->
            handleState(state)
        })
        // Observe totals
        viewModel.total
                .observe(this.viewLifecycleOwner, Observer {
                    total_spendings_amount.text = it!!.toCurrency()
                    progressBar.visibility = View.GONE
                })
    }

    private fun setAdapter() {
        with(list) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = spendingsAdapter
            list.addItemDecoration(DividerItemDecoration(context, 1))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_spendings_list, container, false)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
        viewModel.destroy()
    }

    private fun handleState(spendingsState: SpendingsState?) {
        val state = spendingsState ?: return
        when (state) {
            is SpendingsState.Success -> {
                spendingsAdapter.addSpendings(state.spendings)

                Log.d("SuccessFetch ", "size: ${state.spendings}")
            }
            is SpendingsState.Failed -> {
                Snackbar.make(list, state.message.orEmpty(), Snackbar.LENGTH_SHORT)
                        .setAction("Try Again") { viewModel.fetchSpendings() }
            }
        }
        refresh_spendings.isRefreshing = false
    }


    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}
