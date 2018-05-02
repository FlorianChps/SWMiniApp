package chaps.flo.starwarsminiapp.presentation.ui.activities

import android.arch.paging.PagedList
import android.os.Bundle
import android.support.annotation.MainThread
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import chaps.flo.data.repository.PagePeopleDataRepository
import chaps.flo.domain.models.page.interactors.GetPagePeople
import chaps.flo.starwarsminiapp.R
import chaps.flo.starwarsminiapp.presentation.executor.UIThreadExecutor
import chaps.flo.starwarsminiapp.presentation.presenter.PeoplePresenter
import chaps.flo.starwarsminiapp.presentation.ui.adapters.PeopleAdapter
import chaps.flo.starwarsminiapp.presentation.view.people.IPeopleView
import chaps.flo.starwarsminiapp.presentation.viewmodel.people.PeopleViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IPeopleView, View.OnClickListener {

    private val adapter: PeopleAdapter = PeopleAdapter(::peopleClicked)
    private var presenter: PeoplePresenter? = null
    private var isGenderFabOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fillView()
    }

    private fun fillView() {
        recycler.layoutManager = StaggeredGridLayoutManager(resources.getInteger(R.integer.nb_raw_list), StaggeredGridLayoutManager.VERTICAL)
        recycler.adapter = adapter

        val pagePeopleDataRepo = PagePeopleDataRepository()
        val pagePeopleUseCase = GetPagePeople(pagePeopleDataRepo)
        presenter = PeoplePresenter(pagePeopleUseCase, UIThreadExecutor())
        presenter?.attach(this)
        presenter?.displayPeopleList()
        fabFilter.setOnClickListener(this)
        fabWoman.setOnClickListener(this)
        fabMale.setOnClickListener(this)
        fabRobot.setOnClickListener(this)
    }

    private fun animateFab(animExpand: Animation, animCollapse: Animation) {
        when {
            isGenderFabOpen -> {
                fabRobot.startAnimation(animCollapse)
                fabWoman.startAnimation(animCollapse)
                fabMale.startAnimation(animCollapse)
                fabWoman.isClickable = false
                fabMale.isClickable = false
                isGenderFabOpen = false
            }
            else -> {
                fabRobot.startAnimation(animExpand)
                fabWoman.startAnimation(animExpand)
                fabMale.startAnimation(animExpand)
                fabWoman.isClickable = true
                fabMale.isClickable = true
                isGenderFabOpen = true
            }
        }
    }

    override fun onClick(v: View?) {
        when {
            v?.id == R.id.fabFilter -> animateFab(AnimationUtils.loadAnimation(applicationContext, R.anim.fab_anim_expand),
                    AnimationUtils.loadAnimation(applicationContext, R.anim.fab_anim_collapse))
            v?.id == R.id.fabWoman -> Toast.makeText(this, getString(R.string.woman_text), Toast.LENGTH_LONG).show()
            v?.id == R.id.fabMale -> Toast.makeText(this, getString(R.string.man_text), Toast.LENGTH_LONG).show()
            else -> Toast.makeText(this, getString(R.string.species_text), Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detach()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        loader.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loader.visibility = View.GONE
    }

    @MainThread
    override fun displayPeopleList(pagedList: PagedList<PeopleViewModel>) {
        adapter.submitList(pagedList)
    }

    override fun runEnterRecyclerAnimation() {
        val context = recycler.context
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim_from_bottom)
        recycler.layoutAnimation = controller
        recycler.scheduleLayoutAnimation()
    }

    private fun peopleClicked(peopleViewModel: PeopleViewModel, avatar: ImageView, name: TextView) {
        val pairAvatar = Pair.create<View, String>(avatar, ViewCompat.getTransitionName(avatar))
        val pairName = Pair.create<View, String>(avatar, ViewCompat.getTransitionName(name))
        val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairAvatar, pairName)
        startActivity(PeopleDetailActivity.buildIntent(this, peopleViewModel), optionsCompat.toBundle())
    }
}
