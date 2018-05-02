package chaps.flo.starwarsminiapp.presentation.ui.adapters

import android.arch.paging.PagedListAdapter
import android.os.Build
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import chaps.flo.starwarsminiapp.R
import chaps.flo.starwarsminiapp.presentation.view.people.PeopleViewHolder
import chaps.flo.starwarsminiapp.presentation.viewmodel.people.PeopleViewModel

class PeopleAdapter(private val onPeopleClickFunction: (PeopleViewModel, ImageView, TextView) -> Unit) : PagedListAdapter<PeopleViewModel, PeopleViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder =
            PeopleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_people, parent, false))

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.setItemData(getItem(position)!!)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.avatar.transitionName = holder.itemView.resources.getString(R.string.avatar_transition)
            holder.name.transitionName = holder.itemView.resources.getString(R.string.name_transition)
        }
        holder.itemView.setOnClickListener({
            getItem(position)?.let {
                onPeopleClickFunction.invoke(it, holder.avatar, holder.name)
            }
        })
        holder.setAnimation()
    }

    override fun onViewDetachedFromWindow(holder: PeopleViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.clearAnimation()
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<PeopleViewModel>() {

            override fun areItemsTheSame(oldItem: PeopleViewModel, newItem: PeopleViewModel): Boolean =
                    oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: PeopleViewModel, newItem: PeopleViewModel): Boolean =
                    oldItem == newItem
        }
    }
}