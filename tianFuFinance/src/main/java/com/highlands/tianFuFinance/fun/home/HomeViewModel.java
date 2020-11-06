package com.highlands.tianFuFinance.fun.home;

import com.highlands.common.http.response.HomeBean;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author xll
 * @date 2020-11-01
 * copyright(c) Highlands
 */
public class HomeViewModel extends ViewModel {

    private MutableLiveData<ObservableArrayList<HomeBean>> mHomeBeans;

    public MutableLiveData<ObservableArrayList<HomeBean>> getHomeBeans() {
        if (mHomeBeans == null) {
            mHomeBeans = new MutableLiveData<>();
        }
        return mHomeBeans;
    }
}