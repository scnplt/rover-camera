package dev.sertan.android.rovercamera.ui

import dagger.hilt.android.AndroidEntryPoint
import dev.sertan.android.rovercamera.R
import dev.sertan.android.rovercamera.databinding.ActivityMainBinding
import dev.sertan.android.rovercamera.ui.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main
}