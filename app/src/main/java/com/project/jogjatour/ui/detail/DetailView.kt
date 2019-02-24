package com.project.jogjatour.ui.detail

import com.project.jogjatour.data.Destination

interface DetailView {
    fun showLoading()
    fun dismissLoading()
    fun showErrorAlert(it: Throwable)
    fun showData(pariwisata: Destination?)
}