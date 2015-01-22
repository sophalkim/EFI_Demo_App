package ssk.project.efi_demo_app.practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class RemoteData {

	public static HttpURLConnection getConnection(String url) {
		System.out.println("URL: " + url);
		HttpURLConnection hcon = null;
		try {
			hcon = (HttpURLConnection) new URL(url).openConnection();
			hcon.setReadTimeout(30000); // Timeout at 30 seconds
			hcon.setRequestProperty("User-Agent", "Alien V1.0");
		} catch (MalformedURLException e) {
			Log.e("getConnection()",
					"Invalid URL: " + e.toString());
		} catch (IOException e) {
			Log.e("getConnection()",
					"Could not connect: " + e.toString());
		}
		return hcon;
	}
	
	public static String readContents(String url) {
		HttpURLConnection hcon = getConnection(url);
		if (hcon == null) return null;
		try {
			StringBuffer sb = new StringBuffer(8192);
			String tmp = "";
			BufferedReader br = new BufferedReader( 
					new InputStreamReader(hcon.getInputStream()));
			while ((tmp = br.readLine()) != null) {
				sb.append(tmp).append("\n");
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			Log.d("READ FAILED", e.toString());
			return null;
		}
	}
	
	public static Bitmap getBitmapFromURL(String src) {
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
