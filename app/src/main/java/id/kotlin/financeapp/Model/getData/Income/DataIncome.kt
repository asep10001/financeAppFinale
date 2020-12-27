package id.kotlin.financeapp.Model.getData.Income

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataIncome(

	@field:SerializedName("transaction_date")
	val transactionDate: String? = null,

	@field:SerializedName("category_name")
	val categoryName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("ammount")
	val ammount: Double? = null
) : Parcelable