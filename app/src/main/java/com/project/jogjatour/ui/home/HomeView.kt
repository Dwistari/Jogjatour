package com.project.jogjatour.ui.home

import com.project.jogjatour.data.Destination

interface HomeView {

    fun showLoading()
    fun dismissLoading()
    fun showErrorAlert(it: Throwable)
    fun showData(pariwisata: MutableList<Destination>)
}