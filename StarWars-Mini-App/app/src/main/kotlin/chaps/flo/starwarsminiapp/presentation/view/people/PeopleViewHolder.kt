package chaps.flo.starwarsminiapp.presentation.view.people

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import chaps.flo.starwarsminiapp.R
import chaps.flo.starwarsminiapp.presentation.viewmodel.people.PeopleViewModel

class PeopleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val avatar: ImageView = view.findViewById(R.id.peopleAvatar)
    val name: TextView = view.findViewById(R.id.peopleName)
    private val height: TextView = view.findViewById(R.id.peopleHeight)
    private val mass: TextView = view.findViewById(R.id.peopleMass)
    private val birthDate: TextView = view.findViewById(R.id.peopleBirthDate)
    private val cardPeopleItem: CardView = view.findViewById(R.id.cardPeopleItem)

    fun setItemData(item: PeopleViewModel?) {
        item?.let {
            name.text = it.name
            height.text = String.format(itemView.context.getString(R.string.height_format), it.height)
            mass.text = String.format(itemView.context.getString(R.string.mass_format), it.mass)
            birthDate.text = String.format(itemView.context.getString(R.string.birth_date_format), it.birthDate)
            avatar.setImageResource(it.avatar)
        }
    }

    fun setAnimation() {
        val animation = AnimationUtils.loadLayoutAnimation(cardPeopleItem.context, R.anim.layout_anim_from_bottom)
        cardPeopleItem.layoutAnimation = animation
    }

    fun clearAnimation() {
        cardPeopleItem.clearAnimation()
    }
}