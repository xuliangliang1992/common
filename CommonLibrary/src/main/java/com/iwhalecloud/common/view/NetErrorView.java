package com.iwhalecloud.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.iwhalecloud.common.commonlibrary.R;
import com.iwhalecloud.common.commonlibrary.databinding.ViewNetErrorBinding;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

/**
 * 无网络View
 *
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class NetErrorView extends RelativeLayout {
    private OnClickListener mOnClickListener;
    private final ViewNetErrorBinding mBinding;

    public NetErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_net_error, this, true);
        mBinding.btnNetRefresh.setOnClickListener(v -> {
            if (mOnClickListener != null) {
                mOnClickListener.onClick(v);
            }
        });
    }

    public void setRefreshBtnClickListener(OnClickListener listener) {
        mOnClickListener = listener;
    }

    public void setNetErrorBackground(@ColorRes int colorResId) {
        mBinding.rlNetErrorRoot.setBackgroundColor(ContextCompat.getColor(getContext(), colorResId));
    }
}
