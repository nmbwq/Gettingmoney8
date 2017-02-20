package money.com.gettingmoney.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.app.MoneyApplication;

public class MarketFragment extends Fragment implements View.OnClickListener {

    /**
     * 行情界面
     * @param savedInstanceState
     */
    private View view;
    private Context con;
    private RelativeLayout market_selfchosebtn,market_indexbtn,market_shenzhenbtn,market_platebtn,market_gangmeibtn;
    private TextView market_selfchosetext,market_indextext,market_shenzhentext,market_platetext,market_gangmeitext;
    private View market_view1,market_view2,market_view3,market_view4,market_view5;
    private ArrayList<TextView> texts;
    private ArrayList<View> views;
    private int position;
    private FragmentManager fm;
    private FragmentTransaction trans;
    private SelfchoseFragment selfchoseFragment;
    private IndexFragment indexFragment;
    private PlateFragment plateFragment;
    private ShenzhengFragment shenzhengFragment;
    private GangmeiFragment gangmeiFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_market,null);
        con = MoneyApplication.getContext();
        initview();
        return view;
    }

    private void initview() {
        texts = new ArrayList<>();
        views = new ArrayList<>();


        market_selfchosebtn = (RelativeLayout) view.findViewById(R.id.market_selfchosebtn);
        market_indexbtn = (RelativeLayout) view.findViewById(R.id.market_indexbtn);
        market_shenzhenbtn = (RelativeLayout) view.findViewById(R.id.market_shenzhenbtn);
        market_platebtn = (RelativeLayout) view.findViewById(R.id.market_platebtn);
        market_gangmeibtn = (RelativeLayout) view.findViewById(R.id.market_gangmeibtn);

        market_selfchosetext = (TextView) view.findViewById(R.id.market_selfchosetext);
        market_indextext = (TextView) view.findViewById(R.id.market_indextext);
        market_shenzhentext = (TextView) view.findViewById(R.id.market_shenzhentext);
        market_platetext = (TextView) view.findViewById(R.id.market_platetext);
        market_gangmeitext = (TextView) view.findViewById(R.id.market_gangmeitext);
        texts.add(market_selfchosetext);
        texts.add(market_indextext);
        texts.add(market_shenzhentext);
        texts.add(market_platetext);
        texts.add(market_gangmeitext);

        market_view1 = view.findViewById(R.id.market_view1);
        market_view2 = view.findViewById(R.id.market_view2);
        market_view3 = view.findViewById(R.id.market_view3);
        market_view4 = view.findViewById(R.id.market_view4);
        market_view5 = view.findViewById(R.id.market_view5);
        views.add(market_view1);
        views.add(market_view2);
        views.add(market_view3);
        views.add(market_view4);
        views.add(market_view5);


        selfchoseFragment = new SelfchoseFragment();
        indexFragment = new IndexFragment();
        plateFragment = new PlateFragment();
        shenzhengFragment = new ShenzhengFragment();
        gangmeiFragment = new GangmeiFragment();

        market_selfchosebtn.setOnClickListener(this);
        market_indexbtn.setOnClickListener(this);
        market_shenzhenbtn.setOnClickListener(this);
        market_platebtn.setOnClickListener(this);
        market_gangmeibtn.setOnClickListener(this);

        position = 0;
        fm = getChildFragmentManager();
        trans = fm.beginTransaction();

        trans.replace(R.id.market_fragment, selfchoseFragment);
        trans.commit();
        chose(0);
    }

    @Override
    public void onClick(View v) {
        trans = fm.beginTransaction();
        switch (v.getId()){
            case R.id.market_selfchosebtn:
                if(position==0){
                    return;
                }
                trans.replace(R.id.market_fragment, selfchoseFragment);
                trans.commit();

                chose(0);
                position=0;
                break;

            case R.id.market_indexbtn:
                if(position==1){
                    return;
                }
                trans.replace(R.id.market_fragment, indexFragment);
                trans.commit();

                chose(1);
                position=1;
                break;

            case R.id.market_shenzhenbtn:
                if(position==2){
                    return;
                }
                trans.replace(R.id.market_fragment, shenzhengFragment);
                trans.commit();

                chose(2);
                position=2;
                break;

            case R.id.market_platebtn:
                if(position==3){
                    return;
                }
                trans.replace(R.id.market_fragment, plateFragment);
                trans.commit();

                chose(3);
                position=3;
                break;

            case R.id.market_gangmeibtn:
                if(position==4){
                    return;
                }
                trans.replace(R.id.market_fragment, gangmeiFragment);
                trans.commit();

                chose(4);
                position=4;
                break;
        }
    }

    private void chose(int po){
        for (int i = 0; i < views.size(); i++) {
            if (i == po) {
                texts.get(i).setTextColor(getResources().getColor(R.color.home_color));
                views.get(i).setVisibility(View.VISIBLE);
            } else {
                texts.get(i).setTextColor(getResources().getColor(R.color.text_black));
                views.get(i).setVisibility(View.GONE);
            }

        }
    }
}
