package chaps.flo.starwarsminiapp.presentation.presenter

import android.arch.paging.PagedList
import chaps.flo.domain.models.page.PagePeople
import chaps.flo.domain.models.page.interactors.GetPagePeople
import chaps.flo.starwarsminiapp.presentation.executor.UIThreadExecutor
import chaps.flo.starwarsminiapp.presentation.view.people.IPeopleView
import chaps.flo.starwarsminiapp.presentation.view.people.datasource.PeopleSource
import chaps.flo.starwarsminiapp.presentation.viewmodel.mapToViewModel
import chaps.flo.starwarsminiapp.presentation.viewmodel.page.PagePeopleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class PeoplePresenter(private val getPagePeople: GetPagePeople,
                      private val uiThreadExecutor: UIThreadExecutor) : IBasePresenter<IPeopleView> {

    private var view: IPeopleView? = null

    override fun attach(view: IPeopleView) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    fun loadData(key: Int, callback: (PagePeopleViewModel) -> Unit) {
        uiThreadExecutor.execute({ view?.showLoading() })
        getPagePeople.buildUseCaseSingle(key)
                .map {
                    convert(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { pageResult ->
                            callback.invoke(pageResult)
                            uiThreadExecutor.execute({ view?.hideLoading() })
                        },
                        {
                            view?.showError(it.localizedMessage)
                            uiThreadExecutor.execute({ view?.hideLoading() })
                        })
    }

    fun displayPeopleList() {
        val executor = Executors.newSingleThreadExecutor()
        val pagePeopleSource = PeopleSource(this)
        executor.execute {
            val config: PagedList.Config = PagedList.Config.Builder()
                    .setEnablePlaceholders(true)
                    .setPageSize(10)
                    .setPrefetchDistance(3)
                    .setInitialLoadSizeHint(10)
                    .build()
            val pageList = PagedList.Builder(pagePeopleSource, config)
                    .setInitialKey(1)
                    .setMainThreadExecutor(UIThreadExecutor())
                    .setBackgroundThreadExecutor(PaginationThreadPoolExecutor())
                    .build()
            view?.displayPeopleList(pageList)
        }
    }

    companion object {

        private const val CORE_POOL_SIZE = 2
        private const val MAX_POOL_SIZE = 2
        private const val KEEP_ALIVE_TIME = 2L

        private fun convert(pagePeople: PagePeople): PagePeopleViewModel = mapToViewModel(pagePeople)

        class PaginationThreadPoolExecutor : ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                LinkedBlockingQueue<Runnable>())
    }

    fun runEnterRecycler() {
        view?.runEnterRecyclerAnimation()
    }
}