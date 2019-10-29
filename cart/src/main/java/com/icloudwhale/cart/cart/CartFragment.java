package com.icloudwhale.cart.cart;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.icloudwhale.cart.R;
import com.icloudwhale.cart.databinding.CartFragmentBinding;
import com.icloudwhale.cart.view.SlideExpandableRecyclerView;
import com.iwhalecloud.common.base.fragment.BaseMvpFragment;
import com.iwhalecloud.common.constant.RouterUrl;
import com.iwhalecloud.common.http.response.DataTree;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import timber.log.Timber;

/**
 * @author xll
 * @date 20
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
@Route(path = RouterUrl.CART_FRAGMENT_CART)
public class CartFragment extends BaseMvpFragment<CartPresenter> implements CartContract.View {
    private CartContract.Presenter mPresenter;
    private CartFragmentBinding binding;

    static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public int setLayout() {
        return R.layout.cart_fragment;
    }

    @Override
    public void initView(View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public void initListener() {
        binding.refreshLayout.setEnableRefresh(false);
        binding.refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void initData() {
        String json = "[\n" +
                "  {\n" +
                "    \"goods\" : [\n" +
                "      {\n" +
                "        \"num\" : 3,\n" +
                "        \"goodName\" : \"鞋子\",\n" +
                "        \"goodId\" : 1,\n" +
                "        \"price\" : 200\n" +
                "      },\n" +
                "      {\n" +
                "        \"num\" : 1,\n" +
                "        \"goodName\" : \"衣服\",\n" +
                "        \"goodId\" : 2,\n" +
                "        \"price\" : 200\n" +
                "      },{\n" +
                "        \"num\" : 3,\n" +
                "        \"goodName\" : \"帽子\",\n" +
                "        \"goodId\" : 3,\n" +
                "        \"price\" : 200\n" +
                "      },\n" +
                "      {\n" +
                "        \"num\" : 1,\n" +
                "        \"goodName\" : \"裤子\",\n" +
                "        \"goodId\" : 4,\n" +
                "        \"price\" : 200\n" +
                "      },\n" +
                "      {\n" +
                "        \"num\" : 1,\n" +
                "        \"goodName\" : \"裙子\",\n" +
                "        \"goodId\" : 5,\n" +
                "        \"price\" : 200\n" +
                "      },\n" +
                "      {\n" +
                "        \"num\" : 1,\n" +
                "        \"goodName\" : \"衬衫\",\n" +
                "        \"goodId\" : 6,\n" +
                "        \"price\" : 200\n" +
                "      }\n" +
                "    ],\n" +
                "    \"shopId\" : 1,\n" +
                "    \"shopName\" : \"阿迪达斯\"\n" +
                "  },{\n" +
                "    \"goods\" : [\n" +
                "      {\n" +
                "        \"num\" : 3,\n" +
                "        \"goodName\" : \"鞋子\",\n" +
                "        \"goodId\" : 7,\n" +
                "        \"price\" : 200\n" +
                "      },\n" +
                "      {\n" +
                "        \"num\" : 1,\n" +
                "        \"goodName\" : \"衣服\",\n" +
                "        \"goodId\" : 8,\n" +
                "        \"price\" : 200\n" +
                "      },{\n" +
                "        \"num\" : 3,\n" +
                "        \"goodName\" : \"帽子\",\n" +
                "        \"goodId\" : 9,\n" +
                "        \"price\" : 200\n" +
                "      },\n" +
                "      {\n" +
                "        \"num\" : 1,\n" +
                "        \"goodName\" : \"裤子\",\n" +
                "        \"goodId\" : 10,\n" +
                "        \"price\" : 200\n" +
                "      },\n" +
                "      {\n" +
                "        \"num\" : 1,\n" +
                "        \"goodName\" : \"裙子\",\n" +
                "        \"goodId\" : 11,\n" +
                "        \"price\" : 200\n" +
                "      },\n" +
                "      {\n" +
                "        \"num\" : 1,\n" +
                "        \"goodName\" : \"衬衫\",\n" +
                "        \"goodId\" : 12,\n" +
                "        \"price\" : 200\n" +
                "      }\n" +
                "    ],\n" +
                "    \"shopId\" : 2,\n" +
                "    \"shopName\" : \"彪马\"\n" +
                "  }\n" +
                "]";

        Gson gson = new Gson();
        List<CartBean> cartBeans = gson.fromJson(json, new TypeToken<List<CartBean>>() {
        }.getType());

        List<DataTree<CartBean, CartBean.GoodBean>> mData = new ArrayList<>();
        for (int i = 0; i < cartBeans.size(); i++) {
            mData.add(new DataTree<>(cartBeans.get(i), cartBeans.get(i).getGoods()));
        }

        binding.rvCart.setLayoutManager(new LinearLayoutManager(mActivity));
        CartAdapter cartAdapter = new CartAdapter();
        cartAdapter.refresh(mData);
        //        cartAdapter.setOnGroupClickListener(new BaseTwoLevelAdapter.OnGroupClickListener<CartBean>() {
        //
        //            @Override
        //            public boolean onGroupItemClick(CartBean cartBean, int groupPosition) {
        //                Timber.d(groupPosition + "---" + cartBean.toString());
        //                return false;
        //            }
        //        });
        //        cartAdapter.setOnChildClickListener(new BaseTwoLevelAdapter.OnChildClickListener<CartBean.GoodBean>() {
        //            @Override
        //            public void onChildItemClick(CartBean.GoodBean goodBean, int groupPosition, int childPosition) {
        //                Timber.d(groupPosition + "---" + childPosition + "---" + goodBean.toString());
        //                //                cartAdapter.removeChild(groupPosition, childPosition);
        //            }
        //        });
        //        cartAdapter.setOnGroupExpandListener(new BaseTwoLevelAdapter.OnGroupExpandListener() {
        //            @Override
        //            public void onGroupExpand(int groupPosition) {
        //                Timber.d(groupPosition + "---onGroupExpand");
        //            }
        //        });
        //        cartAdapter.setOnGroupCollapseListener(new BaseTwoLevelAdapter.OnGroupCollapseListener() {
        //
        //            @Override
        //            public void onGroupCollapse(int groupPosition) {
        //                Timber.d(groupPosition + "---onGroupCollapse");
        //            }
        //        });
        binding.rvCart.setAdapter(cartAdapter);

        binding.rvCart.setOnChildClickListener(new SlideExpandableRecyclerView.OnChildClickListener() {
            @Override
            public void onDelete(int position) {
                Timber.d(position + " onDelete " + cartAdapter.getGroupPosition(position) + " child " + cartAdapter.getChildPosition(position));
                cartAdapter.removeChild(cartAdapter.getGroupPosition(position), cartAdapter.getChildPosition(position));
            }

            @Override
            public void onChildClick(int position) {
                Timber.d(position + " group " + cartAdapter.getGroupPosition(position) + " child " + cartAdapter.getChildPosition(position));

            }
        });

        binding.rvCart.setOnGroupClickListener(new SlideExpandableRecyclerView.OnGroupClickListener() {
            @Override
            public void onGroupClickListener(int position) {
                Timber.d(position + " group " + cartAdapter.getGroupPosition(position));
            }
        });
        //
        //        //使用ExpandRecyclerView 需禁用adapter的点击事件 否则滑动事件无法触发
        cartAdapter.setGroupClickable(false);
        cartAdapter.setChildClickable(false);
    }

    @Override
    public void setPresenter() {
        mPresenter = new CartPresenter(this);
    }

}