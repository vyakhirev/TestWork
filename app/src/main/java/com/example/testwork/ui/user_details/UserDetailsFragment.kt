package com.example.testwork.ui.user_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testwork.MainActivity
import com.example.testwork.R
import com.example.testwork.databinding.UserDetailsFragmentBinding
import com.example.testwork.util.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment() : Fragment(R.layout.user_details_fragment) {

    private val args: UserDetailsFragmentArgs by navArgs()
    private val binding by viewBinding(UserDetailsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.apply {
            title = "Подробная информация"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(false)
        }

        binding.avatarIV.loadImage(args.user.avatar)
        binding.emailTV.text = args.user.email
        binding.firstNameOnDetailsTV.text = "${args.user.first_name} ${args.user.last_name}"
    }
}