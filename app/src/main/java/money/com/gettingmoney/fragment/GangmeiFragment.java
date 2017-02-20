package money.com.gettingmoney.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import money.com.gettingmoney.R;

public class GangmeiFragment extends Fragment implements View.OnClickListener {

    /**
     * 港美
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    private View view;
    private RelativeLayout mei_btn,gang_btn;
    private ImageView mei_img,gang_img;
    private int position=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gangmei,null);
        initView();
        return view;
    }

    private void initView() {
        mei_btn = (RelativeLayout) view.findViewById(R.id.mei_btn);
        gang_btn = (RelativeLayout) view.findViewById(R.id.gang_btn);
        mei_img = (ImageView) view.findViewById(R.id.mei_img);
        gang_img = (ImageView) view.findViewById(R.id.gang_img);

        mei_btn.setOnClickListener(this);
        gang_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mei_btn:
                if(position==0){
                    return;
                }
                mei_img.setVisibility(View.VISIBLE);
                gang_img.setVisibility(View.GONE);
                position=0;
                break;

            case R.id.gang_btn:
                if(position==1){
                    return;
                }
                mei_img.setVisibility(View.GONE);
                gang_img.setVisibility(View.VISIBLE);
                position=1;
                break;
        }
    }
}
