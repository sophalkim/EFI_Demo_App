package ssk.project.efi_demo_app.network_connect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextViewFragment2 extends Fragment {

	public static final String TEXT_KEY = "text";
	public static final String TEXT_ID_KEY = "text_id";
	
	String mText;
	int mTextId = -1;
	private TextView mTextView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		processArguments();
		mTextView = new TextView(getActivity());
		mTextView.setGravity(Gravity.CENTER);
		if (mText != null) {
			mTextView.setText(mText);
			Log.i("SimpleTextFragment", mText);
		}
		return mTextView;
	}
	
	public void processArguments() {
		if (getArguments() != null) {
			Bundle args = getArguments();
			if (args.containsKey(TEXT_KEY)) {
				mText = args.getString(TEXT_KEY);
				Log.d("Constructor", "Added Text.");
			} else if (args.containsKey(TEXT_ID_KEY)) {
				mTextId = args.getInt(TEXT_ID_KEY);
				mText = getString(mTextId);
			}
		}
	}
}
