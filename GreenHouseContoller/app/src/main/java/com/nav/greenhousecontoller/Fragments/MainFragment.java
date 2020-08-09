package com.nav.greenhousecontoller.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.nav.greenhousecontoller.R;
import com.nav.greenhousecontoller.integration.GreenHouseServerParams;
import com.nav.greenhousecontoller.integration.greenHouse.GreenHouseService;
import com.nav.greenhousecontoller.model.GreenHouse;
import com.nav.greenhousecontoller.retrofit.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private Button button;
    private TextInputEditText textInputEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.main_fragment, container, false);
        button = view.findViewById(R.id.login_button);
        textInputEditText = view.findViewById(R.id.input_id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getIdFromText = textInputEditText.getText().toString();
                long id = Long.parseLong(getIdFromText);
                navigateOnNextPage(id, view);
            }
        });
        return view;
    }

    private void navigateOnNextPage(long id, final View view) {
        GreenHouseService greenHouseService = RetrofitBuilder.getGreenHouseService(GreenHouseServerParams.URL);
        greenHouseService.getGreenHouse(id).enqueue(new Callback<GreenHouse>() {
            @Override
            public void onResponse(Call<GreenHouse> call, Response<GreenHouse> response) {
                if (response.isSuccessful()) {
                    Bundle bundle = new Bundle();
                    bundle.putLong("id", id);
                    Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_viewGreenHouseFragment, bundle);
                } else {
                    Toast toast = Toast.makeText(getContext(), "Incorrect ID", Toast.LENGTH_LONG);
                    toast.show();
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
