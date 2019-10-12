package com.icloudwhale.cloudpos.injection;

import com.icloudwhale.cloudpos.http.request.LoanRepository;
import com.icloudwhale.cloudpos.http.request.RemoteLoanDataSource;
import com.iwhalecloud.common.schedulers.BaseSchedulerProvider;
import com.iwhalecloud.common.schedulers.SchedulerProvider;

/**
 * @author xll
 * @date 2018/12/4
 */
public class Injection {

    public static LoanRepository provideLoanRepository() {
        return LoanRepository.getInstance(RemoteLoanDataSource.getInstance());
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}
