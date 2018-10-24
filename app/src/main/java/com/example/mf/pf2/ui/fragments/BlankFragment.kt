package com.example.mf.pf2.ui.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mf.pf2.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng


class BlankFragment : Fragment(),OnMapReadyCallback {
    override fun onMapReady(map: GoogleMap?) {
        map?.mapType = GoogleMap.MAP_TYPE_HYBRID
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(49.39,-124.83),20f
        ))
    }

    lateinit var gMapView : MapView


    private var listener: FragmentInteractor? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        view.findViewById<MapView>(R.id.mapView).getMapAsync(this)
        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}
