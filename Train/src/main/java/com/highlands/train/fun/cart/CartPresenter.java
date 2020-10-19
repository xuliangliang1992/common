package com.highlands.train.fun.cart;

import com.highlands.common.base.BasePresenter;

/**
 * @author xll
 * @date 20
 * copyright(c) Highlands
 */
class CartPresenter extends BasePresenter<CartContract.View> implements CartContract.Presenter {

    CartPresenter(CartContract.View view) {
        super(view);
    }

}