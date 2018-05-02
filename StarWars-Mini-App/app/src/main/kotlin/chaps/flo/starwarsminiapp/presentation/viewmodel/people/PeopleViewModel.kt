package chaps.flo.starwarsminiapp.presentation.viewmodel.people

import android.os.Parcel
import android.os.Parcelable

data class PeopleViewModel(
        val avatar: Int,
        val name: String,
        val height: String,
        val mass: String,
        val hairColor: String,
        val skinColor: String,
        val eyesColor: String,
        val birthDate: String,
        private val gender: String,
        private val homeWorld: String,
        private val nbMovies: Int,
        private val nbSpecies: Int,
        private val nbVehicles: Int,
        private val nbStarships: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(avatar)
        parcel.writeString(name)
        parcel.writeString(height)
        parcel.writeString(mass)
        parcel.writeString(hairColor)
        parcel.writeString(skinColor)
        parcel.writeString(eyesColor)
        parcel.writeString(birthDate)
        parcel.writeString(gender)
        parcel.writeString(homeWorld)
        parcel.writeInt(nbMovies)
        parcel.writeInt(nbSpecies)
        parcel.writeInt(nbVehicles)
        parcel.writeInt(nbStarships)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PeopleViewModel> {
        override fun createFromParcel(parcel: Parcel): PeopleViewModel {
            return PeopleViewModel(parcel)
        }

        override fun newArray(size: Int): Array<PeopleViewModel?> {
            return arrayOfNulls(size)
        }
    }

}