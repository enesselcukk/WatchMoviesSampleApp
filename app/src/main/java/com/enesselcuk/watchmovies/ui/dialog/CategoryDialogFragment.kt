package com.enesselcuk.watchmovies.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.enesselcuk.watchmovies.R
import com.enesselcuk.watchmovies.databinding.FragmentDialogBinding
import com.enesselcuk.watchmovies.util.CategoryName.NOW_PLAYING
import com.enesselcuk.watchmovies.util.CategoryName.POPULAR
import com.enesselcuk.watchmovies.util.CategoryName.TOPRATED
import com.enesselcuk.watchmovies.util.CategoryName.UPCOMING
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_dialog,
            container, false
        )

        with(binding) {
            btnClose.setOnClickListener {
                dismiss()
            }
            textNowPlaying.setOnClickListener {
                val action = CategoryDialogFragmentDirections
                    .actionCategoryDialogFragmentToCategoryFragment(NOW_PLAYING)
                findNavController().navigate(action)
            }
            popular.setOnClickListener {
                val action = CategoryDialogFragmentDirections
                    .actionCategoryDialogFragmentToCategoryFragment(POPULAR)
                findNavController().navigate(action)
            }
            topRated.setOnClickListener {
                val action = CategoryDialogFragmentDirections
                    .actionCategoryDialogFragmentToCategoryFragment(TOPRATED)
                findNavController().navigate(action)
            }
            upComing.setOnClickListener {
                val action = CategoryDialogFragmentDirections
                    .actionCategoryDialogFragmentToCategoryFragment(UPCOMING)
                findNavController().navigate(action)
            }
        }
        return binding.root
    }

    override fun getTheme(): Int = R.style.custom_alert_dialog
}