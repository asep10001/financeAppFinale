package id.kotlin.financeapp.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import id.kotlin.anggota.Config.NetworkModule
import id.kotlin.financeapp.Adapters.DetailCategoryAdapters.ExpensesDetailAdapter
import id.kotlin.financeapp.Adapters.DetailCategoryAdapters.IncomeDetailAdapter
import id.kotlin.financeapp.Adapters.ExpensesAdapter
import id.kotlin.financeapp.Adapters.IncomeAdapter
import id.kotlin.financeapp.InputActivity
import id.kotlin.financeapp.Model.actions.ResponseActions
import id.kotlin.financeapp.Model.getData.Expenses.DataExpenses
import id.kotlin.financeapp.Model.getData.Expenses.ResponseExpenses
import id.kotlin.financeapp.Model.getData.Income.DataIncome
import id.kotlin.financeapp.Model.getData.Income.ResponseIncome
import id.kotlin.financeapp.R
import kotlinx.android.synthetic.main.activity_detail_category.*
import kotlinx.android.synthetic.main.activity_expenses.*
import kotlinx.android.synthetic.main.activity_income.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailCategoryActivity : AppCompatActivity() {
    private lateinit var categoryName: String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_category)
        createIncomeDetail.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            intent.putExtra("categoryName", categoryName)
            intent.putExtra("isIncome", "true")
            startActivity(intent)
        }
        categoryName = intent.getStringExtra("categoryName")
        createExpensesDetail.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            intent.putExtra("categoryName", categoryName)
            intent.putExtra("isIncome", "false")
            startActivity(intent)
        }

        showIncomeListByCategory(categoryName)
        showExpensesListByCategory(categoryName)
    }

    fun showExpensesListByCategory(name: String) {
        val listExpenses = NetworkModule.service().getDataExpensesByCategory(name)
        listExpenses.enqueue(object : Callback<ResponseExpenses> {
            override fun onResponse(
                call: Call<ResponseExpenses>,
                response: Response<ResponseExpenses>
            ) {
                if (response.isSuccessful) {
                    val item = response.body()?.data
                    val adapter = ExpensesDetailAdapter(item, object : ExpensesAdapter.OnClickListener {
                        override fun detail(item: DataExpenses?) {
                            val intent = Intent(this@DetailCategoryActivity, InputActivity::class.java)
                            intent.putExtra("categoryName", categoryName)
                            intent.putExtra("dataExpenses", item)
                            intent.putExtra("isIncome", "false")
                            startActivity(intent)
                        }

                        override fun hapusData(item: DataExpenses?) {

                            AlertDialog.Builder(this@DetailCategoryActivity).apply {
                                setTitle("Hapus Expenses?")
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
                    listExpensesDetail.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseExpenses>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }

    fun showIncomeListByCategory(name: String) {
        val listIncomes = NetworkModule.service().getDataIncomesByCategory(name)
        listIncomes.enqueue(object : Callback<ResponseIncome> {
            override fun onResponse(
                call: Call<ResponseIncome>,
                response: Response<ResponseIncome>
            ) {
                if (response.isSuccessful) {
                    val item = response.body()?.data
                    val adapter = IncomeDetailAdapter(item, object : IncomeAdapter.OnClickListener {
                        override fun detail(item: DataIncome?) {
                            val intent = Intent(this@DetailCategoryActivity, InputActivity::class.java)
                            intent.putExtra("categoryName", categoryName)
                            intent.putExtra("isIncome", "true")
                            intent.putExtra("dataIncome", item)
                            startActivity(intent)
                        }

                        override fun hapusData(item: DataIncome?) {

                            AlertDialog.Builder(this@DetailCategoryActivity).apply {
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
                    listIncomeDetail.adapter = adapter
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
                showIncomeListByCategory(categoryName)
            }

            override fun onFailure(call: Call<ResponseActions>, t: Throwable) {
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
                showExpensesListByCategory(categoryName)
            }

            override fun onFailure(call: Call<ResponseActions>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }


    override fun onResume() {
        super.onResume()
        showIncomeListByCategory(categoryName)
        showExpensesListByCategory(categoryName)
    }
}
