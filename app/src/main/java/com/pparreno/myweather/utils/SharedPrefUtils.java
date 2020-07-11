package com.pparreno.myweather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class SharedPrefUtils {

    private static final Integer PRIVATE_MODE = 0;
    private static final String PREF_NAME = "FAV_COUNTRY_IDS_PREF";
    private static final String PREF_FAV_COUNTRIES = "PREF_FAV_COUNTRIES";

    public static boolean isCityNameFavorite(@NonNull String cityName, @NonNull Context context) {
        Set<String> favNameSet = getFavorites(context);
        if(favNameSet != null)
        {
            for (String name : favNameSet) {
                if(name.compareTo(cityName) == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private static Set<String> getFavorites(@NonNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return sharedPreferences.getStringSet(PREF_FAV_COUNTRIES, null);
    }

    private static boolean storeFavoriteSet(@NonNull Context context, @NonNull Set<String> favSet) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(PREF_FAV_COUNTRIES, favSet);
        return editor.commit();
    }

    public static boolean setCityNameAsFavorite(@NonNull String cityName, @NonNull Context context) {
        Set<String> favNameSet = getFavorites(context);
        if (favNameSet == null) {
            favNameSet = new ArraySet<>();
        }
        favNameSet.add(cityName);
        return storeFavoriteSet(context, favNameSet);
    }

    public static boolean removeCityNameAsFavorite(@NotNull String cityName, @NotNull Context context) {
        Set<String> favNameSet = getFavorites(context);
        if (favNameSet == null) {
           return false;
        } else {
            favNameSet.remove(cityName);
            return storeFavoriteSet(context, favNameSet);
        }
    }
}
