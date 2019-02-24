package com.project.jogjatour.ui.detail

import com.project.jogjatour.data.HomeInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPresenterImp  : DetailPresenter {

    private lateinit var view: DetailView
    private var interactor: HomeInteractor = HomeInteractor()
    private var disposables = CompositeDisposable()

    override fun getData() {
        view.showLoading()
        interactor.getData()
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    view.dismissLoading()
                    view.showData(it.detaillWisata)
                },
                {
                    view.showErrorAlert(it)
                    view.dismissLoading()
                }
            )?.let {
                disposables.add(
                    it
                )
            }
    }

    override fun initView(view: DetailView) {
        this.view = view

    }
}