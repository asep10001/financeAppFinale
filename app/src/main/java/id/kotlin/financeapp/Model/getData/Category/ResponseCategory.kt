package id.kotlin.financeapp.Model.getData.Category

import com.google.gson.annotations.SerializedName

data class ResponseCategory(

	@field:SerializedName("data")
	val data: List<DataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)