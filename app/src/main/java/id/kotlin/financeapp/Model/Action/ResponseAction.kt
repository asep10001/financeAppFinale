package id.kotlin.financeapp.Model.Action

import com.google.gson.annotations.SerializedName

data class ResponseAction(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccsess")
	val isSuccsess: Boolean? = null
)