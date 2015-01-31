package ssk.project.efi_demo_app.network_connect;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import ssk.project.efi_demo_app.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

	public static final String TAG = "Network Connect";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.network_connect_main);
		
		TextViewFragment introFragment = (TextViewFragment)
				getSupportFragmentManager().findFragmentById(R.id.intro_fragment);
		introFragment.setText(R.string.welcome_message);
		introFragment.getTextView().setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16.0f);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.fetch_action:
			new DownloadTask().execute("http://www.google.com");
			return true;
		}
		return false;
	}
	
	private class DownloadTask extends AsyncTask<String, Void, String> {
		
		@Override
		protected String doInBackground(String... urls) {
			try {
				return loadFromNetwork(urls[0]);
			} catch (IOException e) {
				return getString(R.string.connection_error);
			}
		}
		
		@Override
		protected void onPostExecute(String result) {
			Log.i(TAG, result);
		}
	}
	
	private String loadFromNetwork(String urlString) throws IOException {
		InputStream stream = null;
		String str = "";
		
		try {
			stream = downloadUrl(urlString);
			str = readIt(stream, 500);
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
		return str;
	}
	
	private InputStream downloadUrl(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.connect();
		InputStream stream = conn.getInputStream();
		return stream;
	}
	
	private String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[len];
		reader.read(buffer);
		return new String(buffer);
	}
	
	
}
