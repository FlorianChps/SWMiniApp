package chaps.flo.data.mapper

import chaps.flo.data.model.api.people.PeopleAPI
import chaps.flo.data.model.api.people.PeopleResultAPI
import chaps.flo.domain.models.page.PagePeople
import chaps.flo.domain.models.people.People


internal fun mapFromAPI(entity: PeopleAPI): PagePeople =
        PagePeople(
                entity.count,
                entity.next,
                entity.previous,
                entity.peopleResults.map {
                    mapFromAPI(it)
                })

internal fun mapFromAPI(entity: PeopleResultAPI): People = People(
        entity.name,
        entity.height,
        entity.mass,
        entity.hairColor,
        entity.skinColor,
        entity.eyeColor,
        entity.birthYear,
        entity.gender,
        entity.homeWorld,
        entity.films,
        entity.species,
        entity.vehicles,
        entity.starships,
        entity.detailUrl)
