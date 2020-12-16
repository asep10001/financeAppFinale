package id.kotlin.financeapp.Activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import id.kotlin.anggota.Config.NetworkModule
import id.kotlin.financeapp.Adapters.IncomeAdapter
import id.kotlin.financeapp.Model.actions.ResponseAction
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

        showIncomeList()
    }

    fun showIncomeList() {
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
//        val intent = Intent(this@IncomeActivity, InputActivity::class.java)
//        intent.putExtra("data", item)
//        startActivity(intent)

                        }

                        override fun hapusData(item: DataIncome?) {

                            AlertDialog.Builder(this@IncomeActivity).apply {
                                setTitle("Hapus Data")
                                setMessage("Apakah anda yakin ingin menghapus data ini?")
                                setPositiveButton("Delete") { dialog, _ ->
                                    hapusItem(item?.id)
                                    dialog.dismiss()
                                }

                                setNegativeButton("Cancel") { dialog, _ ->
                                    dialog.dismiss()
                                }
                            }.show()

                        }

                    })

                    list.adapter = adapter

                }
            }

            override fun onFailure(call: Call<ResponseIncome>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun hapusItem(id: String?) {
//        val hapus = NetworkModule.service().deleteIncome(id ?: "")
//        hapus.enqueue(object : Callback<ResponseAction> {
//            override fun onResponse(
//                call: Call<ResponseAction>,
//                response: Response<ResponseAction>
//            ) {
//                Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
//                showIncomeList()
//            }
//
//            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
//            }
//
//        })

    }

    override fun onResume() {
        super.onResume()
        showIncomeList()
    }
}
