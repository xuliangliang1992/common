package com.highlands.tianFuFinance.fun.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author xll
 * @date 2020-11-02
 * copyright(c) Highlands
 */
public class LoginViewModel extends ViewModel {
    private MutableLiveData<String> currentName;
    private MutableLiveData<Boolean> codeLogin;

    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<String>();
        }
        return currentName;
    }

    public MutableLiveData<Boolean> isCodeLogin() {
        if (codeLogin == null) {
            codeLogin = new MutableLiveData<Boolean>();
        }
        return codeLogin;
    }
}