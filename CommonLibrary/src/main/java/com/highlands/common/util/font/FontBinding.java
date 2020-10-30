package com.highlands.common.util.font;

import android.graphics.Typeface;

import com.highlands.common.base.BaseApplication;

import androidx.databinding.BindingConversion;

/**
 * @author xuliangliang
 * @date 2020/10/30
 * copyright(c) Highlands
 */
public class FontBinding {
    @BindingConversion
    public static Typeface convertStringToFace(String s) {
        try {
            return FontCache.getTypeface(BaseApplication.getInstance(), s);
        } catch (Exception e) {
            throw e;
        }
    }
}
