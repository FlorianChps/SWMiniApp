package chaps.flo.data

import chaps.flo.data.mapper.mapFromAPI
import chaps.flo.data.model.api.people.PeopleAPI
import chaps.flo.data.model.api.people.PeopleResultAPI
import chaps.flo.domain.models.page.PagePeople
import org.junit.Assert.assertEquals
import org.junit.Test


class PagePeopleMapperTest {

    @Test
    fun mapFromEntity() {
        val listPeopleApi = makePeopleAPI()
        val listPeople = mapFromAPI(listPeopleApi)

        assertPagePeopleEquality(listPeopleApi, listPeople)
    }

    private fun assertPagePeopleEquality(listPeopleApi: PeopleAPI, listPeople: PagePeople) {
        assertEquals(listPeopleApi.count, listPeople.count)
        assertEquals(listPeopleApi.next, listPeople.next)
        assertEquals(listPeopleApi.previous, listPeople.previous)
    }

    private fun makePeopleAPI(): PeopleAPI = PeopleAPI(
            40,
            "urlNext",
            "urlPrev",
            listOf(PeopleResultAPI(
                    "John",
                    "198",
                    "98",
                    "blond",
                    "green",
                    "blue",
                    "456BBY",
                    "male",
                    "planet",
                    listOf("movieone", "movieTwo", "movieThree"),
                    listOf("specieOne", "specieTwo", "specieThree"),
                    listOf("vehicOne", "vehicTwo", "vehicThree"),
                    listOf("starOne", "starTwo", "starThree"),
                    "at time",
                    "at time",
                    "detailUrl"
            ))
    )
}