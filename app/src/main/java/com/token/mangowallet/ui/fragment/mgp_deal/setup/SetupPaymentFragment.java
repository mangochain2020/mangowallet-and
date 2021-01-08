package com.token.mangowallet.ui.fragment.mgp_deal.setup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.MapUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonObject;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.token.mangowallet.R;
import com.token.mangowallet.base.BaseFragment;
import com.token.mangowallet.bean.PayInfoBean;
import com.token.mangowallet.db.MangoWallet;
import com.token.mangowallet.net.common.NetWorkManager;
import com.token.mangowallet.ui.adapter.PayInfoAdapter;
import com.token.mangowallet.utils.NRSAUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.token.mangowallet.utils.Constants.EXTRA_WALLET;
import static com.token.mangowallet.utils.Constants.LOG_TAG;

public class SetupPaymentFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private MangoWallet mangoWallet;
    private List<PayInfoBean.DataBean> payInfoList = new ArrayList<>();
    private PayInfoAdapter payInfoAdapter;

    @Override
    protected View onCreateView() {
        BarUtils.setStatusBarColor(getBaseFragmentActivity(), ContextCompat.getColor(getBaseFragmentActivity(), R.color.qmui_config_color_white));
        QMUIStatusBarHelper.setStatusBarLightMode(getBaseFragmentActivity());
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_set_payment_term, null);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        mangoWallet = bundle.getParcelable(EXTRA_WALLET);
        getPayInfoList();
    }

    @Override
    protected void initView() {
        topbar.setTitle(R.string.str_payment_term);
        topbar.addLeftImageButton(R.drawable.icon_black_arrows_back, R.id.topbar_left_change_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        Button rightBtn = topbar.addRightTextButton(R.string.str_add, R.id.topbar_right_change_button);
        rightBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.app_color_dark_blue));
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        payInfoAdapter = new PayInfoAdapter(payInfoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(payInfoAdapter);
    }

    @Override
    protected void initAction() {

    }

    /**
     * 获取收款方式
     */
    private void getPayInfoList() {
        try {
            showTipDialog(getString(R.string.str_loading));
            Map params = MapUtils.newHashMap();
            params.put("mgpName", mangoWallet.getWalletAddress());
            String json = GsonUtils.toJson(params);
            String content = NRSAUtils.encrypt(json);
            NetWorkManager.getRequest().getPayInfoList(content)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onPayInfoSuccess, this::onError);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onPayInfoSuccess(JsonObject jsonObject) {
        dismissTipDialog();
        payInfoList.clear();
        if (ObjectUtils.isNotEmpty(jsonObject)) {
            PayInfoBean payInfoBean = GsonUtils.fromJson(GsonUtils.toJson(jsonObject), PayInfoBean.class);
            if (payInfoBean != null) {
                if (payInfoBean.getCode() == 0) {
                    if (CollectionUtils.isNotEmpty(payInfoBean.getData())) {
                        payInfoList.addAll(payInfoBean.getData());
                    }
                } else {
                    ToastUtils.showLong(payInfoBean.getMsg());
                }
            }
        }
        payInfoAdapter.notifyDataSetChanged();
    }

    private void onError(Object e) {
        dismissTipDialog();
        LogUtils.eTag(LOG_TAG, "e = " + e.toString());
    }

}
