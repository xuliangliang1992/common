package com.highlands.common.util.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.ArrayMap;

/**
 * @author xuliangliang
 * @date 2020/10/30
 * copyright(c) Highlands
 */
public class FontCache {
    private static ArrayMap<String, Typeface> fontCache = new ArrayMap<String, Typeface>();

    public static Typeface getTypeface( Context context,String fontName) {
        Typeface tf = fontCache.get(fontName);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), fontName);
            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(fontName, tf);
        }
        return tf;
    }
}
