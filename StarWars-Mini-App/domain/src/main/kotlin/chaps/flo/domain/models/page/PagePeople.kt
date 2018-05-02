package chaps.flo.domain.models.page

import chaps.flo.domain.models.people.People

data class PagePeople(
        val count: Int,
        val next: String?,
        val previous: String?,
        val results: List<People>
)
