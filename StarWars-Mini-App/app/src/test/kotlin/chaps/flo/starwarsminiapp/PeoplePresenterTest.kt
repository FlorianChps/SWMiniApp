package chaps.flo.starwarsminiapp

import chaps.flo.domain.models.page.PagePeople
import chaps.flo.domain.models.page.interactors.GetPagePeople
import chaps.flo.domain.models.page.repository.PagePeopleRepository
import chaps.flo.domain.models.people.People
import chaps.flo.starwarsminiapp.presentation.executor.UIThreadExecutor
import chaps.flo.starwarsminiapp.presentation.presenter.PeoplePresenter
import chaps.flo.starwarsminiapp.presentation.view.people.IPeopleView
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PeoplePresenterTest {

    private lateinit var presenter: PeoplePresenter
    private lateinit var useCase: GetPagePeople
    private lateinit var view: IPeopleView
    private var executor = UIThreadExecutor()

    @Before
    fun setup() {
        view = Mockito.mock(IPeopleView::class.java)
        useCase = GetPagePeople(Mockito.mock(PagePeopleRepository::class.java))
        presenter = PeoplePresenter(useCase, executor)
        presenter.attach(view)
    }

    @Test
    fun checkView() {
        `when`(useCase.buildUseCaseSingle(1)).thenReturn(Single.create {
            it.onSuccess(dummyPagePeople())
        })

        presenter.displayPeopleList()
    }

    private fun dummyPeople() = People(
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
    )

    private fun dummyPagePeople() = PagePeople(
            25,
            "urlNext",
            "urlPrev",
            listOf(dummyPeople())
    )
}
