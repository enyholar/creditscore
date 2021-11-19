package com.gideondev.creditscore.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.gideondev.creditscore.R
import com.gideondev.creditscore.base.BaseActivity
import com.gideondev.creditscore.viewModel.CreditScoreViewModel
import com.gideondev.creditscore.viewModel.Status

class MainActivity : BaseActivity() {
    lateinit var viewModel: CreditScoreViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun configureDesign() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(CreditScoreViewModel::class.java)
        observeData()
        fetchCreditScoreDataFromServer()
    }

    private fun fetchCreditScoreDataFromServer(){
        viewModel.getCreditScoreFromServer()

    }

    private fun observeData(){
        viewModel.creditScoreResponse
            .observe(this, {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { users ->
                            Log.e("credit response", "response : ${it.toString()}")
                            print(it.toString())

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
