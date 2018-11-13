package com.example.mf.pf2.ui.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mf.pf2.R
import com.example.mf.pf2.di.Injectable
import com.example.mf.pf2.utils.ChartGenerator
import com.example.mf.pf2.utils.Constants
import com.example.mf.pf2.viewmodel.ReportsViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject


class DashboardFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ReportsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ReportsViewModel::class.java)
        observe()
        viewModel.fetchReports()

    }

    private fun observe() {
        viewModel.reports.observe(this, Observer { report ->
            val chartDS = if (report!!.size >= 3) report else Constants.mockDataset
            ChartGenerator.makeLineChart(
                    chartDS,
                    Constants.mockLabels,
                    dashboard_line_chart,
                    ChartGenerator.generateLabels(chartDS.size, "Week")
            )
        })
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.destroy()
    }
}
