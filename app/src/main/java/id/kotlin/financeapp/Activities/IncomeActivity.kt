package id.kotlin.financeapp.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import id.kotlin.anggota.Config.NetworkModule
import id.kotlin.financeapp.Adapters.IncomeAdapter
import id.kotlin.financeapp.Model.actions.ResponseActions
import id.kotlin.financeapp.Model.getData.Income.DataIncome
import id.kotlin.financeapp.Model.getData.Income.ResponseIncome
import id.kotlin.financeapp.R
import kotlinx.android.synthetic.main.activity_income.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IncomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)

        fab.setOnClickListener{
            val intent = Intent(this, ShowAllInputActivity::class.java)
            startActivity(intent)
        }
        showIncomeList(list)
    }

    fun  showIncomeList(x: RecyclerView) {
        val listIncome = NetworkModule.service().getDataIncome()
        listIncome.enqueue(object : Callback<ResponseIncome> {
            override fun onResponse(
                call: Call<ResponseIncome>,
                response: Response<ResponseIncome>
            ) {
                if (response.isSuccessful) {
                    val item = response.body()?.data
                    val adapter = IncomeAdapter(item, object : IncomeAdapter.OnClickListener {
                        override fun detail(item: DataIncome?) {
                            val intent = Intent(this@IncomeActivity, ShowAllInputActivity::class.java)
                            intent.putExtra("dataIncome", item)
                            startActivity(intent)
                        }

                        override fun hapusData(item: DataIncome?) {

                            AlertDialog.Builder(this@IncomeActivity).apply {
                                setTitle("Hapus Income?")
                                setMessage("Are you sure to delete this Income?")
                                setPositiveButton("Delete") { dialog, _ ->
                                    delIncome(item?.id.toString().toLong())
                                    dialog.dismiss()
                                }

                                setNegativeButton("Cancel") { dialog, _ ->
                                    dialog.dismiss()
                                }
                            }.show()
                        }
                    })
                    x.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseIncome>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }


    fun delIncome(id: Long?) {
        val hapus = NetworkModule.service().deleteDataIncome(id ?: 0)
        hapus.enqueue(object : Callback<ResponseActions> {
            override fun onResponse(
                call: Call<ResponseActions>,
                response: Response<ResponseActions>
            ) {
                Toast.makeText(applicationContext, "Data is sucessfully deleted", Toast.LENGTH_SHORT).show()
                showIncomeList(list)
            }

            override fun onFailure(call: Call<ResponseActions>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }



    override fun onResume() {
        super.onResume()
        showIncomeList(list)
    }
}
