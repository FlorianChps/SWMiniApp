package chaps.flo.domain.models.people

data class People(
        val name: String,
        val height: String,
        val mass: String,
        val hairColor: String,
        val skinColor: String,
        val eyeColor: String,
        val birthYear: String,
        val gender: String,
        val homeWorld: String,
        val films: List<String>,
        val species: List<String>,
        val vehicles: List<String>,
        val starships: List<String>,
        val detailUrl: String
)
