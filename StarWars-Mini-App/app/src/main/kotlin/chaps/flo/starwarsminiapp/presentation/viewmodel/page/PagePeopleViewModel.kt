package chaps.flo.starwarsminiapp.presentation.viewmodel.page

import android.os.Parcel
import android.os.Parcelable
import chaps.flo.starwarsminiapp.presentation.viewmodel.people.PeopleViewModel

data class PagePeopleViewModel(
        private val count: Int,
        val next: String?,
        val prev: String?,
        val results: List<PeopleViewModel>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(PeopleViewModel))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(count)
        parcel.writeString(next)
        parcel.writeString(prev)
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PagePeopleViewModel> {
        override fun createFromParcel(parcel: Parcel): PagePeopleViewModel {
            return PagePeopleViewModel(parcel)
        }

        override fun newArray(size: Int): Array<PagePeopleViewModel?> {
            return arrayOfNulls(size)
        }
    }

}