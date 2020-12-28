package id.kotlin.financeapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.kotlin.financeapp.Model.getData.Income.DataIncome
import id.kotlin.financeapp.R

class ShowAllInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all_input)
        val getIncomeData = intent.getParcelableExtra<DataIncome>("dataIncome");

    }
}
