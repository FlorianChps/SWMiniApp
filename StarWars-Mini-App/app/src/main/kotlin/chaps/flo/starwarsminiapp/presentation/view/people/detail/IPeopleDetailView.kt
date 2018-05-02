package chaps.flo.starwarsminiapp.presentation.view.people.detail

interface IPeopleDetailView {

    fun displayToolbarTitle(name: String)
    fun displayHairColor(hairColor: String)
    fun displayEyesColor(eyesColor: String)
    fun displayWeight(mass: String)
    fun displayHeight(height: String)
    fun displaySkinColor(skinColor: String)
    fun displayBirthDate(birthDate: String)
    fun displayAvatar(avatar: Int)
}