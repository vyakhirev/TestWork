package com.example.testwork.ui.about

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testwork.R
import com.example.testwork.databinding.AboutFragmentBinding

class AboutFragment():Fragment(R.layout.about_fragment) {
    private val binding by viewBinding(AboutFragmentBinding::bind)
}