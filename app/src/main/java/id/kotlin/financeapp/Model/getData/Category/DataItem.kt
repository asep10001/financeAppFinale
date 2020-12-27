package id.kotlin.financeapp.Model.getData.Category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem(
//
//	@field:SerializedName("incomes")
//	val incomes: List<IncomesItem?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

//	@field:SerializedName("expenses")
//	val expenses: List<ExpensesItem?>? = null
) : Parcelable