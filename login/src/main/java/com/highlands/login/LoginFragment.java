package com.highlands.login;

import android.view.View;
import android.widget.MediaController;

import com.highlands.common.base.fragment.BaseFragment;
import com.highlands.login.databinding.LoginFragmentBinding;

import androidx.databinding.DataBindingUtil;

/**
 * @author xuliangliang
 * @date 2019-10-21
 * copyright(c) Highlands
 */
public class LoginFragment extends BaseFragment {

    private LoginFragmentBinding binding;

    static LoginFragment newInstance() {
        return new LoginFragment();
    }

    /**
     * 设置布局
     *
     * @return 布局id
     */
    @Override
    public int setLayout() {
        return R.layout.login_fragment;
    }

    /**
     * 初始化控件
     * onViewCreated时调用
     *
     * @param view view
     */
    @Override
    public void initView(View view) {
        binding = DataBindingUtil.bind(view);
    }

    /**
     * 初始化监听器
     */
    @Override
    public void initListener() {
        binding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        binding.videoView.setVideoURI("https://www.baidu.com");

                //        binding.videoView.setMediaController(new MediaController(mActivity));
            }
        });
    }

    /**
     * 初始化数据
     * onActivityCreated
     */
    @Override
    public void initData() {

    }

    /**
     * 设置presenter对象
     */
    @Override
    public void setPresenter() {

    }
}
