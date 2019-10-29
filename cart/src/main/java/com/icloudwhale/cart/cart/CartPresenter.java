package com.icloudwhale.cart.cart;

import com.iwhalecloud.common.base.BasePresenter;

/**
 * @author xll
 * @date 20
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
class CartPresenter extends BasePresenter<CartContract.View> implements CartContract.Presenter {

    CartPresenter(CartContract.View view) {
        super(view);
    }

}