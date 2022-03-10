package com.stc.apetask.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stc.apetask.R
import com.stc.apetask.data.models.factsResponse.FactsData
import com.stc.apetask.presentation.di.component.DaggerAppComponent
import com.stc.apetask.presentation.ui.adapter.HomeDetailsAdapter
import com.stc.apetask.presentation.util.ApiState
import com.stc.apetask.presentation.util.CustomProgressDialog
import com.stc.apetask.presentation.util.Utils.checkForInternet
import javax.inject.Inject


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var recyclerView: RecyclerView? = null

    @Inject
    lateinit var vm: HomeViewModel

    private val progressDialog = CustomProgressDialog()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appComponent = DaggerAppComponent.builder()
            .build()
        appComponent.inject(this)
        recyclerView = view.findViewById<RecyclerView>(R.id.rvItems)
        val mSwipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
        val errorIv = view.findViewById<AppCompatImageView>(R.id.errorIv)
        loadData()

        vm.apiStateLiveData.observe(viewLifecycleOwner, {
            when (it) {
                ApiState.LOADING -> {
                    progressDialog.show(requireContext(), getString(R.string.please_wait))
                }
                ApiState.SUCCESS -> {
                    progressDialog.dialog.dismiss()
                }
                ApiState.FAILED -> {
                    progressDialog.dialog.dismiss()
                    recyclerView?.visibility = View.GONE
                    errorIv.visibility = View.VISIBLE
                    Toast.makeText(
                        requireContext(),
                        R.string.error,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

        vm.factsDataLive.observe(viewLifecycleOwner, {
            Log.i("FactsData===>", it.toString())
            Log.i("FactsDataSize===>", it.size.toString())
            prepareList(it)
            recyclerView?.visibility = View.VISIBLE
            errorIv.visibility = View.GONE
        })

        mSwipeRefreshLayout.setOnRefreshListener {
            loadData()
            mSwipeRefreshLayout.isRefreshing = false
        }
    }


    private fun loadData() {
        if (!checkForInternet(requireContext())) {
            Toast.makeText(
                requireContext(),
                R.string.please_check_internet_connection,
                Toast.LENGTH_LONG
            ).show()
            return
        }
        vm.loadFactsData()
    }

    private fun prepareList(it: List<FactsData>?) {
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = it?.let { productDetailsList ->
            HomeDetailsAdapter(requireContext(), productDetailsList)
        }

    }
}