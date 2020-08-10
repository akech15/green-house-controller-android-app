package com.nav.greenhousecontoller;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

public final class SpinnerHelper {

    public static List<Integer> getTemperatureInterval() {
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            tempList.add(i);
        }
        return tempList;
    }

    public static List<Integer> getMoistureInterval() {
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < 1200; i += 50) {
            tempList.add(i);
        }
        return tempList;
    }

    public static List<Integer> getLightInterval() {
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < 1200; i += 50) {
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
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(activity,
                android.R.layout.simple_spinner_item, interval);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        down.setAdapter(dataAdapter);
        up.setAdapter(dataAdapter);
        down.setSelection(downLimit);
        up.setSelection(upLimit);
    }
}
