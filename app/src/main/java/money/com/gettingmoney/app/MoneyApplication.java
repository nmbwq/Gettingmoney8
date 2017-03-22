package money.com.gettingmoney.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;

import java.io.File;

import money.com.gettingmoney.R;

/**
 * Created by Administrator on 2017/2/17.
 */
public class MoneyApplication extends Application {
    private static MoneyApplication instance;
    public static ImageLoader imageLoader;
    public static DisplayImageOptions options;


    private SharedPreferences mySharedPreferences;
    private SharedPreferences.Editor editor;


    public static  MoneyApplication getInstance() {
        return instance;
    }
    public static MoneyApplication getContext() {
        return instance;
    }
    @Override
    public void onCreate() {
        instance = this;

        x.Ext.init(this);
        x.Ext.setDebug(true);
        UMShareAPI.get(this);
//		initImageLoader(getApplicationContext());
        int memClass = 4;//((ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        int cacheSize = 1024 * 1024 * memClass / 2 ;
        File cacheDir = StorageUtils.getCacheDirectory(this);


        L.writeDebugLogs(false);
        L.writeLogs(false);
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.icon_stub)
                .resetViewBeforeLoading(true)  // default
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .showImageForEmptyUri(R.mipmap.icon_empty)
                .showImageOnFail(R.mipmap.icon_erro)
                .showImageOnLoading(R.mipmap.icon_empty)
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED) // default
                .bitmapConfig(Bitmap.Config.RGB_565) // default
                .displayer(new FadeInBitmapDisplayer(100)) // default
                .handler(new Handler()) // default
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .memoryCacheExtraOptions(480, 800)// default = device screen dimensions
                .threadPoolSize(2)// default
                .threadPriority(Thread.NORM_PRIORITY - 2)// default
                .tasksProcessingOrder(QueueProcessingType.LIFO)// default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(cacheSize))
                .memoryCacheSize(cacheSize)
                .discCacheFileCount(20)
                .diskCache(new UnlimitedDiscCache(cacheDir)) // default
                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout(5s), readTimeout(30s)��ʱʱ��
                .writeDebugLogs().build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

        //异常捕捉，不会弹出异常框，影响体验,打包时解封
//        CrashHandler handler = CrashHandler.getInstance();
//        handler.init(getApplicationContext());
//        Thread.setDefaultUncaughtExceptionHandler(handler);

    }

    {

        PlatformConfig.setWeixin("wx99581f72ef59420b", "6a78d65bff7b905d2e94f4d93d8d512d");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }


}
