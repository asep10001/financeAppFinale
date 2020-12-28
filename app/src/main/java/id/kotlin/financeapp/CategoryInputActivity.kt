package id.kotlin.financeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.kotlin.anggota.Config.NetworkModule
import id.kotlin.financeapp.Model.actions.ResponseActions
import id.kotlin.financeapp.Model.getData.Category.DataItem
import kotlinx.android.synthetic.main.activity_category_input.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_input)

        val getCategoryData = intent.getParcelableExtra<DataItem>("dataCategory")
        if(getCategoryData!=null){
            ieUpdateTextCategory.setText(getCategoryData.name.toString())
            imageUpdateTextCategory.setText(getCategoryData.image.toString())

            btnUpdateCategory.setOnClickListener{
                updateDataCategory(getCategoryData.id.toString().toLong(), ieUpdateTextCategory.text.toString(), imageUpdateTextCategory.text.toString())
            }
        }
    }

    private fun updateDataCategory(category_id: Long?, name: String?, image: String?) {
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("image", image)

        val jsonObjectString = jsonObject.toString()

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        val input = NetworkModule.service().updateDataCategory(category_id ?: 0, requestBody)

        input.enqueue(object : Callback<ResponseActions> {
            override fun onResponse(
                call: Call<ResponseActions>,
                response: Response<ResponseActions>
            ) {
                Toast.makeText(
                    applicationContext,
                    "Successfully updating $name from category",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }

            override fun onFailure(call: Call<ResponseActions>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }
}
