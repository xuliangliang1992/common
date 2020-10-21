package com.highlands.home.home;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<ObservableArrayList<HomeBean>> mHomeBeans;

    public MutableLiveData<ObservableArrayList<HomeBean>> getHomeBeans() {
        if (mHomeBeans == null) {
            mHomeBeans = new MutableLiveData<ObservableArrayList<HomeBean>>();
        }
        return mHomeBeans;
    }
}