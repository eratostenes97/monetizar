package apps.paprika.monetizar.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import apps.paprika.monetizar.R
import apps.paprika.monetizar.adapter.CountryClickListener
import apps.paprika.monetizar.adapter.CountryListAdapter
import apps.paprika.monetizar.model.Country
import apps.paprika.monetizar.presenter.CountriesPresenter
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity(), CountryClickListener, CountriesPresenter.View {

    private val countriesList = arrayListOf<Country>()
    private val countriesAdapter = CountryListAdapter(arrayListOf(), this)


    private val presenter = CountriesPresenter(this)

    private lateinit var rvList:RecyclerView
   private lateinit var progress: ProgressBar
   private lateinit var retryButton: MaterialButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress = findViewById(R.id.progress)
        rvList = findViewById(R.id.rvList)
        retryButton = findViewById(R.id.retryButtom)

        rvList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = countriesAdapter
        }
    }
    override fun onCountryClick(country: Country) {
        startActivity(DetailActivity.getIntent(this, country))
    }
    fun onRetry(v:View){
   presenter.onRetry()

        retryButton.visibility = View.GONE
        progress.visibility = View.VISIBLE
        rvList.visibility = View.VISIBLE
    }

    override fun setCountries(countries: List<Country>) {
        countriesList.clear()
        countries?.let { countriesList.addAll(it) }
        countriesAdapter.updateCountries(countriesList)

        retryButton.visibility = View.GONE
        progress.visibility = View.GONE
        rvList.visibility = View.VISIBLE
    }

    override fun onError() {
        Toast.makeText(this, "Unable to get countries list, please try again later", Toast.LENGTH_SHORT).show()
       retryButton.visibility = View.VISIBLE
        progress.visibility = View.GONE
        rvList.visibility = View.INVISIBLE
    }


}