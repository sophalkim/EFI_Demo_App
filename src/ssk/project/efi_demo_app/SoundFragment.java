package ssk.project.efi_demo_app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SoundFragment extends Fragment {

	Context context;
	
	public static SoundFragment newInstance(Context context) {
		SoundFragment fragment = new SoundFragment(context);
		return fragment;
	}

	public SoundFragment(Context context) {
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.sound_layout, container,
				false);
		TextView tv = (TextView) rootView.findViewById(R.id.soundTextView);
		tv.setText("Click the Button to play a sound");
//		Button button = (Button) rootView.findViewById(R.id.soundButton);
//		button.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				soundPool.play(one, 1, 1, 1, 0, 1);
//			}
//			
//		});
		return rootView;
	}
}
