package com.example.lab_ta.presentations.activities

import android.os.Bundle
import com.example.lab_ta.base.BaseActivity
import com.example.lab_ta.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
    }


}