package id.kotlin.financeapp.Adapters.DetailCategoryAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.kotlin.financeapp.Adapters.ExpensesAdapter
import id.kotlin.financeapp.Adapters.IncomeAdapter
import id.kotlin.financeapp.Model.getData.Expenses.DataExpenses
import id.kotlin.financeapp.Model.getData.Income.DataIncome
import id.kotlin.financeapp.R
import kotlinx.android.synthetic.main.list_income.view.*

class ExpensesDetailAdapter (val data: List<DataExpenses>?, val itemClick: ExpensesAdapter.OnClickListener) :
    RecyclerView.Adapter<ExpensesDetailAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val expensesName = view.textIncomeName
        val expensesSum = view.textIncomeSum
        val expensesDate = view.textIncomeDate
        val expensesImage = view.textIncomeImage
        val btnHapusExpenses = view.btnHapus
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_income, parent, false)

        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.expensesName.text = item?.name
        holder.expensesSum.text = item?.ammount.toString()
        holder.expensesDate.text = item?.transactionDate
        Glide.with(holder.view.context).load(item?.image).into(holder.expensesImage)

        holder.view.setOnClickListener {
            itemClick.detail(item)
        }

        holder.btnHapusExpenses.setOnClickListener {
            itemClick.hapusData(item)
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
}