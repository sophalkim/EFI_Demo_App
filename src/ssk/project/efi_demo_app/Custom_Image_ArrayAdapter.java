package ssk.project.efi_demo_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Custom_Image_ArrayAdapter extends ArrayAdapter<String> {
	private final Context mContext;
	private final String[] values;
	private Integer[] mThumbIds = {
			R.drawable.icon_color_print, 
			R.drawable.icon_black_print, 
			R.drawable.icon_search,
			R.drawable.icon_settings,
			R.drawable.icon_ink,
			R.drawable.icon_fax,
			R.drawable.icon_network,
			R.drawable.icon_report,
			R.drawable.icon_printer,
			R.drawable.icon_wizard 
	};

	public Custom_Image_ArrayAdapter(Context context, String[] values) {
		super(context, R.layout.custom_row, values);
		mContext = context;
		this.values = values;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.custom_row, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.image_view_1);
		textView.setText(values[position]);
		imageView.setImageResource(mThumbIds[position]);
//		switch (position) {
//			case 0: imageView.setImageResource(mThumbIds[0]); break;
//			case 1: imageView.setImageResource(mThumbIds[1]); break;
//			case 2: imageView.setImageResource(mThumbIds[2]); break;
//			default: imageView.setImageResource(mThumbIds[0]); break;
//		}
//		if (position%2==0) {
//			imageView.setImageResource(mThumbIds[0]);
//    	} else {
//    		imageView.setImageResource(mThumbIds[1]);
//    	}
		return rowView;
	}
}


