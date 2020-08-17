package com.nav.greenhousecontoller.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.nav.greenhousecontoller.R;
import com.nav.greenhousecontoller.integration.GreenHouseServerParams;
import com.nav.greenhousecontoller.integration.userService.UserService;
import com.nav.greenhousecontoller.model.User;
import com.nav.greenhousecontoller.retrofit.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private EditText userName;
    private EditText password;

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.main_fragment, container, false);
        MaterialButton button = view.findViewById(R.id.login_button);
        MaterialButton register = view.findViewById(R.id.register);
        userName = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        setLoginListener(view, button);
        register.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_registerFragment));

        return view;
    }

    private void setLoginListener(View view, MaterialButton button) {
        button.setOnClickListener(v -> {
            Editable user = userName.getText();
            Editable pass = password.getText();
            String getUserFromText = user == null ? "" : user.toString();
            String getPassFromText = pass == null ? "" : pass.toString();
            if (getUserFromText.equals("")) {
                Toast toast = Toast.makeText(getContext(), "Please Enter UserName", Toast.LENGTH_LONG);
                toast.show();
            }
            if (getPassFromText.equals("")) {
                Toast toast = Toast.makeText(getContext(), "Please Enter Password", Toast.LENGTH_LONG);
                toast.show();
            }
            navigateOnNextPage(getUserFromText, getPassFromText, view);
        });
    }

    private void navigateOnNextPage(String getUserFromText, String getPassFromText, View view) {
        UserService userService = RetrofitBuilder.getUserService(GreenHouseServerParams.URL);
        userService.getUser(getUserFromText).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    if (user != null) {
                        if (user.getPassword().equals(getPassFromText)) {
                            Bundle bundle = new Bundle();
                            bundle.putLong("id", user.getId());
                            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_viewGreenHouseFragment, bundle);
                        } else {
                            Toast toast = Toast.makeText(getContext(), "Incorrect Password", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(getContext(), "User Doesn't Exists", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast toast = Toast.makeText(getContext(), "User or Password is Incorrect", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }


}
