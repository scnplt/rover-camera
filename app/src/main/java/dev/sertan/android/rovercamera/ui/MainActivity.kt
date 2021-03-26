package dev.sertan.android.rovercamera.ui

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import dev.sertan.android.rovercamera.R
import dev.sertan.android.rovercamera.databinding.ActivityMainBinding
import dev.sertan.android.rovercamera.ui.base.BaseActivity
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
    }
}