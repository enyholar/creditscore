package com.gideondev.creditscore.ui.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import app.futured.donut.DonutSection
import com.gideondev.creditscore.R
import com.gideondev.creditscore.databinding.ActivityCreditScoreDetailsBinding
import com.gideondev.creditscore.model.CreditScoreResponse

class CreditScoreDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreditScoreDetailsBinding
    lateinit var model:  CreditScoreResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_credit_score_details)
        getData()
        binding.imgBack.setOnClickListener{
            finish()
        }
    }

    fun getData(){
        model = intent.getSerializableExtra("model") as CreditScoreResponse
        setValueToView()
    }

    fun setValueToView(){
        if(model != null){
            binding.txtDivStatus.text = model.accountIDVStatus
            binding.txtDivStatus.text = model.accountIDVStatus
            binding.txtCreditScore.text = "Your credit score is ${model.creditReportInfo!!.score}"
            binding.txtMaxScore.text = "out of  ${model.creditReportInfo!!.maxScoreValue}"
            binding.txtEquifaxDescription.text = model.creditReportInfo!!.equifaxScoreBandDescription
            binding.txtPercentageOfCreditUsed.text = model.creditReportInfo!!.percentageCreditUsed.toString()
            binding.txtPersonalType.text = model.personaType
            val resultScore: Double = model.creditReportInfo!!.score!!.toDouble()/100
            val infoMaxScore: Double = model.creditReportInfo!!.maxScoreValue!!.toDouble()/100
            println(resultScore.toString())
            val section1 = DonutSection(
                name = "section_1",
                color = Color.parseColor("#FAC66E"),
                amount = resultScore.toFloat()
            )

            binding.donutView.cap = infoMaxScore.toFloat()
            binding.donutView.submitData(listOf(section1))
        }
    }
}