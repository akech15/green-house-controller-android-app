package com.nav.greenhousecontoller.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.nav.greenhousecontoller.R;
import com.nav.greenhousecontoller.integration.GreenHouseServerParams;
import com.nav.greenhousecontoller.integration.greenHouse.GreenHouseService;
import com.nav.greenhousecontoller.model.GreenHouse;
import com.nav.greenhousecontoller.retrofit.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewGreenHouseInformationFragment extends Fragment {
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_green_house_information, container, false);
        long id = getArguments().getLong("id");
        textView = view.findViewById(R.id.green_house_inf_text);
        downloadInformation(id);
        return view;
    }

    private void downloadInformation(long id) {
        GreenHouseService greenHouseService = RetrofitBuilder.getGreenHouseService(GreenHouseServerParams.URL);
        greenHouseService.getGreenHouse(id).enqueue(new Callback<GreenHouse>() {
            @Override
            public void onResponse(Call<GreenHouse> call, Response<GreenHouse> response) {
                if (response.isSuccessful()) {
                    GreenHouse greenHouse = response.body();
                    assert greenHouse != null;
                    String temp = "Temperature: " + greenHouse.getTemperature() + "\n";
                    String hum = "Humidity: " + greenHouse.getHumidity() + "\n";
                    String moist = "Moisture: " + greenHouse.getMoisture() + "\n";
                    String light = "Light: " + greenHouse.getLight();
                    String result = temp + hum + moist + light;
                    textView.setText(result);
                }
            }

            @Override
            public void onFailure(Call<GreenHouse> call, Throwable t) {
                Toast toast = Toast.makeText(getContext(), "Incorrect ID", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}