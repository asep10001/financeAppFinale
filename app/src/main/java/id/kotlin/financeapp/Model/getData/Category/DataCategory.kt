package id.kotlin.financeapp.Model.getData.Category

import com.google.gson.annotations.SerializedName

data class DataCategory(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)