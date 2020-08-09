package com.nav.greenhousecontoller.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.nav.greenhousecontoller.R;
import com.nav.greenhousecontoller.integration.GreenHouseServerParams;
import com.nav.greenhousecontoller.integration.greenHouseLimits.GreenHouseLimitsService;
import com.nav.greenhousecontoller.model.GreenHouseLimits;
import com.nav.greenhousecontoller.retrofit.RetrofitBuilder;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewGreenHouseChangeInformationFragment extends Fragment {
    private Spinner downTempSpinner;
    private Spinner upTempSpinner;
    private Spinner downMoistSpinner;
    private Spinner upMoistSpinner;
    private Spinner downLightSpinner;
    private Spinner upLightSpinner;
    private GreenHouseLimits greenHouseLimits = new GreenHouseLimits(-1, new BigDecimal(22), new BigDecimal(25),
            new BigDecimal(400), new BigDecimal(600), new BigDecimal(500), new BigDecimal(1000));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_green_house_change_information, container, false);
        long id = getArguments().getLong("id");
        getTempSpinners(view);
        getLightSpinners(view);
        getMoistSpinners(view);
        greenHouseLimits.setId(id);
        updateChangerInformationForGreenHouse(greenHouseLimits);
        return view;
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

    private void saveState(String name, long param) {
        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        edt.putLong(name, param);
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
}