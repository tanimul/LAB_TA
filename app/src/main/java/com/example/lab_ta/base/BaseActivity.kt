package com.example.lab_ta.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.lab_ta.R

abstract class BaseActivity<E: ViewBinding> : AppCompatActivity(){

    private var _binding: E? = null

    val binding: E get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setTheme(R.style.Theme_Lab_TA)
        setContentView(_binding!!.root)
        init(savedInstanceState)
    }


    protected abstract fun getViewBinding() : E
    protected abstract fun init(savedInstanceState: Bundle?)
}