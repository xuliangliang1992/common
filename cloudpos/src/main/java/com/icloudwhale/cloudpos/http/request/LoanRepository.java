package com.icloudwhale.cloudpos.http.request;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Observable;

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


}
