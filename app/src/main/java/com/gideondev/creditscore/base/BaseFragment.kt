package com.gideondev.creditscore.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gideondev.creditscore.di.viewModelFactory.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureDaggers()
        configureDesign()
    }
    private fun configureDaggers() = (requireActivity().application as BaseApplication).appComponent.inject(this)


    abstract fun configureDesign()
}