package com.nav.greenhousecontoller.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.nav.greenhousecontoller.R;


public class ViewGreenHouseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_green_house, container, false);
        MaterialButton greenHouseInfButton = view.findViewById(R.id.greenhouse_inf_but);
        MaterialButton greenHouseChangeParamsButton = view.findViewById(R.id.change_sys_params_but);
        MaterialButton greenHouseChangeSysParamsButton = view.findViewById(R.id.system_inf_but);

        String id = requireArguments().getString("id");

        greenHouseInfButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            Navigation.findNavController(view).navigate(R.id.action_viewGreenHouseFragment_to_viewGreenHouseInformationFragment, bundle);
        });
        greenHouseChangeParamsButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            Navigation.findNavController(view).navigate(R.id.action_viewGreenHouseFragment_to_viewGreenHouseChangeInformationFragment, bundle);
        });
        greenHouseChangeSysParamsButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            Navigation.findNavController(view).navigate(R.id.action_viewGreenHouseFragment_to_viewChangeSystemInformationFragment, bundle);
        });

        return view;
    }
}