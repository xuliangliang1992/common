package com.highlands.tianFuFinance.fun.register;

import android.text.InputType;
import android.view.View;

import com.highlands.common.base.fragment.BaseMvpFragment;
import com.highlands.common.util.ShapeUtil;
import com.highlands.common.util.StringUtil;
import com.highlands.tianFuFinance.R;
import com.highlands.tianFuFinance.databinding.RegisterFragmentBinding;
import com.highlands.tianFuFinance.http.response.SmsSendBean;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * 注册
 *
 * @author xll
 * @date 2020-11-02
 * copyright(c) Highlands
 */
public class RegisterFragment extends BaseMvpFragment<RegisterPresenter> implements RegisterContract.View, View.OnClickListener {
    private RegisterFragmentBinding binding;
    private RegisterViewModel mViewModel;

    static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public int setLayout() {
        return R.layout.register_fragment;
    }

    @Override
    public void initView(View view) {
        binding = DataBindingUtil.bind(view);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        ShapeUtil.setShape(binding.tvCode, mActivity, 8, R.color.blue_709AFF);
        ShapeUtil.setShape(binding.tvRegister, mActivity, 8, R.color.blue_3974FF);

        binding.lvPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        binding.lvPasswordAgain.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

    }

    @Override
    public void initListener() {
        // Create the observer which updates the UI.
        final Observer<String> nameObserver = newName -> {
            // Update the UI, in this case, a TextView.
            //            mTextView.setText(newName);
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mViewModel.getCurrentName().observe(this, nameObserver);

        binding.tvCode.setOnClickListener(this);
        binding.tvLogin.setOnClickListener(this);
        binding.tvRegister.setOnClickListener(this);
    }

    @Override
    public void initData() {
        binding.tvCode.init(60);
    }

    @Override
    public void setPresenter() {
        mPresenter = new RegisterPresenter(this);
    }


    @Override
    protected void toMain() {
        super.toMain();
        mActivity.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.tvCode.onDestroy();
    }

    @Override
    public void onClick(View v) {
        String phone = "";
        if (v.getId() == R.id.tv_code) {
            phone = binding.lvAccount.getText();
            if (StringUtil.checkPhone(phone)) {
                showLoading();
                mPresenter.sendSms(phone);
            }
        } else if (v.getId() == R.id.tv_login) {
            toLogin();
        } else if (v.getId() == R.id.tv_register) {
            phone = binding.lvAccount.getText();
            String code = binding.lvCode.getText();
            String password = binding.lvPassword.getText();
            String passwordAgain = binding.lvPasswordAgain.getText();
            if (StringUtil.checkCode(code) && StringUtil.checkCode(password) && StringUtil.checkCode(passwordAgain)) {
                if (!password.equals(passwordAgain)) {
                    showToast("两次密码不一致");
                    return;
                }
                showLoading();
                mPresenter.register(phone, code, password);
            }
        }

    }

    @Override
    public void sendSmsSuccess(SmsSendBean smsSendBean) {
        showToast("验证码已发送");
        hideLoading();
        binding.tvCode.onClick();
    }

    @Override
    public void register() {
        hideLoading();
        showToast("注册成功");
        toLogin();
    }
}