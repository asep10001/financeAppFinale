package id.kotlin.financeapp.Model.getData.Expenses

import com.google.gson.annotations.SerializedName

data class ResponseExpenses(

	@field:SerializedName("data")
	val data: List<DataExpenses>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)