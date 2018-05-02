package chaps.flo.domain.interactor

import chaps.flo.domain.models.page.PagePeople
import chaps.flo.domain.models.page.interactors.GetPagePeople
import chaps.flo.domain.models.page.repository.PagePeopleRepository
import chaps.flo.domain.models.people.People
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito

class GetPagePeopleTest {

    @Test
    fun getPagePeople() {
        val pagePeople = PagePeople(
                25,
                "urlNext",
                "urlPrev",
                listOf(People(
                        "john",
                        "160",
                        "90",
                        "brown",
                        "skinColor",
                        "blue",
                        "987BBY",
                        "male",
                        "planet",
                        listOf("1", " 2", "3"),
                        listOf("specieone", "specieone", "specieone"),
                        listOf("vehione", "vehiTwo", "vehiThree"),
                        listOf("starone", "startwo", "starthree"),
                        "urldetail"
                ))
        )

        val peopleSource: PagePeopleRepository = Mockito.mock(PagePeopleRepository::class.java)
        Mockito.`when`(peopleSource.getPeoplePage(1)).thenReturn(Single.create {
            it.onSuccess(pagePeople)
        })

        GetPagePeople(peopleSource).buildUseCaseSingle(1)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(pagePeople)
    }
}