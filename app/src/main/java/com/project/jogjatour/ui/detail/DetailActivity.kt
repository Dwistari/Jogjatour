package com.project.jogjatour.ui.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.project.jogjatour.R
import com.project.jogjatour.data.Destination
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import android.content.Intent
import android.net.Uri
import android.view.Menu


class DetailActivity : AppCompatActivity(), DetailView {
    private lateinit var presenter: DetailPresenter

    private  var wisata : Destination? = null
    private var position: Int? = null
    private var destination_name = ""

    override fun showLoading() {
        loadings.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        loadings.visibility = View.GONE
    }


    override fun showErrorAlert(it: Throwable) {
        Log.e("HomeActivity", it.localizedMessage)
        Toast.makeText(this@DetailActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
    }

    override fun showData(pariwisata: Destination?) {
        Log.d("detail",pariwisata.toString())

       destination_name_detail.text = pariwisata?.namaPariwisata
       address_detail.text = pariwisata?.alamatPariwisata
       detail.text = pariwisata?.detailPariwisata
       Picasso.with(this).load(pariwisata?.gambarPariwisata).into(image)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initPresenter()
        wisata = intent.getSerializableExtra("wisata") as Destination
//        destination_name = intent.getStringExtra("wisata_name")
        position = intent.getIntExtra("position", 0)

        showData(wisata)

//        supportActionBar?.title = destination_name
        btn_lokasi.setOnClickListener {
            val intent = Intent(
                android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q="+ wisata!!.namaPariwisata))
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    private fun initPresenter() {
        presenter = DetailPresenterImp()
        presenter.initView(this)
    }
}
