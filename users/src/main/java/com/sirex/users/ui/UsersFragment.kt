package com.sirex.users.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sirex.common.base.BaseFragment
import com.sirex.common.utils.network.NetworkStatus
import com.sirex.common.views.InfiniteScrollListener
import com.sirex.core.extensions.coreComponent
import com.sirex.presentation.model.UserListItem
import com.sirex.presentation.viewmodel.UsersViewModel
import com.sirex.users.databinding.FragmentUsersBinding
import com.sirex.users.di.DaggerUsersComponent
import com.sirex.users.ui.UsersFragmentDirections
import javax.inject.Inject

class UsersFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: UsersViewModel by viewModels { factory }
    lateinit var binding: FragmentUsersBinding
    lateinit var adapter: UsersAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerUsersComponent.factory().create(coreComponent()).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(layoutInflater)
        val view = binding.root
        initRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllUsers()
        observeUsers()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = UsersAdapter(emptyList(), object : UserAdapterCallback {
            override fun onClick(userName: String?) {
                val action =
                    UsersFragmentDirections.actionUsersFragmentToUserDetailsFragment(userName ?: "")
                findNavController().navigate(action)
            }
        })
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvUsers.adapter = adapter
        binding.rvUsers.layoutManager = layoutManager
        binding.rvUsers.addOnScrollListener(object : InfiniteScrollListener(layoutManager) {
            override fun onLoadMore() {
                viewModel.getMoreUsers()
            }

            override fun isDataLoading(): Boolean {
                return viewModel.usersLiveData.value is NetworkStatus.Loading
            }

        })
    }

    private fun observeUsers() {
        viewModel.usersLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkStatus.Loading -> {
                    if (result.data.isNullOrEmpty().not()) {
                        hideLoading()
                        result.data?.let { getUsersSuccessful(it) }
                    } else showLoading()
                }
                is NetworkStatus.Error -> {
                    if (result.data.isNullOrEmpty().not()) {
                        hideLoading()
                        result.data?.let { getUsersSuccessful(it) }
                    } else {
                        hideLoading()
                        getRequestFailed(result.errorMessage)
                    }
                }
                is NetworkStatus.Success -> {
                    hideLoading()
                    result.data?.let { getUsersSuccessful(it) }
                }
            }
        }
    }

    private fun getUsersSuccessful(users: List<UserListItem>) {
        adapter.updateAdapter(users)
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }
}