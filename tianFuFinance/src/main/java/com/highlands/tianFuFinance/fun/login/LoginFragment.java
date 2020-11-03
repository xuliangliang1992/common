package com.highlands.tianFuFinance.fun.login;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.highlands.common.base.fragment.BaseMvpFragment;
import com.highlands.common.constant.RouterUrl;
import com.highlands.common.util.ShapeUtil;
import com.highlands.common.util.StringUtil;
import com.highlands.tianFuFinance.R;
import com.highlands.tianFuFinance.databinding.LoginFragmentBinding;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * 登录
 *
 * @author xll
 * @date 2020-11-02
 * copyright(c) Highlands
 */
public class LoginFragment extends BaseMvpFragment<LoginPresenter> implements LoginContract.View, View.OnClickListener {
    private LoginFragmentBinding binding;
    private LoginViewModel mViewModel;

    static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public int setLayout() {
        return R.layout.login_fragment;
    }

    @Override
    public void initView(View view) {
        binding = DataBindingUtil.bind(view);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        ShapeUtil.setShape(binding.tvCode, mActivity, 8, R.color.blue_709AFF);
        ShapeUtil.setShape(binding.tvLogin, mActivity, 8, R.color.blue_3974FF);

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

        binding.tvCodeLogin.setOnClickListener(this);
        binding.tvAccountLogin.setOnClickListener(this);
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
        mPresenter = new LoginPresenter(this);
    }


    @Override
    protected void toHome() {
        super.toHome();
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
        if (v.getId() == R.id.tv_code_login) {
            mViewModel.isCodeLogin().setValue(true);
        } else if (v.getId() == R.id.tv_account_login) {
            mViewModel.isCodeLogin().setValue(false);
        } else if (v.getId() == R.id.tv_code) {
            phone = binding.lvAccount.getText();
            if (StringUtil.checkPhone(phone)) {
                binding.tvCode.onClick();
            }

        } else if (v.getId() == R.id.tv_login) {
            if (StringUtil.checkPhone(phone)) {
                if (mViewModel.isCodeLogin().getValue()) {
                    String code = binding.lvCode.getText();
                    if (StringUtil.checkCode(code)) {
                        mPresenter.codeLogin(phone, code);
                    }
                } else {
                    String password = binding.lvPassword.getText();
                    if (StringUtil.checkPassword(password)) {
                        mPresenter.accountLogin(phone, password);
                    }
                }
            }
        } else if (v.getId() == R.id.tv_register) {
            ARouter.getInstance().build(RouterUrl.TAX_REGISTER).navigation();
        }

    }

}