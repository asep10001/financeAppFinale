package id.kotlin.financeapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import id.kotlin.anggota.Config.NetworkModule
import id.kotlin.financeapp.Adapters.ExpensesAdapter
import id.kotlin.financeapp.InputActivity
import id.kotlin.financeapp.Model.actions.ResponseActions
import id.kotlin.financeapp.Model.getData.Expenses.DataExpenses
import id.kotlin.financeapp.Model.getData.Expenses.ResponseExpenses

import id.kotlin.financeapp.R
import kotlinx.android.synthetic.main.activity_expenses.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExpensesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses)

        fabExpenses.setOnClickListener{
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }
        showExpensesList()
    }

    fun showExpensesList() {
        val listExpenses = NetworkModule.service().getDataExpenses()
        listExpenses.enqueue(object : Callback<ResponseExpenses> {
            override fun onResponse(
                call: Call<ResponseExpenses>,
                response: Response<ResponseExpenses>
            ) {
                if (response.isSuccessful) {
                    val item = response.body()?.data
                    val adapter = ExpensesAdapter(item, object : ExpensesAdapter.OnClickListener {
                        override fun detail(item: DataExpenses?) {
                            val intent = Intent(this@ExpensesActivity, InputActivity::class.java)
                            intent.putExtra("dateExpenses", item)
                            startActivity(intent)
                        }

                        override fun hapusData(item: DataExpenses?) {

                            AlertDialog.Builder(this@ExpensesActivity).apply {
                                setTitle("Hapus Income?")
                                setMessage("Are you sure to delete this Expenses?")
                                setPositiveButton("Delete") { dialog, _ ->
                                    delExpenses(item?.id.toString().toLong())
                                    dialog.dismiss()
                                }

                                setNegativeButton("Cancel") { dialog, _ ->
                                    dialog.dismiss()
                                }
                            }.show()
                        }
                    })
                    listExpense.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseExpenses>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }

    fun delExpenses(id: Long?) {
        val hapus = NetworkModule.service().deleteDataExpenses(id ?: 0)
        hapus.enqueue(object : Callback<ResponseActions> {
            override fun onResponse(
                call: Call<ResponseActions>,
                response: Response<ResponseActions>
            ) {
                Toast.makeText(applicationContext, "Data is sucessfully deleted", Toast.LENGTH_SHORT).show()
                showExpensesList()
            }

            override fun onFailure(call: Call<ResponseActions>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onResume() {
        super.onResume()
        showExpensesList()
    }
}
