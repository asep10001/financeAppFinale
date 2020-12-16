package id.kotlin.financeapp.Model.Data.Income

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccsess")
	val isSuccsess: Boolean? = null
)