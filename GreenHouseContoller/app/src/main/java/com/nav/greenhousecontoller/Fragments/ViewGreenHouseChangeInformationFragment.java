package com.nav.greenhousecontoller.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.nav.greenhousecontoller.GreenhouseInterval;
import com.nav.greenhousecontoller.R;
import com.nav.greenhousecontoller.SpinnerHelper;
import com.nav.greenhousecontoller.integration.GreenHouseServerParams;
import com.nav.greenhousecontoller.integration.greenHouseLimits.GreenHouseLimitsService;
import com.nav.greenhousecontoller.model.GreenHouseLimits;
import com.nav.greenhousecontoller.retrofit.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewGreenHouseChangeInformationFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner downTempSpinner;
    private Spinner upTempSpinner;
    private Spinner downMoistSpinner;
    private Spinner upMoistSpinner;
    private Spinner downLightSpinner;
    private Spinner upLightSpinner;
    private GreenHouseLimits greenHouseLimits = new GreenHouseLimits(-1, 22, 25, 400, 800, 500, 900);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_green_house_change_information, container, false);
        long id = getArguments().getLong("id");
        getTempSpinners(view);
        getLightSpinners(view);
        getMoistSpinners(view);
        organizeSpinners();
        returnPreviousState();
        greenHouseLimits.setId(id);
        updateChangerInformationForGreenHouse(greenHouseLimits);
        return view;
    }

    private void returnPreviousState() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            int upTempPos = sharedPreferences.getInt("upTemp", 0);
            upTempSpinner.setSelection(upTempPos);
            int downTempPos = sharedPreferences.getInt("downTemp", 0);
            downTempSpinner.setSelection(downTempPos);
            int upLightPos = sharedPreferences.getInt("upLight", 0);
            upLightSpinner.setSelection(upLightPos);
            int downLightPos = sharedPreferences.getInt("downLight", 0);
            downLightSpinner.setSelection(downLightPos);
            int upMoistPos = sharedPreferences.getInt("upMoist", 0);
            upMoistSpinner.setSelection(upMoistPos);
            int downMoistPos = sharedPreferences.getInt("downMoist", 0);
            downMoistSpinner.setSelection(downMoistPos);

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateTempLimits();
        updateLightLimit();
        updateMoistLimit();
    }

    private void updateMoistLimit() {
        downMoistSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int value = (int) parent.getItemAtPosition(position);
                if (value > greenHouseLimits.getUpMoistureLimit()) {
                    Toast toast = Toast.makeText(getContext(), "Down moisture limit Can't be Bigger Then up Moisture Limit", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                greenHouseLimits.setDownMoistureLimit(value);
                saveState("downMoist", position);
                updateChangerInformationForGreenHouse(greenHouseLimits);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        upMoistSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int value = (int) parent.getItemAtPosition(position);
                if (value < greenHouseLimits.getDownMoistureLimit()) {
                    Toast toast = Toast.makeText(getContext(), "Up moisture limit Can't be Smaller Then Down Moisture Limit", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                greenHouseLimits.setUpMoistureLimit(value);
                saveState("upMoist", position);
                updateChangerInformationForGreenHouse(greenHouseLimits);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateLightLimit() {
        downLightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int value = (int) parent.getItemAtPosition(position);
                if (value > greenHouseLimits.getUpLightLimit()) {
                    Toast toast = Toast.makeText(getContext(), "Down light limit Can't be Bigger Then up light Limit", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                greenHouseLimits.setDownLightLimit(value);
                saveState("downLight", position);
                updateChangerInformationForGreenHouse(greenHouseLimits);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        upLightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int value = (int) parent.getItemAtPosition(position);
                if (value < greenHouseLimits.getDownLightLimit()) {
                    Toast toast = Toast.makeText(getContext(), "Up light limit Can't be Smaller Then Down light Limit", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                greenHouseLimits.setUpLightLimit(value);
                saveState("upLight", position);
                updateChangerInformationForGreenHouse(greenHouseLimits);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateTempLimits() {
        downTempSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int value = (int) parent.getItemAtPosition(position);
                if (value > greenHouseLimits.getUpTemperatureLimit()) {
                    Toast toast = Toast.makeText(getContext(), "Down Temperature limit Can't be Bigger Then up light Temperature", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                greenHouseLimits.setDownTemperatureLimit(value);
                saveState("downTemp", position);
                updateChangerInformationForGreenHouse(greenHouseLimits);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        upTempSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int value = (int) parent.getItemAtPosition(position);
                if (value < greenHouseLimits.getDownTemperatureLimit()) {
                    Toast toast = Toast.makeText(getContext(), "Up Temperature limit Can't be Smaller Then Down Temperature Limit", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                greenHouseLimits.setUpTemperatureLimit(value);
                saveState("upTemp", position);
                updateChangerInformationForGreenHouse(greenHouseLimits);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void organizeSpinners() {
        SpinnerHelper.setTempIntervalToSpinner(getActivity(), upTempSpinner, downTempSpinner, SpinnerHelper.getTemperatureInterval(),
                GreenhouseInterval.DOWN_TEMP_LIMIT, GreenhouseInterval.UP_TEMP_LIMIT);
        SpinnerHelper.setLightIntervalToSpinner(getActivity(), upLightSpinner, downLightSpinner, SpinnerHelper.getLightInterval(),
                GreenhouseInterval.DOWN_LIGHT_LIMIT, GreenhouseInterval.UP_LIGHT_LIMIT);
        SpinnerHelper.setMoistIntervalToSpinner(getActivity(), upMoistSpinner, downMoistSpinner, SpinnerHelper.getMoistureInterval(),
                GreenhouseInterval.DOWN_MOIST_LIMIT, GreenhouseInterval.UP_MOIST_LIMIT);
    }


    private void getMoistSpinners(View view) {
        ConstraintLayout constraintLayout = view.findViewById(R.id.main_fragment_id);
        ConstraintLayout tempConstraintLayout = constraintLayout.findViewById(R.id.moist_const);
        ConstraintLayout lastConstraintLayout = tempConstraintLayout.findViewById(R.id.include_moist);
        ConstraintLayout downMoist = lastConstraintLayout.findViewById(R.id.down_moist_spinner_constr);
        ConstraintLayout upMoist = lastConstraintLayout.findViewById(R.id.up_moist_spinner_constraint);
        downMoistSpinner = downMoist.findViewById(R.id.down_moist_spinner);
        upMoistSpinner = upMoist.findViewById(R.id.up_moist_spinner);
    }

    private void getLightSpinners(View view) {
        ConstraintLayout constraintLayout = view.findViewById(R.id.main_fragment_id);
        ConstraintLayout tempConstraintLayout = constraintLayout.findViewById(R.id.light_const);
        ConstraintLayout lastConstraintLayout = tempConstraintLayout.findViewById(R.id.include_light);
        ConstraintLayout downLight = lastConstraintLayout.findViewById(R.id.down_light_spinner_constr);
        ConstraintLayout upLight = lastConstraintLayout.findViewById(R.id.up_light_spinner_constraint);
        downLightSpinner = downLight.findViewById(R.id.down_light_spinner);
        upLightSpinner = upLight.findViewById(R.id.up_light_spinner);
    }

    private void getTempSpinners(View view) {
        ConstraintLayout constraintLayout = view.findViewById(R.id.main_fragment_id);
        ConstraintLayout tempConstraintLayout = constraintLayout.findViewById(R.id.temp_constraint);
        ConstraintLayout lastConstraintLayout = tempConstraintLayout.findViewById(R.id.include_Temp);
        ConstraintLayout downTemp = lastConstraintLayout.findViewById(R.id.down_temp_spinner_constr);
        ConstraintLayout upTemp = lastConstraintLayout.findViewById(R.id.up_temp_spinner_constraint);
        downTempSpinner = downTemp.findViewById(R.id.temp_down_spinner);
        upTempSpinner = upTemp.findViewById(R.id.up_temp_spinner);
    }

    private void saveState(String name, int param) {
        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        edt.putInt(name, param);
        edt.commit();
    }

    private void updateChangerInformationForGreenHouse(GreenHouseLimits greenHouseLimits) {
        GreenHouseLimitsService greenHouseLimitsService = RetrofitBuilder.getGreenHouseLimitsService(GreenHouseServerParams.URL);
        greenHouseLimitsService.updateGreenHouseLimits(greenHouseLimits).enqueue(new Callback<GreenHouseLimits>() {
            @Override
            public void onResponse(Call<GreenHouseLimits> call, Response<GreenHouseLimits> response) {

            }

            @Override
            public void onFailure(Call<GreenHouseLimits> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}