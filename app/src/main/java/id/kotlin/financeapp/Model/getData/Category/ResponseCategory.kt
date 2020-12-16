package id.kotlin.financeapp.Model.getData.Category

import com.google.gson.annotations.SerializedName

data class ResponseCategory(

	@field:SerializedName("data")
	val data: List<DataCategory?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccsess")
	val isSuccsess: Boolean? = null
)