package com.pparreno.myweather.utils;

import androidx.annotation.NonNull;

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

}
