package id.kotlin.financeapp.Model.getData.Income

import com.google.gson.annotations.SerializedName

data class ResponseIncome(

	@field:SerializedName("data")
	val data: List<DataIncome>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccsess")
	val isSuccsess: Boolean? = null
)