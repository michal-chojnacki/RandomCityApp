package com.github.michalchojnacki.randomcityapp.ui.citydatadetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.github.michalchojnacki.randomcityapp.databinding.CityDataDetailsFragmentBinding;
import com.github.michalchojnacki.randomcityapp.domain.model.CityData;
import com.github.michalchojnacki.randomcityapp.ui.common.BaseFragment;

import dagger.hilt.android.AndroidEntryPoint;

import static com.github.michalchojnacki.randomcityapp.ui.citydatadetails.CityDataDetailsViewModel.ARG_CITY_DATA;

@AndroidEntryPoint
public class CityDataDetailsFragment extends BaseFragment {

    private CityDataDetailsViewModel mViewModel;

    public static CityDataDetailsFragment newInstance(@NonNull CityData cityData) {
        CityDataDetailsFragment fragment = new CityDataDetailsFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_CITY_DATA, cityData);

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CityDataDetailsFragmentBinding binding = CityDataDetailsFragmentBinding.inflate(inflater, container, false);

        mViewModel = new ViewModelProvider(this).get(CityDataDetailsViewModel.class);
        binding.setViewModel(mViewModel);

        return binding.getRoot();
    }

    @Override
    public boolean getDisplayHomeAsUpEnabled() {
        return true;
    }

    @Nullable
    @Override
    public String getToolbarTitle() {
        if (mViewModel == null) {
            return null;
        }
        return mViewModel.getToolbarTitle();
    }

    @Nullable
    @ColorInt
    @Override
    public Integer getToolbarColor() {
        if (mViewModel == null) {
            return null;
        }
        return mViewModel.getToolbarTitleColor();
    }
}
