package chaps.flo.domain.models.page.repository

import chaps.flo.domain.models.page.PagePeople
import io.reactivex.Single

interface PagePeopleRepository {

    fun getPeoplePage(page: Int): Single<PagePeople>
}