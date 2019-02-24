package com.project.jogjatour.ui.home

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.project.jogjatour.R
import com.project.jogjatour.data.Destination
import com.project.jogjatour.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeView {


    private lateinit var adapter: HomeAdapter
    private lateinit var presenter: HomePresenter

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        loading.visibility = View.GONE
    }


    override fun showErrorAlert(it: Throwable) {
        Log.e("HomeActivity", it.localizedMessage)
        Toast.makeText(this@HomeActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
    }

    override fun showData(pariwisata: MutableList<Destination>)
    {
        adapter.setData(pariwisata)
//        swipe_refresh?.isRefreshing = false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar_home)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        initRecylerView()
        initPresenter()
        presenter.getData()



    }

    private fun initRecylerView() {

        card_recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = HomeAdapter()
        card_recycler_view.adapter = adapter
    }
    private fun initPresenter() {
        presenter = HomePresenterImp()
        presenter.initView(this)
    }

}
