package ssk.project.efi_demo.app.ruby_on_rails_PUT_fragment;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import ssk.project.efi_demo_app.R;
import android.os.AsyncTask;
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
	
	private final String URL_TEMPLATE= "https://sophalkim.herokuapp.com/users";
	
	
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
				Runnable r = new Runnable()
				{
				    @Override
				    public void run()
				    {
				        try {
							postData();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				};

				Thread t = new Thread(r);
				t.start();
				Toast.makeText(getActivity(), name + ": Submitted to Ruby on Rails Server", Toast.LENGTH_LONG).show();
			}
        	
        });
        
        return v;
    }
    
    public void postData() throws JSONException {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(URL_TEMPLATE);
        name = etName.getText().toString();
        email = etEmail.getText().toString();
        try {
            // Add your data
        	JSONObject jsonobj = new JSONObject();
        	jsonobj.put("name", name);
        	jsonobj.put("email", email);
        	StringEntity se = new StringEntity(jsonobj.toString());
            httppost.setEntity(se);

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }
    
    class PostDataTask extends AsyncTask<Void, Void, Void> {

        protected void onPostExecute() {

        }

		@Override
		protected Void doInBackground(Void... params) {
			try {
				postData();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
    }
  
}
