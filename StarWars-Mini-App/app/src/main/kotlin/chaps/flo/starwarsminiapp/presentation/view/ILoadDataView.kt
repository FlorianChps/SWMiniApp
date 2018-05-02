package chaps.flo.starwarsminiapp.presentation.view

interface ILoadDataView {

    fun showError(message: String)

    fun showLoading()

    fun hideLoading()
}