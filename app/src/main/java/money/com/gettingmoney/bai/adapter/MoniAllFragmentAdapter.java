package money.com.gettingmoney.bai.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 默认的fragment切换适配器
 * 
 * @author Administrator
 * 
 */
public class MoniAllFragmentAdapter extends FragmentPagerAdapter {
	private List<Fragment> datas;

	public List<Fragment> getDatas() {
		return datas;
	}

	public void setDatas(List<Fragment> datas) {
		this.datas = datas;
	}

	public MoniAllFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public MoniAllFragmentAdapter(FragmentManager fm, List<Fragment> data) {
		super(fm);
		datas = data;
	}

	@Override
	public Fragment getItem(int arg0) {
		return datas.get(arg0);
	}

	@Override
	public int getCount() {
		if (datas == null) {
			return 0;
		} else {
			return datas.size();
		}
	}

}
