package chaps.flo.data.repository

import chaps.flo.data.mapper.mapFromAPI
import chaps.flo.data.service.StarWarsService
import chaps.flo.domain.models.page.PagePeople
import chaps.flo.domain.models.page.repository.PagePeopleRepository
import io.reactivex.Single

class PagePeopleDataRepository : PagePeopleRepository {

    override fun getPeoplePage(page: Int): Single<PagePeople> =
            StarWarsService.createService()
                    .flatMap {
                        it.getPeople(page)
                                .map {
                                    mapFromAPI(it)
                                }
                    }
}