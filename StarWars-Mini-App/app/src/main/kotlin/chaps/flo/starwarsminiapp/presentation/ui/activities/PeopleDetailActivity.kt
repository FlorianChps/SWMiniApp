package chaps.flo.starwarsminiapp.presentation.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import chaps.flo.starwarsminiapp.R
import chaps.flo.starwarsminiapp.presentation.presenter.PeopleDetailPresenter
import chaps.flo.starwarsminiapp.presentation.view.people.detail.IPeopleDetailView
import chaps.flo.starwarsminiapp.presentation.viewmodel.people.PeopleViewModel
import kotlinx.android.synthetic.main.activity_detail_people.*
import kotlinx.android.synthetic.main.include_card_people_info.*

class PeopleDetailActivity : AppCompatActivity(), IPeopleDetailView {

    private var presenter: PeopleDetailPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_people)
        setupActionBar()
        fillView()
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setHomeAsUpIndicator(R.drawable.ic_close_white)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun fillView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            peopleAvatar.transitionName = getString(R.string.avatar_transition)
            collapsingToolbarLayout.transitionName = getString(R.string.name_transition)
        }

        presenter = PeopleDetailPresenter(this)

        intent.extras.getParcelable<PeopleViewModel>(KEY_PEOPLE)?.let {
            presenter?.displayPeopleInfo(it)
        }
        cardInfosLayout.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardInfosLayout.visibility = View.GONE
            finishAfterTransition()
        }
    }

    override fun displayToolbarTitle(name: String) {
        collapsingToolbarLayout.title = name
    }

    override fun displayHairColor(hairColor: String) {
        hairColorText.text = hairColor
    }

    override fun displayEyesColor(eyesColor: String) {
        eyeColorText.text = eyesColor
    }

    override fun displayWeight(mass: String) {
        weightText.text = String.format(getString(R.string.mass_format), mass)
    }

    override fun displayHeight(height: String) {
        heightText.text = String.format(getString(R.string.height_format), height)
    }

    override fun displaySkinColor(skinColor: String) {
        skinColorText.text = skinColor
    }

    override fun displayBirthDate(birthDate: String) {
        birthDateText.text = String.format(getString(R.string.birth_date_format), birthDate)
    }

    override fun displayAvatar(avatar: Int) {
        peopleAvatar.setImageResource(avatar)
    }

    companion object {

        private const val KEY_PEOPLE = "PEOPLE"

        fun buildIntent(context: Context, peopleViewModel: PeopleViewModel): Intent {
            val intent = Intent(context, PeopleDetailActivity::class.java)
            intent.putExtra(KEY_PEOPLE, peopleViewModel)
            return intent
        }
    }
}