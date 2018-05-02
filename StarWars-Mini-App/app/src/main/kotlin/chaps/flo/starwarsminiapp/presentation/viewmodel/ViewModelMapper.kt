package chaps.flo.starwarsminiapp.presentation.viewmodel

import chaps.flo.domain.models.page.PagePeople
import chaps.flo.domain.models.people.People
import chaps.flo.starwarsminiapp.R
import chaps.flo.starwarsminiapp.presentation.viewmodel.page.PagePeopleViewModel
import chaps.flo.starwarsminiapp.presentation.viewmodel.people.GenderPeople
import chaps.flo.starwarsminiapp.presentation.viewmodel.people.PeopleViewModel

internal fun mapToViewModel(pagePeople: PagePeople): PagePeopleViewModel = PagePeopleViewModel(
        pagePeople.count,
        pagePeople.next,
        pagePeople.previous,
        pagePeople.results.map {
            mapToViewModel(it)
        }
)

internal fun mapToViewModel(people: People): PeopleViewModel = PeopleViewModel(
        setIcon(people),
        people.name,
        people.height,
        people.mass,
        people.hairColor,
        people.skinColor,
        people.eyeColor,
        people.birthYear,
        people.gender,
        people.homeWorld,
        people.films.size,
        people.species.size,
        people.vehicles.size,
        people.starships.size
)

private fun setIcon(people: People): Int =
        when {
            people.gender == GenderPeople.MALE.gender -> R.drawable.ic_male
            people.gender == GenderPeople.WOMAN.gender -> R.drawable.ic_woman
            else -> R.drawable.ic_robot
        }
