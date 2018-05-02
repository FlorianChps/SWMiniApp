package chaps.flo.starwarsminiapp.presentation.presenter

import chaps.flo.starwarsminiapp.presentation.view.ILoadDataView

interface IBasePresenter<in V : ILoadDataView> {

    fun attach(view: V)

    fun detach()
}