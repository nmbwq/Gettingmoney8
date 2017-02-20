package money.com.gettingmoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.weiget.LoadingDialog;

/**
 * Created by Administrator on 2017/2/18.
 */
public class StockAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list;
    private StockAdapter adapter;
    private String imgurl = MyAppApiConfig.IMGHOST_URL;
    private LoadingDialog dialog;
    public StockAdapter(Context context, ArrayList<String> list) {
        super();
        this.context = context;
        this.list = list;
        this.adapter = this;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.stock_item,null);
            holder.stock_name = (TextView) convertView.findViewById(R.id.stock_name);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        holder.stock_name.setText(list.get(position));

        return convertView;

    }
    class Holder{

        TextView stock_name;
    }
}
