package money.com.gettingmoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bean.BankCard;

/**
 * Created by Administrator on 2016/11/5.
 */
public class BankCardAdapter extends BaseAdapter {


    private ArrayList<BankCard> cardlist;
    private Context context;
    private BankCardAdapter adapter;
    private int itemtype = 1;
    private OnRemoveListener mRemoveListener;
    public BankCardAdapter(Context context, ArrayList<BankCard> list) {
        super();
        this.context = context;
        this.cardlist = list;
        adapter = this;
    }


    public void setRemoveListener(OnRemoveListener removeListener) {
        this.mRemoveListener = removeListener;
    }

    public int getItemtype() {
        return itemtype;
    }

    public void setItemtype(int itemtype) {
        this.itemtype = itemtype;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return cardlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if(convertView==null){
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bankcard,null);
            holder.bank_delete = (TextView) convertView.findViewById(R.id.bank_delete);
            holder.bank_name = (TextView) convertView.findViewById(R.id.bank_name);
            holder.bankcard_num = (TextView) convertView.findViewById(R.id.bankcard_num);
            holder.bankcard_style = (TextView) convertView.findViewById(R.id.bankcard_style);
            convertView.setTag(holder);
        }else {
            holder = (Holder)convertView.getTag();
        }

        BankCard card = cardlist.get(position);
        holder.bank_name.setText(card.getBankName());
//        String banknum = card.getBankNumber();
//        int length=banknum.length();
//        String str;
//        if(length>11) {
//            str = banknum.substring(0, length - 11) + "*******" + banknum.substring(length - 2);
//        }else{
//            str = banknum;
//        }
//        holder.bankcard_num.setText(str);

        holder.bankcard_style.setText("储蓄卡");
        holder.bank_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRemoveListener!=null) {
                    mRemoveListener.onRemoveItem(position);
                }
            }
        });
        return convertView;
    }

    public interface OnRemoveListener {
        void onRemoveItem(int position);
    }
    class Holder{
        TextView bank_name;
        TextView bankcard_style;
        TextView bankcard_num;
        TextView bank_delete;
    }
}
