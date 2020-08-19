package com.nav.greenhousecontoller.utils;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

public final class SpinnerHelper {

    public static List<Integer> getTempInterval() {
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i <= 50; i++) {
            tempList.add(i);
        }
        return tempList;
    }

    public static List<Integer> getPercentageInterval() {
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            tempList.add(i);
        }
        return tempList;
    }

    public static void setTempIntervalToSpinner(FragmentActivity activity, Spinner up, Spinner down, List<Integer> interval, int downLimit, int upLimit) {
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(activity,
                android.R.layout.simple_spinner_item, interval);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        down.setAdapter(dataAdapter);
        up.setAdapter(dataAdapter);
        down.setSelection(downLimit);
        up.setSelection(upLimit);
    }

    public static void setLightIntervalToSpinner(FragmentActivity activity, Spinner up, Spinner down, List<Integer> interval, int downLimit, int upLimit) {
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(activity,
                android.R.layout.simple_spinner_item, interval);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        down.setAdapter(dataAdapter);
        up.setAdapter(dataAdapter);
        down.setSelection(downLimit);
        up.setSelection(upLimit);
    }

    public static void setMoistIntervalToSpinner(FragmentActivity activity, Spinner up, Spinner down, List<Integer> interval, int downLimit, int upLimit) {
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_spinner_item, interval);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        down.setAdapter(dataAdapter);
        up.setAdapter(dataAdapter);
        down.setSelection(downLimit);
        up.setSelection(upLimit);
    }
}
