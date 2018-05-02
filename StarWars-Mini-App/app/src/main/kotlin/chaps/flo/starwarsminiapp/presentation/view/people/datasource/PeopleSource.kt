package chaps.flo.starwarsminiapp.presentation.view.people.datasource

import android.arch.paging.PageKeyedDataSource
import chaps.flo.starwarsminiapp.presentation.presenter.PeoplePresenter
import chaps.flo.starwarsminiapp.presentation.viewmodel.people.PeopleViewModel
import java.util.regex.Pattern

class PeopleSource(private val presenter: PeoplePresenter) : PageKeyedDataSource<Int, PeopleViewModel>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PeopleViewModel>) {
        presenter.loadData(FIRST_PAGE, { pageResult ->
            val p = Pattern.compile("\\d+")
            val m = p.matcher(pageResult.next)
            while (m.find()) {
                presenter.runEnterRecycler()
                callback.onResult(pageResult.results, null, m.group().toInt())
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PeopleViewModel>) {
        presenter.loadData(params.key, { pageResult ->
            when {
                pageResult.next != null -> {
                    val p = Pattern.compile("\\d+")
                    val m = p.matcher(pageResult.next)
                    while (m.find()) {
                        callback.onResult(pageResult.results, m.group().toInt())
                    }
                }
                else -> callback.onResult(pageResult.results, null)
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PeopleViewModel>) {
        presenter.loadData(params.key, { pageResult ->
            if (pageResult.prev != null) {
                val p = Pattern.compile("\\d+")
                val m = p.matcher(pageResult.prev)
                while (m.find()) {
                    callback.onResult(pageResult.results, m.group().toInt())
                }
            } else {
                callback.onResult(pageResult.results, null)
            }
        })
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}