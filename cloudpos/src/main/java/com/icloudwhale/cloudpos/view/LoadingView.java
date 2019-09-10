package com.icloudwhale.cloudpos.view;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.icloudwhale.cloudpos.R;
import com.icloudwhale.cloudpos.databinding.ViewLoadingBinding;

/**
 * 加载中View
 *
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class LoadingView extends RelativeLayout {
    private final AnimationDrawable mAnimationDrawable;
    private ViewLoadingBinding binding;

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_loading, this, true);
        mAnimationDrawable = (AnimationDrawable) binding.imgLoading.getDrawable();
    }

    public void setLoadingBackgroundColor(@ColorRes int colorResId) {
        binding.rlLoadingRoot.setBackgroundColor(ContextCompat.getColor(getContext(),colorResId));
    }

    private void startLoading() {
        mAnimationDrawable.start();
    }

    private void stopLoading() {
        mAnimationDrawable.stop();
    }

    public void loading(boolean b) {
        if (b) {
            startLoading();
        } else {
            stopLoading();
        }
    }
}
