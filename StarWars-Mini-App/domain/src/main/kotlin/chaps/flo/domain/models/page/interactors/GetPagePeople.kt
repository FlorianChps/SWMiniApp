package chaps.flo.domain.models.page.interactors

import chaps.flo.domain.models.page.PagePeople
import chaps.flo.domain.models.page.repository.PagePeopleRepository
import io.reactivex.Single


class GetPagePeople(private val dataRepository: PagePeopleRepository) {

    fun buildUseCaseSingle(page: Int): Single<PagePeople> = dataRepository.getPeoplePage(page)
}