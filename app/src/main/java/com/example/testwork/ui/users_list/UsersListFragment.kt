package com.example.testwork.ui.users_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testwork.MainActivity
import com.example.testwork.R
import com.example.testwork.databinding.UsersListBinding
import com.example.testwork.ui.user_adapter.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersListFragment : Fragment(R.layout.users_list) {

    private val viewModel: UserListViewModel by viewModels()
    private val binding by viewBinding(UsersListBinding::bind)

    private lateinit var usersAdapter: UsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.apply {
            title = "Список"
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }

        usersAdapter = UsersAdapter(listOf()) {
            val direction =
                UsersListFragmentDirections.actionUsersListFragmentToUserDetailsFragment(it)
            view.findNavController().navigate(direction)
        }

        binding.listUsersRV.apply {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.getUsers()

        viewModel.users.observe(
            viewLifecycleOwner,
            {
                usersAdapter.update(it)
            })

        viewModel.isViewLoading.observe(
            viewLifecycleOwner,
            {
                if (it.peekContent()) {
                    binding.usersLoadingPB.visibility = View.VISIBLE
                    binding.listUsersRV.visibility = View.GONE
                }
                else {
                    binding.usersLoadingPB.visibility = View.GONE
                }
            })

        viewModel.onMessageError.observe(
            viewLifecycleOwner,
            {
                if (it.peekContent()) {
                    binding.errorImg.visibility = View.VISIBLE
                    binding.errorTV.visibility = View.VISIBLE
                    binding.listUsersRV.visibility = View.GONE
                } else {
                    binding.errorImg.visibility = View.GONE
                    binding.errorTV.visibility = View.GONE
                    binding.listUsersRV.visibility = View.VISIBLE
                }
            }
        )

        binding.fromNetBtn.setOnClickListener {
            viewModel.getFromRemote()
        }

        binding.fromDbBtn.setOnClickListener {
            viewModel.getFromLocal()
        }
    }
}