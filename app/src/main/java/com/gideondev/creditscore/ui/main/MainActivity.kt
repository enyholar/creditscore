package com.gideondev.creditscore.ui.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.futured.donut.DonutSection
import com.gideondev.creditscore.R
import com.gideondev.creditscore.base.BaseActivity
import com.gideondev.creditscore.databinding.ActivityMainBinding
import com.gideondev.creditscore.model.CreditScoreResponse
import com.gideondev.creditscore.ui.detail.CreditScoreDetailsActivity
import com.gideondev.creditscore.viewModel.CreditScoreViewModel
import com.gideondev.creditscore.viewModel.Status

class MainActivity : BaseActivity() {
    lateinit var viewModel: CreditScoreViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var creditScore: CreditScoreResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainView.setOnClickListener {
            val intent = Intent(this@MainActivity, CreditScoreDetailsActivity::class.java)
            intent.putExtra("model",creditScore)
            startActivity(intent)

        }
    }

    override fun configureDesign() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(CreditScoreViewModel::class.java)
        observeData()
        fetchCreditScoreDataFromServer()
    }

    fun setDataToView(score: Int,maxScore: Int){
        binding.txtCreditScore.text = score.toString()
        binding.txtMaxScore.text = "Out of $maxScore"
        val resultScore: Double = score.toDouble()/100
        val infoMaxScore: Double = maxScore.toDouble()/100
        println(resultScore.toString())
        val section1 = DonutSection(
            name = "section_1",
            color = Color.parseColor("#FAC66E"),
            amount = resultScore.toFloat()
        )

        binding.donutView.cap = infoMaxScore.toFloat()
        binding.donutView.submitData(listOf(section1))
    }

    private fun fetchCreditScoreDataFromServer(){
        viewModel.getCreditScoreFromServer()

    }

    private fun observeData(){
        viewModel.creditScoreResponse
            .observe(this, {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { creditReport ->
                            Log.e("credit response", "response : ${it.toString()}")
                            print(it.toString())
                            creditScore = creditReport
                            setDataToView(creditReport.creditReportInfo?.score!!,creditReport.creditReportInfo?.maxScoreValue!!)

                        }

                    }
                    Status.LOADING -> {
                        println("Loading")
                    }
                    Status.ERROR -> {
                        //Handle Error
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            })


    }
}
