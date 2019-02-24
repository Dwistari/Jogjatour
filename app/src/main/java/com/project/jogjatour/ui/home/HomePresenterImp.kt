package com.project.jogjatour.ui.home

import com.project.jogjatour.data.HomeInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



class HomePresenterImp : HomePresenter {

    private lateinit var view: HomeView
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
                    view.showData(it.wisata)
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
    override fun initView(view: HomeView) {
        this.view = view
    }
}