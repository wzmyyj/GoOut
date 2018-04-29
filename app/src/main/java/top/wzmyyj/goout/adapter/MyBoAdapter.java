package top.wzmyyj.goout.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyBoAdapter extends PagerAdapter {
	private ArrayList<ImageView> imageList;

	public MyBoAdapter(ArrayList<ImageView> imageList) {
		this.imageList = imageList;
	}

	public int getCount() {
		return Integer.MAX_VALUE;
	}

	public Object instantiateItem(ViewGroup container, int position) {

		container.addView(imageList.get(position % imageList.size()));
		return imageList.get(position % imageList.size());
	}

	public boolean isViewFromObject(View view, Object object) {
		if (view == object) {
			return true;
		} else {
			return false;
		}
	}

	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
		object = null;
	}

}
