package com.nav.greenhousecontoller.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.nav.greenhousecontoller.R;
import com.nav.greenhousecontoller.integration.GreenHouseServerParams;
import com.nav.greenhousecontoller.integration.greenHouse.GreenHouseService;
import com.nav.greenhousecontoller.integration.userService.UserService;
import com.nav.greenhousecontoller.model.GreenHouse;
import com.nav.greenhousecontoller.model.GreenHouseResponse;
import com.nav.greenhousecontoller.model.User;
import com.nav.greenhousecontoller.retrofit.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    private EditText userName;
    private EditText greenHouseId;
    private EditText password;
    private EditText repeatPassword;
    private Button button;
    private boolean greenHouseIdExists = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_register, container, false);
        userName = view.findViewById(R.id.register_username);
        greenHouseId = view.findViewById(R.id.green_house_id);
        password = view.findViewById(R.id.register_password);
        repeatPassword = view.findViewById(R.id.register_password_again);
        button = view.findViewById(R.id.register_button);
        button.setOnClickListener(v -> {
            String getUserNameFromText = getTextFromEditText(userName);
            String getPasswordFromText = getTextFromEditText(password);
            String getRepeatedPassword = getTextFromEditText(repeatPassword);
            String getGreenHouseId = getTextFromEditText(greenHouseId);
            if (getGreenHouseId.equals("")) {
                Toast toast = Toast.makeText(getContext(), "Please Enter Green House Id", Toast.LENGTH_LONG);
                toast.show();
                return;
            }
            if (getUserNameFromText.equals("")) {
                Toast toast = Toast.makeText(getContext(), "Please Enter UserName", Toast.LENGTH_LONG);
                toast.show();
                return;
            }
            if (getPasswordFromText.equals("")) {
                Toast toast = Toast.makeText(getContext(), "Please Enter Password", Toast.LENGTH_LONG);
                toast.show();
                return;
            }
            if (getRepeatedPassword.equals("")) {
                Toast toast = Toast.makeText(getContext(), "Please Repeat Password", Toast.LENGTH_LONG);
                toast.show();
                return;
            }
            User user = getUser(getUserNameFromText, getPasswordFromText, getGreenHouseId);
            checkIfIdExists(Long.parseLong(getGreenHouseId));
            if (greenHouseIdExists) {
                addUser(user, view);
            } else {
                Toast toast = Toast.makeText(getContext(), "Incorrect GreenHouse Id", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return view;
    }

    private void checkIfIdExists(long id) {
        GreenHouseService greenHouseService = RetrofitBuilder.getGreenHouseService(GreenHouseServerParams.URL);
        greenHouseService.getGreenHouse(id).enqueue(new Callback<GreenHouse>() {
            @Override
            public void onResponse(Call<GreenHouse> call, Response<GreenHouse> response) {
                if (response.isSuccessful()) {
                    GreenHouse greenHouse = response.body();
                    greenHouseIdExists = greenHouse != null;
                }
            }

            @Override
            public void onFailure(Call<GreenHouse> call, Throwable t) {
                greenHouseIdExists = false;
            }
        });
    }

    private void addUser(User user, View view) {
        UserService userService = RetrofitBuilder.getUserService(GreenHouseServerParams.URL);
        userService.addUser(user).enqueue(new Callback<GreenHouseResponse>() {
            @Override
            public void onResponse(Call<GreenHouseResponse> call, Response<GreenHouseResponse> response) {
                if (response.isSuccessful()) {
                    GreenHouseResponse greenHouseResponse = response.body();
                    if (greenHouseResponse != null) {
                        Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_mainFragment);
                    }
                }
            }

            @Override
            public void onFailure(Call<GreenHouseResponse> call, Throwable t) {

            }
        });
    }

    private User getUser(String getUserNameFromText, String getPasswordFromText, String getGreenHouseId) {
        User user = new User();
        user.setId(Long.parseLong(getGreenHouseId));
        user.setPassword(getPasswordFromText);
        user.setUserName(getUserNameFromText);
        return user;
    }

    private String getTextFromEditText(EditText editText) {
        Editable result = editText.getText();
        return result == null ? "" : result.toString();
    }
}
