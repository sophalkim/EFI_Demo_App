package ssk.project.efi_demo.app.ruby_on_rails_PUT_fragment;

import ssk.project.efi_demo_app.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PutFragment extends Fragment {
    
	Button button;
	EditText etName;
	EditText etEmail;
	String name = "";
	String email = "";
	
	
    PutFragment(){
    }    
     
    public static PutFragment newInstance(){
        PutFragment instance = new PutFragment();        
        return instance;
    }
     
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.put_layout
                                , container
                                , false);
        etName = (EditText) v.findViewById(R.id.et_put_name);
        etEmail = (EditText) v.findViewById(R.id.et_put_email);
        button = (Button) v.findViewById(R.id.btn_put);
        button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				name = etName.getText().toString();
				email = etEmail.getText().toString();
				Toast.makeText(getActivity(), name + ": Submitted to Ruby on Rails Server", Toast.LENGTH_LONG).show();
			}
        	
        });
        
        return v;
    }
  
}
