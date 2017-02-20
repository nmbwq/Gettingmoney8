package money.com.gettingmoney.bai.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 万能适配器
 *
 * @param <T> 指定泛型
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    public List<T> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void addmDatas(List<T> mDatas) {
        if (this.mDatas == null) {
            this.mDatas = mDatas;
        } else {
            this.mDatas.addAll(mDatas);
        }
        notifyDataSetChanged();
    }

    public void addmDatas(T mDatas) {
        if (this.mDatas == null) {
            this.mDatas = new ArrayList<T>();
            this.mDatas.add(mDatas);
        } else {
            this.mDatas.add(mDatas);
        }
        notifyDataSetChanged();
    }

    public void addmDatas(int postion, T mDatas) {
        if (this.mDatas == null) {
            this.mDatas = new ArrayList<T>();
            this.mDatas.add(mDatas);
        } else {
            this.mDatas.add(postion, mDatas);
        }
        notifyDataSetChanged();
    }

    public void removeData(int position) {
        if (position >= 0 && position < mDatas.size()) {
            mDatas.remove(position);
        }
        notifyDataSetChanged();
    }

    public void removeData(T data) {
        if (mDatas != null) {
            mDatas.remove(data);
        }
        notifyDataSetChanged();
    }


    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();

    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();

    }

    public abstract void convert(ViewHolder helper, T item);

    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }
}
