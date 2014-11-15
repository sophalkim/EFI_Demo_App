package ssk.project.efi_demo_app.settings;

import ssk.project.efi_demo_app.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WebImagesFragment extends Fragment {
	
	String[] x = {
			"Test Print",
			"Cleaning",
			"Ink Volume"
	};
     
    WebImagesFragment(){
    }    
     
    public static WebImagesFragment newInstance(){
        WebImagesFragment instance = new WebImagesFragment();        
        return instance;
    }
     
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.web_images_layout
                                , container
                                , false);
        ListView lv = (ListView) v.findViewById(R.id.list_view_1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, x);
        lv.setAdapter(adapter);
        return v;
    }
  
}
