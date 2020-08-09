package com.nav.greenhousecontoller.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.nav.greenhousecontoller.R;


public class ViewGreenHouseFragment extends Fragment {
    private TextView greenHouseInfButton;
    private TextView greenHouseChangeParamsButton;
    private TextView greenHouseChangeSysParamsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_green_house, container, false);
        greenHouseInfButton = view.findViewById(R.id.greenhouse_inf_but);
        greenHouseChangeParamsButton = view.findViewById(R.id.change_sys_params_but);
        greenHouseChangeSysParamsButton = view.findViewById(R.id.system_inf_but);
        long id = getArguments().getLong("id");
        greenHouseInfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("id", id);
                Navigation.findNavController(view).navigate(R.id.action_viewGreenHouseFragment_to_viewGreenHouseInformationFragment, bundle);
            }
        });
        greenHouseChangeParamsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("id", id);
                Navigation.findNavController(view).navigate(R.id.action_viewGreenHouseFragment_to_viewGreenHouseChangeInformationFragment, bundle);
            }
        });
        greenHouseChangeSysParamsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("id", id);
                Navigation.findNavController(view).navigate(R.id.action_viewGreenHouseFragment_to_viewChangeSystemInformationFragment, bundle);
            }
        });
        return view;
    }
}