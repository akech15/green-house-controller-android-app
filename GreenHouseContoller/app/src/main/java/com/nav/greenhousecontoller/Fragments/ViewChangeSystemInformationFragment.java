package com.nav.greenhousecontoller.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nav.greenhousecontoller.R;
import com.nav.greenhousecontoller.integration.GreenHouseServerParams;
import com.nav.greenhousecontoller.integration.systemInf.GreenHouseSystemInfService;
import com.nav.greenhousecontoller.model.GreenHouseSystemInf;
import com.nav.greenhousecontoller.retrofit.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewChangeSystemInformationFragment extends Fragment {
    private static final int SYSTEM_ON = 1;
    private static final int SYSTEM_OFF = 0;
    private Switch conditionSwitch;
    private Switch lightSwitch;
    private Switch irrigationSwitch;
    private String id;
    private GreenHouseSystemInf greenHouseSystemInf = new GreenHouseSystemInf(1, 1, 1);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_change_system_information, container, false);
        conditionSwitch = view.findViewById(R.id.cond_switch);
        lightSwitch = view.findViewById(R.id.light_switch);
        irrigationSwitch = view.findViewById(R.id.irrigation_switch);
        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        if (pref != null) {
            boolean condition = pref.getBoolean("cond", true);
            boolean light = pref.getBoolean("light", true);
            boolean irr = pref.getBoolean("irr", true);
            returnConditioningState(condition);
            returnLightState(light);
            returnIrrState(irr);
        }
        assert getArguments() != null;
        id = getArguments().getString("id");
        updateSystemInformation(id, greenHouseSystemInf);
        return view;
    }

    private void returnIrrState(boolean irr) {
        if (irr) {
            greenHouseSystemInf.setIrrigationSystemOn(SYSTEM_ON);
        } else {
            greenHouseSystemInf.setIrrigationSystemOn(SYSTEM_OFF);
        }
        irrigationSwitch.setChecked(irr);
    }

    private void returnLightState(boolean light) {
        if (light) {
            greenHouseSystemInf.setLightOn(SYSTEM_ON);
        } else {
            greenHouseSystemInf.setLightOn(SYSTEM_OFF);
        }
        lightSwitch.setChecked(light);
    }

    private void returnConditioningState(boolean condition) {
        if (condition) {
            greenHouseSystemInf.setConditioningOn(SYSTEM_ON);
        } else {
            greenHouseSystemInf.setConditioningOn(SYSTEM_OFF);
        }
        conditionSwitch.setChecked(condition);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateConditioningSys();
        updateLightningSys();
        updateIrrigationSys();
    }

    private void saveState(String name, boolean state) {
        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        edt.putBoolean(name, state);
        edt.commit();
    }

    private void updateIrrigationSys() {
        irrigationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    greenHouseSystemInf.setIrrigationSystemOn(SYSTEM_ON);
                } else {
                    greenHouseSystemInf.setIrrigationSystemOn(SYSTEM_OFF);
                }
                saveState("irr", isChecked);
                updateSystemInformation(id, greenHouseSystemInf);
            }
        });
    }

    private void updateLightningSys() {
        lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    greenHouseSystemInf.setLightOn(SYSTEM_ON);
                } else {
                    greenHouseSystemInf.setLightOn(SYSTEM_OFF);
                }
                saveState("light", isChecked);
                updateSystemInformation(id, greenHouseSystemInf);
            }
        });
    }

    private void updateConditioningSys() {
        conditionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    greenHouseSystemInf.setConditioningOn(SYSTEM_ON);
                } else {
                    greenHouseSystemInf.setConditioningOn(SYSTEM_OFF);
                }
                saveState("cond", isChecked);
                updateSystemInformation(id, greenHouseSystemInf);
            }
        });
    }

    private void updateSystemInformation(String id, GreenHouseSystemInf greenHouseSystemInf) {
        GreenHouseSystemInfService greenHouseSystemInfService = RetrofitBuilder.getGreenHouseSystemInfService(GreenHouseServerParams.URL);
        greenHouseSystemInfService.updateSystemInf(id, greenHouseSystemInf).enqueue(new Callback<GreenHouseSystemInf>() {
            @Override
            public void onResponse(Call<GreenHouseSystemInf> call, Response<GreenHouseSystemInf> response) {

            }

            @Override
            public void onFailure(Call<GreenHouseSystemInf> call, Throwable t) {

            }
        });
    }
}