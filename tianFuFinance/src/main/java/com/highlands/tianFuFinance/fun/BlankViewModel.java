package com.highlands.tianFuFinance.fun;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BlankViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> currentName;

    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<String>();
        }
        return currentName;
    }
}