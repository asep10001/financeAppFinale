package id.kotlin.financeapp.Config

import id.kotlin.financeapp.Model.getData.Category.ResponseCategory
import id.kotlin.financeapp.Model.getData.Expenses.ResponseExpenses
import id.kotlin.financeapp.Model.getData.Income.ResponseIncome
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    //getDataCategory
    @GET("category/getData.php")
    fun getDataCategory(): Call<ResponseCategory>

    //getDataIncome
    @GET("income/getData.php")
    fun getDataIncome(): Call<ResponseIncome>

    //getDataIncome
    @GET("expenses/getData.php")
    fun getDataExpenses(): Call<ResponseExpenses>

}