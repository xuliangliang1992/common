package com.highlands.tianFuFinance.fun.register;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    // TODO: Implement the ViewModel
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