package com.pparreno.myweather.utils;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.pparreno.myweather.R;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<String> getPredefinedCityIDs(){
        ArrayList<String> cityIDsList =  new ArrayList<>();
        cityIDsList.add("1701668");
        cityIDsList.add("3067696");
        cityIDsList.add("1835848");
        return cityIDsList;
    }

    public static String buildGroupCityQueryParam(@NonNull List<String> cityIDs)
    {
        int x = 0;
        String stringToReturn = "";

        for (String id: cityIDs) {
            if(x <  cityIDs.size() -1)
            {
                stringToReturn = stringToReturn + id + ",";
            } else {
                stringToReturn = stringToReturn + id;
            }
            x++;
        }

        return stringToReturn;
    }

    public static int colorForTemp(@NonNull Float temp, @NonNull Context context) {
        // Background color based on the temperature
        // ■ Freezing #1976D2 (-infinity, 0]
        // ■ Cold #26C6DA (0, 15]
        /// ■ Warm #66BB6A (15, 30]
        // ■ Hot #FF7043 (30, infinity)
        if(temp <= 0f) {
            return ContextCompat.getColor(context, R.color.colorFreezing);
        } else if(temp > 0f && temp <= 15.0f) {
            return ContextCompat.getColor(context, R.color.colorCold);
        } else if(temp > 15.0f && temp <= 30.0f) {
            return ContextCompat.getColor(context, R.color.colorWarm);
        } else {
            return ContextCompat.getColor(context, R.color.colorHot);
        }
    }

}
