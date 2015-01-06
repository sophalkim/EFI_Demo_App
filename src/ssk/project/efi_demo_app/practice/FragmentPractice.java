package ssk.project.efi_demo_app.practice;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ssk.project.efi_demo_app.R;
import ssk.project.efi_demo_app.ruby_on_rails_json_parser_fragment.Post;
import ssk.project.efi_demo_app.ruby_on_rails_json_parser_fragment.PostsHolder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentPractice extends Fragment {

	ListView lv;
	ArrayAdapter<Post> adapter;
	Handler handler;
	
	String subreddit;
	List<Post> posts;
	PostsHolder postsHolder;
	
	FragmentPractice() {
		handler = new Handler();
		posts = new ArrayList<Post>();
	}
	
	public static Fragment newInstance() {
		FragmentPractice fp = new FragmentPractice();
		fp.postsHolder = new PostsHolder(fp.subreddit);
		return fp;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.posts, container, false);
		lv = (ListView) v.findViewById(R.id.list_view_1);
		return v;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initialize();
	}
	
	private void initialize() {
		if (posts.size() == 0) {
			new Thread() {
				public void run() {
					posts.addAll(postsHolder.fetchPosts());
					handler.post(new Runnable() {
						public void run() {
							createAdapter();
						}
					});
				}
			}.start();
		} else {
			createAdapter();
		}
	}
	
	private void createAdapter() {
		if (getActivity() == null) return;
		adapter = new ArrayAdapter<Post>(getActivity(), R.layout.post_item, posts) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					convertView = getActivity().getLayoutInflater().inflate(R.layout.post_item, null);
				}
				TextView postTitle;
				postTitle = (TextView) convertView.findViewById(R.id.post_title);
				ImageView thumbs;
				thumbs = (ImageView) convertView.findViewById(R.id.image_view);
				postTitle.setText(posts.get(position).title);
				thumbs.setImageBitmap(getBitmapFromURL(posts.get(position).thumbnail));
				return convertView;
			}
		};
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
