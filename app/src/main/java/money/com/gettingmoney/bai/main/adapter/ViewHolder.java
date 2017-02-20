package money.com.gettingmoney.bai.main.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 万能适配器的ViewHolder
 */
public class ViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    Context context;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        this.context = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,

                false);
        mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public ViewHolder setText(int viewId, String text,
                              View.OnClickListener listener) {
        TextView view = getView(viewId);
        view.setText(text);
        if (listener != null) {
            view.setOnClickListener(listener);
        }
        return this;
    }

    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public ViewHolder setLinearLayout(int viewId, int height, int width,
                                      int left, int top, int right, int bottom) {
        LinearLayout view = getView(viewId);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,
                height);
        params.setMargins(left, top, right, bottom);
        view.setLayoutParams(params);
        return this;
    }

    public ViewHolder setReleativeLayout(int viewId, int height, int width,
                                         int left, int top, int right, int bottom) {
        RelativeLayout views = getView(viewId);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                width, height);
        params.setMargins(left, top, right, bottom);
        views.setLayoutParams(params);
        return this;
    }

    public ViewHolder setFrameLayout(int viewId, int height, int width,
                                     int left, int top, int right, int bottom) {
        FrameLayout views = getView(viewId);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width,
                height);
        params.setMargins(left, top, right, bottom);
        views.setLayoutParams(params);
        return this;
    }

    public ViewHolder setLayoutParams(int viewId, int height, int width,
                                      int left, int top, int right, int bottom) {
        View view = getView(viewId);
        if (view instanceof RelativeLayout) {
            RelativeLayout views = (RelativeLayout) view;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    width, height);
            params.setMargins(left, top, right, bottom);
            views.setLayoutParams(params);
        }
        if (view instanceof LinearLayout) {
            RelativeLayout views = (RelativeLayout) view;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    width, height);
            params.setMargins(left, top, right, bottom);
            views.setLayoutParams(params);
        }
        if (view instanceof FrameLayout) {
            FrameLayout views = (FrameLayout) view;
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    width, height);
            params.setMargins(left, top, right, bottom);
            views.setLayoutParams(params);
        }

        return this;

    }

    public ViewHolder setAbsViewLayout(int viewId, int height, int width) {

        ViewGroup view = getView(viewId);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(width,
                height);
        view.setLayoutParams(params);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int drawableId,
                                       View.OnClickListener listener) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        if (listener != null) {
            view.setOnClickListener(listener);
        }

        return this;
    }

    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bm,
                                     View.OnClickListener listener) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        if (listener != null) {
            view.setOnClickListener(listener);
        }
        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 给指定ImageView的id下载图片
     *
     * @param viewId   图片id
     * @param url      图片地址
     * @param listener ImageView监听
     * @return ViewHolder
     */
    public ViewHolder setImageByUrl(int viewId, String url,
                                    View.OnClickListener listener) {
        SimpleDraweeView simpleDraweeView = getView(viewId);
        if (listener != null) {
            simpleDraweeView.setOnClickListener(listener);
        }
        simpleDraweeView.setImageURI(url);
        return this;
    }

    /**
     * 给指定ImageView的id下载图片
     *
     * @param viewId 图片id
     * @param url    图片地址
     * @return ViewHolder
     */
    public ViewHolder setImageByUrl(int viewId, String url) {
        SimpleDraweeView simpleDraweeView = getView(viewId);
        simpleDraweeView.setImageURI(url);
        return this;
    }

    public int getPosition() {
        return mPosition;
    }

}
