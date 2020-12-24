package id.kotlin.financeapp.Config

import id.kotlin.financeapp.Model.actions.ResponseActions
import id.kotlin.financeapp.Model.getData.Category.ResponseCategory
import id.kotlin.financeapp.Model.getData.Expenses.ResponseExpenses
import id.kotlin.financeapp.Model.getData.Income.ResponseIncome
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    //getDataCategory
    @GET("categories")
    fun getDataCategory(): Call<ResponseCategory>

    //getDataIncome
    @GET("incomes")
    fun getDataIncome(): Call<ResponseIncome>

    //insert
    @Headers("Content-Type: application/json")
    @POST("incomes")
    fun insertDataIncome(
        @Query("categoryId") category_id: Long,
        @Body requestBody: RequestBody
    ): Call<ResponseActions>

    @PUT("incomes/{incomeId}")
    fun updateDataIncome(
        @Path("incomeId") income_id: Long,
        @Query("categoryId") category_id : Long,
        @Body requestBody: RequestBody
    ): Call<ResponseActions>

    @DELETE("incomes")
    fun deleteDataIncome(@Query("incomeId") income_id: Long): Call<ResponseActions>

    //getDataIncome
    @GET("expenses/getData.php")
    fun getDataExpenses(): Call<ResponseExpenses>

    @DELETE("categories")
     fun deleteDataCategory(@Query("categoryId") category_id: Long): Call<ResponseActions>

}