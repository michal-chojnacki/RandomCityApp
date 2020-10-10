package com.github.michalchojnacki.randomcityapp.ui.citydatadetails;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.github.michalchojnacki.randomcityapp.R;
import com.github.michalchojnacki.randomcityapp.databinding.CityDataDetailsFragmentBinding;
import com.github.michalchojnacki.randomcityapp.domain.model.CityData;
import com.github.michalchojnacki.randomcityapp.ui.common.AppHelpers;
import com.github.michalchojnacki.randomcityapp.ui.common.BaseFragment;
import com.google.android.libraries.maps.CameraUpdateFactory;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.LatLng;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

import static com.github.michalchojnacki.randomcityapp.ui.citydatadetails.CityDataDetailsViewModel.ARG_CITY_DATA;

@AndroidEntryPoint
public class CityDataDetailsFragment extends BaseFragment implements OnMapReadyCallback {
    private static final int MAP_ZOOM_VALUE = 10;

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
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NotNull View view, @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean getDisplayHomeAsUpEnabled() {
        if (getActivity() == null) {
            return true;
        }
        return !AppHelpers.getTwoPane(getActivity());
    }

    @Nullable
    @Override
    public String getToolbarTitle() {
        if (mViewModel == null) {
            return null;
        }
        return mViewModel.getCityName();
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

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        LatLng cityLatLng = findCityLatLng(mViewModel.getCityNameWithCountry());
        if (cityLatLng != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cityLatLng, MAP_ZOOM_VALUE));
        } else {
            mViewModel.onErrorLoadingMap();
        }
    }

    @Nullable
    private LatLng findCityLatLng(@NonNull String cityNameWithCountry) {
        Geocoder geocoder = new Geocoder(requireContext(), Locale.forLanguageTag("pl"));
        try {
            List<Address> addresses = geocoder.getFromLocationName(cityNameWithCountry, 1);
            if (!addresses.isEmpty()) {
                return new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
