package id.kotlin.financeapp.Model.actions

import com.google.gson.annotations.SerializedName

data class ResponseActions(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)