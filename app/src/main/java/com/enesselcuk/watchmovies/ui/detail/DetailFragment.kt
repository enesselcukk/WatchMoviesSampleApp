package com.enesselcuk.watchmovies.ui.detail


import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.enesselcuk.watchmovies.R
import com.enesselcuk.watchmovies.base.BaseFragment
import com.enesselcuk.watchmovies.databinding.FragmentDetailBinding
import com.enesselcuk.watchmovies.util.CategoryName.LANGUAGE
import com.enesselcuk.watchmovies.util.collectLatest
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    private val detailViewModel: DetailViewModel by activityViewModels()
    private val dataArgs: DetailFragmentArgs by navArgs()

    private var isLiked: Boolean? = false
    override fun definition() {
        backPressed()
        val moviesId = dataArgs.idToDetail

        detailViewModel.getDetail(moviesId, LANGUAGE)
        detailViewModel.getVideos(moviesId, LANGUAGE)
        detailViewModel.getFavoriteId(moviesId)
    }

    override fun setObserver() {
        collectLatest(detailViewModel.detailFlow, ::detailUiState)
        collectLatest(detailViewModel.videosFlow, ::videosUiState)
        collectLatest(detailViewModel.favoriteFlow, ::favoriteUiState)
    }

    private fun detailUiState(detailUiState: DetailUiState) {

        binding.setData = detailUiState
        detailUiState.detailResponse?.let { detailResponse ->
            binding.btnInsert.setOnClickListener {
                if (isLiked == true) {
                    isLiked = true
                    binding.btnInsert.setImageResource(R.drawable.ic_baseline_bookmark_add)
                } else {
                    isLiked = false
                    detailViewModel.moviesInsert(detailResponse.mapToFavorite())
                    binding.btnInsert.setImageResource(R.drawable.ic_baseline_bookmark_added)
                    Toast.makeText(
                        requireContext(),
                        "${detailResponse.title} added",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun favoriteUiState(moviesFavoriteEntity: FavoriteUiState) {
        moviesFavoriteEntity.favoriteUiState?.let { isLiked ->
            this.isLiked = isLiked.liked
            if (this.isLiked == true) {
                binding.btnInsert.setImageResource(R.drawable.ic_baseline_bookmark_added)
            } else {
                binding.btnInsert.setImageResource(R.drawable.ic_baseline_bookmark_add)
            }
        }
    }

    private fun videosUiState(videoUiState: VideoUiState) {
        videoUiState.videosResponse?.results?.map {
            with(binding) {
                youtubePlayerView.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        val defaultPlayerUiController =
                            DefaultPlayerUiController(youtubePlayerView, youTubePlayer)
                        youtubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)

                        val key = it.key
                        youTubePlayer.cueVideo(key, 0.0f)
                    }
                })
            }
        }
    }

    private fun backPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            }
        )
    }
}