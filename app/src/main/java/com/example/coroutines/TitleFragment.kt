package com.example.coroutines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.coroutines.databinding.FragmentTitleBinding
import com.example.coroutines.utils.viewBinding


class TitleFragment : Fragment() {

    val binding: FragmentTitleBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.playButton.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }
        binding.toolbar.setupWithNavController(findNavController())

        binding.toolbar.menu.findItem(R.id.aboutFragment).setOnMenuItemClickListener {
            findNavController().navigate(R.id.action_titleFragment_to_aboutFragment)
            true
        }

    }


}