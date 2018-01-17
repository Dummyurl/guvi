package com.guvi.gt.lataxidriver.config;


import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by Jemsheer K D on 18 February, 2017.
 * Package com.guvi.gt.lataxidriver.conf
 * Project LaTaxi
 */

public class TypefaceCache {

    private static TypefaceCache instance = new TypefaceCache();

    private final HashMap<String, Typeface> typefaceList = new HashMap<>();

    public static TypefaceCache getInstance() {
        return instance;
    }

    private TypefaceCache() {
    }

    public Typeface getTypeface(Context context, String asset) throws Exception {

        synchronized (typefaceList) {
            Typeface typeface;
            if (!typefaceList.isEmpty() && typefaceList.containsKey(asset)) {
                typeface = typefaceList.get(asset);
            } else {
                typeface = Typeface.createFromAsset(context.getAssets(), asset);
                typefaceList.put(asset, typeface);
            }
            return typeface;
        }
    }
}