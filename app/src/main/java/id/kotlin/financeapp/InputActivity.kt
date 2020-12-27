package id.kotlin.financeapp

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import id.kotlin.anggota.Config.NetworkModule
import id.kotlin.financeapp.Model.actions.ResponseActions
import id.kotlin.financeapp.Model.getData.Category.ResponseCategory
import id.kotlin.financeapp.Model.getData.Income.DataIncome
import kotlinx.android.synthetic.main.activity_input.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    @RequiresApi(Build.VERSION_CODES.N)
    var categoryId: Long = 0
    lateinit var categoryName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        getCategoryId()
        val getIncomeData = intent.getParcelableExtra<DataIncome>("dataIncome");
        val isIncome = intent.getStringExtra("isIncome")
        categoryName = intent.getStringExtra("categoryName")
//        getCategoryList();
        etCategory.setText(categoryName)
        if (getIncomeData != null) {
            etName.setText(getIncomeData.name)
            etSum.setText(getIncomeData.ammount.toString())
            etTransactionDate.setText(getIncomeData.transactionDate)
            button1.text = "Update"
        }


        when (button1.text) {
            "Update" -> {
                when (isIncome) {
                    "true" -> {
                        button1.setOnClickListener {
                            updateDataIncome(
                                getIncomeData.id.toString().toLong(),
                                categoryId,
                                etName.text.toString(),
                                etTransactionDate.text.toString(),
                                etSum.text.toString()
                            )
                        }
                    }
                }
            }
            else -> {
                when (isIncome) {
                    "true" -> {
                        button1.setOnClickListener {
                            inputDataIncome(
                                categoryId,
                                etName.text.toString(),
                                etTransactionDate.text.toString(),
                                etSum.text.toString()
                            )
                        }
                    }
                }
            }
        }

    }


    fun getCategoryId() {
        val listCategory = NetworkModule.service().getDataCategory();
        listCategory.enqueue(object : Callback<ResponseCategory> {
            override fun onResponse(
                call: Call<ResponseCategory>,
                response: Response<ResponseCategory>
            ) {
                if (response.isSuccessful) {
                    response?.body()?.data?.forEach { item ->
                        if(categoryName == item.name){
                            categoryId = item?.id?.toString()?.toLong() ?: 0
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseCategory>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getCategoryList() {
        val listCategory = NetworkModule.service().getDataCategory();
        listCategory.enqueue(object : Callback<ResponseCategory> {
            override fun onResponse(
                call: Call<ResponseCategory>,
                response: Response<ResponseCategory>
            ) {
                if (response.isSuccessful) {
                    val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

                    response?.body()?.data?.forEach { item ->
                        var radioButton = RadioButton(this@InputActivity)
//                        Toast.makeText(
//                            this@InputActivity,
//                            list_of_items?.get(0)?.name,
//                            Toast.LENGTH_LONG
//                        ).show()
                        radioButton.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        radioButton.text = item?.name
                        radioButton.setOnClickListener {
                            etCategory.text = item?.id.toString()
                        }
                        if (radioGroup != null) {
                            radioGroup.addView(radioButton)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseCategory>, t: Throwable) {
                print(t.message)
            }

        })


    }

    fun inputDataIncome(
        category_id: Long?,
        name: String?,
        transaction_date: String?,
        ammount: String?
    ) {
        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("transaction_date", transaction_date)
        jsonObject.put("ammount", ammount)

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        val input = NetworkModule.service().insertDataIncome(category_id ?: 0, requestBody)
        input.enqueue(object : Callback<ResponseActions> {
            override fun onResponse(
                call: Call<ResponseActions>,
                response: Response<ResponseActions>
            ) {
                Toast.makeText(
                    applicationContext,
                    "Successfully insert $name to the database",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }

            override fun onFailure(call: Call<ResponseActions>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun updateDataIncome(
        income_id: Long,
        category_id: Long?,
        name: String?,
        transaction_date: String?,
        ammount: String?
    ) {
        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("transaction_date", transaction_date)
        jsonObject.put("ammount", ammount)

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        val input =
            NetworkModule.service().updateDataIncome(income_id ?: 0, category_id ?: 0, requestBody)
        input.enqueue(object : Callback<ResponseActions> {
            override fun onResponse(
                call: Call<ResponseActions>,
                response: Response<ResponseActions>
            ) {
                Toast.makeText(
                    applicationContext,
                    "Sucessfully updating ${name} in the database",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }

            override fun onFailure(call: Call<ResponseActions>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}
