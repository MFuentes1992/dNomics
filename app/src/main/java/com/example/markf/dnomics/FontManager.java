package com.example.markf.dnomics;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by markf on 09/06/2018.
 */

public class FontManager {
    public static final String ROOT = "fonts/",
            FONTAWESOMESOLID = ROOT + "Font Awesome 5 Free-Solid-900.otf", FONTAWESOMEBUSINESS = ROOT + "Font Awesome 5 Brands-Regular-400.otf", FONTAWESOMEREGULAR = ROOT + "Font Awesome 5 Free-Regular-400.otf";
    public static final String RIGHTEOUS = ROOT + "Righteous-Regular.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }

    public static void markAsIconContainer(View v, Typeface typeface) {
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                markAsIconContainer(child, typeface);
            }
        } else if (v instanceof TextView) {
            ((TextView) v).setTypeface(typeface);
        }
    }
}
