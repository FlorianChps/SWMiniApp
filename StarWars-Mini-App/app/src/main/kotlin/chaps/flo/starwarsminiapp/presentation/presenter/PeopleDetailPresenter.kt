package chaps.flo.starwarsminiapp.presentation.presenter

import chaps.flo.starwarsminiapp.presentation.view.people.detail.IPeopleDetailView
import chaps.flo.starwarsminiapp.presentation.viewmodel.people.PeopleViewModel

class PeopleDetailPresenter(private val view: IPeopleDetailView) {

    fun displayPeopleInfo(peopleViewModel: PeopleViewModel) {
        view.displayAvatar(peopleViewModel.avatar)
        view.displayBirthDate(peopleViewModel.birthDate)
        view.displayHairColor(peopleViewModel.hairColor)
        view.displayHeight(peopleViewModel.height)
        view.displaySkinColor(peopleViewModel.skinColor)
        view.displayToolbarTitle(peopleViewModel.name)
        view.displayWeight(peopleViewModel.mass)
        view.displayEyesColor(peopleViewModel.eyesColor)
    }
}