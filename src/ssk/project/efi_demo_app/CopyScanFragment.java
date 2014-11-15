package ssk.project.efi_demo_app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CopyScanFragment extends Fragment {

	Context context;
	
	public static CopyScanFragment newInstance(Context context) {
		CopyScanFragment fragment = new CopyScanFragment(context);
		return fragment;
	}

	public CopyScanFragment(Context context) {
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.copy_scan_layout, container,
				false);
		TextView tv = (TextView) rootView.findViewById(R.id.tv_select_action);
//		Button button = (Button) rootView.findViewById(R.id.soundButton);
//		button.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				soundPool.play(one, 1, 1, 1, 0, 1);
//			}
//			
//		});
		ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar_black_ink);
		return rootView;
	}
}
