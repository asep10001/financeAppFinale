package id.kotlin.financeapp.Model.getData.Expenses

import com.google.gson.annotations.SerializedName

data class DataExpenses(

	@field:SerializedName("trans_date")
	val transDate: String? = null,

	@field:SerializedName("category_id")
	val categoryId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("sum")
	val sum: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)