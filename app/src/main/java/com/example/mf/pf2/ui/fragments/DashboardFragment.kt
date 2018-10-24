package com.example.mf.pf2.ui.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mf.pf2.BaseApplication
import com.example.mf.pf2.R
import com.example.mf.pf2.network.SpendingsAPI
import com.example.mf.pf2.utils.ChartGenerator
import com.example.mf.pf2.utils.Constants
import com.example.mf.pf2.viewmodel.ReportsViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject


class DashboardFragment : Fragment() {

    @Inject
    lateinit var chartGenerator: ChartGenerator

    @Inject
    lateinit var spendingsAPI: SpendingsAPI

    private lateinit var viewModel : ReportsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApplication.activityComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return root
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ReportsViewModel::class.java)
        viewModel.reports.observe(this, Observer{ report ->
            val chartDS = if (report!!.size >= 3) report else Constants.mockDataset
            chartGenerator.makeLineChart(
                    chartDS,
                    Constants.mockLabels,
                    dashboard_line_chart,
                    chartGenerator.generateLabels(chartDS.size,"Week")
            )
        })
        viewModel.fetchReports(spendingsAPI)

    }

    override fun onDetach() {
        super.onDetach()
        viewModel.disposable.dispose()
    }
}
