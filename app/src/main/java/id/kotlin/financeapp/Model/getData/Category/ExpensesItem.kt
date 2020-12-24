package id.kotlin.financeapp.Model.getData.Category

import com.google.gson.annotations.SerializedName

data class ExpensesItem(

	@field:SerializedName("transaction_date")
	val transactionDate: String? = null,

	@field:SerializedName("category_name")
	val categoryName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("ammount")
	val ammount: Int? = null
)