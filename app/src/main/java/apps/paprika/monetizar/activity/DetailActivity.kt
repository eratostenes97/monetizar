package apps.paprika.monetizar.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import apps.paprika.monetizar.R
import apps.paprika.monetizar.model.Country
import apps.paprika.monetizar.util.getProgressDrawable
import apps.paprika.monetizar.util.loadImage

class DetailActivity : AppCompatActivity() {


    lateinit var country: Country
    private lateinit var countryflag: ImageView
    private lateinit var textName: TextView
    private lateinit var textCapital: TextView
    private lateinit var textArea: TextView
    private lateinit var textPopulation: TextView
    private lateinit var textRegion: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        countryflag = findViewById(R.id.countryFlag)
        textName = findViewById(R.id.textName)
        textCapital = findViewById(R.id.textCapital)
        textArea = findViewById(R.id.textArea)
        textPopulation = findViewById(R.id.textPopulation)
        textRegion = findViewById(R.id.textRegion)



        if (intent.hasExtra(PARAM_COUNTRY) && intent.getParcelableExtra<Country>(PARAM_COUNTRY) != null){
            country = intent.getParcelableExtra(PARAM_COUNTRY)!!
        }else{
            finish()
        }

        populate()

    }

    fun populate(){
        countryflag.loadImage(country.flag, getProgressDrawable(this))
        textName.text = country.countryName
        textCapital.text = "capital: ${country.capital}"
        textArea.text = "area: ${country.area}"
        textPopulation.text = "population: ${country.population}"
        textArea.text = "region: ${country.region}"

    }
    companion object {
        val PARAM_COUNTRY = "country"

        fun getIntent(context: Context, country: Country): Intent{
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(PARAM_COUNTRY, country)
            return intent
        }
    }
}