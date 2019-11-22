/*
 * Copyright (C) 2018 Marcus Pimenta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.pimenta.bestv.feature.main.presentation.ui.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.os.bundleOf
import androidx.leanback.R
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.pimenta.bestv.common.extension.addFragment
import com.pimenta.bestv.model.presentation.model.GenreViewModel
import com.pimenta.bestv.model.presentation.model.WorkViewModel
import com.pimenta.bestv.common.presentation.ui.fragment.ErrorFragment
import com.pimenta.bestv.feature.main.di.GenreWorkGridFragmentComponent
import com.pimenta.bestv.feature.main.presentation.presenter.GenreGridPresenter
import com.pimenta.bestv.feature.workdetail.presentation.ui.activity.WorkDetailsActivity
import com.pimenta.bestv.feature.workdetail.presentation.ui.fragment.WorkDetailsFragment
import com.pimenta.bestv.model.presentation.model.loadBackdrop
import javax.inject.Inject

/**
 * Created by marcus on 11-02-2018.
 */
private const val GENRE = "GENRE"
private const val ERROR_FRAGMENT_REQUEST_CODE = 1

class GenreWorkGridFragment : BaseWorkGridFragment(), GenreGridPresenter.View {

    private val genreViewModel by lazy { arguments?.getSerializable(GENRE) as GenreViewModel }

    @Inject
    lateinit var presenter: GenreGridPresenter

    override fun onAttach(context: Context) {
        GenreWorkGridFragmentComponent.create(this, requireActivity().application)
                .inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.bindTo(lifecycle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadWorkByGenre(genreViewModel)
    }

    override fun lastRowLoaded() {
        presenter.loadWorkByGenre(genreViewModel)
    }

    override fun workSelected(workSelected: WorkViewModel) {
        presenter.countTimerLoadBackdropImage(workSelected)
    }

    override fun workClicked(itemViewHolder: Presenter.ViewHolder, workViewModel: WorkViewModel) {
        presenter.workClicked(itemViewHolder, workViewModel)
    }

    override fun onShowProgress() {
        progressBarManager.show()
    }

    override fun onHideProgress() {
        progressBarManager.hide()
    }

    override fun onWorksLoaded(works: List<WorkViewModel>) {
        rowsAdapter.setItems(works, workDiffCallback)
        mainFragmentAdapter.fragmentHost.notifyDataReady(mainFragmentAdapter)
    }

    override fun loadBackdropImage(workViewModel: WorkViewModel) {
        workViewModel.loadBackdrop(requireNotNull(context), object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                backgroundManager?.setBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                // DO ANYTHING
            }
        })
    }

    override fun onErrorWorksLoaded() {
        val fragment = ErrorFragment.newInstance().apply {
            setTargetFragment(this@GenreWorkGridFragment, ERROR_FRAGMENT_REQUEST_CODE)
        }
        fragmentManager?.addFragment(R.id.scale_frame, fragment, ErrorFragment.TAG)
    }

    override fun openWorkDetails(itemViewHolder: Presenter.ViewHolder, workViewModel: WorkViewModel) {
        val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireNotNull(activity),
                (itemViewHolder.view as ImageCardView).mainImageView,
                WorkDetailsFragment.SHARED_ELEMENT_NAME
        ).toBundle()
        startActivity(WorkDetailsActivity.newInstance(requireContext(), workViewModel), bundle)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            ERROR_FRAGMENT_REQUEST_CODE -> {
                fragmentManager?.popBackStack(ErrorFragment.TAG, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
                if (resultCode == Activity.RESULT_OK) {
                    presenter.loadWorkByGenre(genreViewModel)
                }
            }
        }
    }

    companion object {

        fun newInstance(genreViewModel: GenreViewModel) =
                GenreWorkGridFragment().apply {
                    arguments = bundleOf(
                            GENRE to genreViewModel
                    )
                }
    }
}