package money.com.gettingmoney.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.activity.PaychoseActivity;
import money.com.gettingmoney.bean.Youcaidou;

/**
 * Created by Administrator on 2017/2/20.
 */
public class DouAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Youcaidou> list;
    public DouAdapter(Context context, ArrayList<Youcaidou> list) {
        super();
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder =null;
        if(convertView==null){
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dou,null);
            holder.dou_num = (TextView) convertView.findViewById(R.id.dou_num);
            holder.buy_dou = (TextView) convertView.findViewById(R.id.buy_dou);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        final Youcaidou dou = list.get(position);
        holder.dou_num.setText(dou.getYoucainum()+"个有财豆  ("+dou.getYoucaimoney()+"元)");
        holder.buy_dou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PaychoseActivity.class);
                intent.putExtra("paystyle","有财豆");
                intent.putExtra("paynum",dou.getYoucaimoney());
                context.startActivity(intent);
            }
        });

        return convertView;

    }
    class Holder{
        public TextView dou_num;
        public TextView buy_dou;
    }
}
