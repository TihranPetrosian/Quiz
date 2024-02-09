/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.coroutines

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.coroutines.R
import com.example.coroutines.databinding.FragmentGameWonBinding
import com.example.coroutines.utils.viewBinding
import androidx.navigation.ui.setupWithNavController



class GameWonFragment : Fragment() {

    val binding: FragmentGameWonBinding by viewBinding()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.nextMatchButton.setOnClickListener {
            findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment2())
        }

        (requireActivity() as MainActivity).toolbar.inflateMenu(R.menu.winner_menu)

        (requireActivity() as MainActivity).toolbar.menu.findItem(R.id.share).setOnMenuItemClickListener {
            shareSuccess()
            true
        }
    }

    override fun onDestroyView() {
        (requireActivity() as MainActivity).toolbar.menu.clear()
        super.onDestroyView()
    }

    private fun getShareIntent(): Intent {
        var args = GameWonFragmentArgs.fromBundle(requireArguments())

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            getString(R.string.share_success_text, args.numCorrect, args.numQuestions)
        )

        return Intent.createChooser(shareIntent, getString(R.string.share_via))
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
}
