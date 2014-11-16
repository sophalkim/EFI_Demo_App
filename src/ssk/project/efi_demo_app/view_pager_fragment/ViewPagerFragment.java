package ssk.project.efi_demo_app.view_pager_fragment;

import ssk.project.efi_demo_app.Custom_Image_ArrayAdapter;
import ssk.project.efi_demo_app.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

public class ViewPagerFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	private ViewPager awesomePager;
	private static int NUM_AWESOME_VIEWS = 4;
	private AwesomePagerAdapter awesomeAdapter;
	
	
	public static ViewPagerFragment newInstance() {
		ViewPagerFragment fragment = new ViewPagerFragment();
		return fragment;
	}

	public ViewPagerFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.view_pager_layout, container,
				false);
		awesomeAdapter = new AwesomePagerAdapter(this.getActivity());
        awesomePager = (ViewPager)rootView.findViewById(R.id.awesomepager);
        awesomePager.setAdapter(awesomeAdapter); 
		return rootView;
	}
	
	private class AwesomePagerAdapter extends PagerAdapter {
		
		Context context;
		
		private String[] x = {
				"Current Jobs",
				"List",
				"Printing Queue",
				"General Settings",
				"Ink Settings",
				"Fax",
				"Network",
				"Print Reports",
				"Machine Info",
				"Initial Setup"
		};
		
		public AwesomePagerAdapter(Activity activity) {
			context = activity;
		}
    	@Override
    	public int getCount() {
    		return NUM_AWESOME_VIEWS;
    	}
    	
    	@Override
    	public Object instantiateItem(ViewGroup collection, int position) {
    		if (position == 3) {
    			ListView lv = new ListView(context);
        		Custom_Image_ArrayAdapter adapter = new Custom_Image_ArrayAdapter(getActivity(), x);
        		lv.setAdapter(adapter);
        		collection.addView(lv, 3);
        		return lv;
    		}
    		ImageView iv = new ImageView(context);
    		iv.setImageResource(mThumbIds[position]);
    		collection.addView(iv, 0);    		
    		return iv;
    		
    	}
    	
    	private Integer[] mThumbIds = {    
    			R.drawable.word_screenshot,
    			R.drawable.pdf_screenshot,
    			R.drawable.house_screenshot,
    			R.drawable.family_screenshot
    	};
    	
    	@Override
    	public void destroyItem(ViewGroup collection, int position, Object view) {
    		collection.removeView((ImageView) view);
    	}
    	
    	@Override
    	public boolean isViewFromObject(View view, Object object) {
    		return (view==object);
    	}
    	
    	@Override
    	public void finishUpdate(ViewGroup arg0) {
    	}
    	
    	@Override
    	public void restoreState(Parcelable arg0, ClassLoader arg1) {
    	}
    	
    	@Override
    	public Parcelable saveState() {
    		return null;
    	}
    	
    	@Override
    	public void startUpdate(ViewGroup arg0) {
    	}
    	
    }
}
