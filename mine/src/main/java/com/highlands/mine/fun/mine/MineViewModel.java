package com.highlands.mine.fun.mine;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MineViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> currentName;
    private MutableLiveData<Boolean> loggedIn;
    private MutableLiveData<Boolean> paid;

    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<String>();
        }
        return currentName;
    }

    public MutableLiveData<Boolean> isLoggedIn() {
        if (loggedIn == null) {
            loggedIn = new MutableLiveData<Boolean>();
        }
        return loggedIn;
    }

    public MutableLiveData<Boolean> isPaid() {
        if (paid == null) {
            paid = new MutableLiveData<Boolean>();
        }
        return paid;
    }
}