package money.com.gettingmoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bean.Balance;

/**
 * Created by Administrator on 2017/2/21.
 */
public class BalanceAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Balance> list;
    public BalanceAdapter(Context context, ArrayList<Balance> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_balance,null);
            holder.balance_name = (TextView) convertView.findViewById(R.id.balance_name);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        Balance balance = list.get(position);
        holder.balance_name.setText(balance.getBalanceName());


        return convertView;

    }
    class Holder{
        public TextView balance_name;
    }
}
