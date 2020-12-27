package id.kotlin.financeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import id.kotlin.anggota.Config.NetworkModule
import id.kotlin.financeapp.Activities.DetailCategoryActivity
import id.kotlin.financeapp.Activities.ExpensesActivity
import id.kotlin.financeapp.Activities.IncomeActivity
import id.kotlin.financeapp.Adapters.CategoryAdapter
import id.kotlin.financeapp.Model.actions.ResponseActions
import id.kotlin.financeapp.Model.getData.Category.DataItem
import id.kotlin.financeapp.Model.getData.Category.ResponseCategory
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val bottomSheetFragment = BottomSheetFragment();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fabCategory.setOnClickListener {
            bottomSheetFragment.show(supportFragmentManager, "BottomSheetDialog")
        }



        showCategorylist()
    }

    fun showCategorylist() {
        val listCategory = NetworkModule.service().getDataCategory()
        listCategory.enqueue(object : Callback<ResponseCategory> {
            override fun onResponse(
                call: Call<ResponseCategory>,
                response: Response<ResponseCategory>
            ) {
                if (response.isSuccessful) {
                    val item = response.body()?.data
                    val adapter = CategoryAdapter(item, object : CategoryAdapter.OnClickListener {
                        override fun detail(item: DataItem?) {
                            val intent = Intent(this@MainActivity, DetailCategoryActivity::class.java)
                            intent.putExtra("dataCategory", item)
                            intent.putExtra("categoryName", item?.name)
                            startActivity(intent)
                        }

                        override fun hapusData(item: DataItem?) {
                            AlertDialog.Builder(this@MainActivity).apply {
                                setTitle("Hapus Category?")
                                setMessage("Are you sure to delete this category?")
                                setPositiveButton("Delete") { dialog, _ ->
                                    delCategory(item?.id.toString().toLong())
                                    dialog.dismiss()
                                }
                                setNegativeButton("Cancel") { dialog, _ ->
                                    dialog.dismiss()
                                }
                            }.show()
                        }

                        override fun updateData(item: DataItem?) {
                            val intent =
                                Intent(this@MainActivity, CategoryInputActivity::class.java)
                            intent.putExtra("dataCategory", item)
                            startActivity(intent)
                        }


                    })
                    listCategoryRV.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseCategory>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }


    private fun delCategory(id: Long?) {
        val hapus = NetworkModule.service().deleteDataCategory(id ?: 0)
        hapus.enqueue(object : Callback<ResponseActions> {
            override fun onResponse(
                call: Call<ResponseActions>,
                response: Response<ResponseActions>
            ) {
                Toast.makeText(
                    applicationContext,
                    "Data is sucessfully deleted",
                    Toast.LENGTH_SHORT
                ).show()
                showCategorylist()
            }

            override fun onFailure(call: Call<ResponseActions>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }


    override fun onResume() {
        super.onResume()
        showCategorylist()
    }


}
