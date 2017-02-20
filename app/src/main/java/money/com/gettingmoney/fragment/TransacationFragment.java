package money.com.gettingmoney.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.main.base.BaseFragment;
import money.com.gettingmoney.bai.main.base.MyToolBar;

public class TransacationFragment extends BaseFragment {


    /**
     * 交易界面
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        toolBar = new MyToolBar(getActivity(), "", "交易", "");
        View view = requestView(inflater, R.layout.fragment_transacation);
        ButterKnife.inject(this, view);
        initEvent();
        return view;
    }

    private void initEvent() {
    }


    @Override
    public void requestInit() {
        
    }
}
