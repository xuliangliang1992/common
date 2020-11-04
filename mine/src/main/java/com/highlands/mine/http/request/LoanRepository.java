package com.highlands.mine.http.request;

import com.highlands.mine.http.response.LoginBean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.rx_cache2.Reply;

/**
 * @author xll
 * @date 2018/12/4
 */
public class LoanRepository implements LoanDataSource {

    @Nullable
    private static LoanRepository INSTANCE;

    private RemoteLoanDataSource mRemoteLoanDataSource;

    private LoanRepository(RemoteLoanDataSource remoteLoanDataSource) {
        this.mRemoteLoanDataSource = remoteLoanDataSource;
    }

    public static LoanRepository getInstance(@NonNull RemoteLoanDataSource remoteLoanDataSource) {
        if (null == INSTANCE) {
            INSTANCE = new LoanRepository(remoteLoanDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<String> getAccessToken(long userId, long shopId, String dbName) {
        return mRemoteLoanDataSource.getAccessToken(userId, shopId, dbName);
    }

    /**
     * 登录
     *
     * @param userName 账号
     * @param password 密码
     * @return Observable
     */
    @Override
    public Observable<Reply<LoginBean>> login(String userName, String password) {
        return mRemoteLoanDataSource.login(userName, password);
    }
}
