package money.com.gettingmoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bean.News;
import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.weiget.LoadingDialog;

/**
 * Created by Administrator on 2017/2/20.
 */
public class NewsAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<News> list;
    private NewsAdapter adapter;
    private String imgurl = MyAppApiConfig.IMGHOST_URL;
    private LoadingDialog dialog;
    private int type;//0、不是收藏界面，1、是收藏界面
    public NewsAdapter(Context context, ArrayList<News> list,int type) {
        super();
        this.context = context;
        this.list = list;
        this.adapter = this;
        this.type = type;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_news,null);
            holder.news_name = (TextView) convertView.findViewById(R.id.news_name);
            holder.news_commentlayout = (LinearLayout) convertView.findViewById(R.id.news_commentlayout);
            holder.news_deletlayout = (LinearLayout) convertView.findViewById(R.id.news_deletlayout);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        if(type==0){
            holder.news_commentlayout.setVisibility(View.VISIBLE);
            holder.news_deletlayout.setVisibility(View.GONE);
        }else{
            holder.news_commentlayout.setVisibility(View.GONE);
            holder.news_deletlayout.setVisibility(View.VISIBLE);
        }
        News news = list.get(position);
        holder.news_name.setText(news.getNewsName());

        return convertView;

    }
    class Holder{
        public TextView news_name;
        public LinearLayout news_commentlayout;
        public LinearLayout news_deletlayout;
    }
}
