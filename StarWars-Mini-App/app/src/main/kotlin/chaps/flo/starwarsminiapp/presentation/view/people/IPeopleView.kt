package chaps.flo.starwarsminiapp.presentation.view.people

import android.arch.paging.PagedList
import chaps.flo.starwarsminiapp.presentation.view.ILoadDataView
import chaps.flo.starwarsminiapp.presentation.viewmodel.people.PeopleViewModel

interface IPeopleView : ILoadDataView {

    fun displayPeopleList(pagedList: PagedList<PeopleViewModel>)

    fun runEnterRecyclerAnimation()
}