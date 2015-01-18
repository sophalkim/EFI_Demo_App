package ssk.project.efi_demo_app.practice2;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import ssk.project.efi_demo_app.ruby_on_rails_json_parser_fragment.RemoteData;
import android.util.Log;

public class PostsHolder {

	private final String URL_TEMPLATE = "https://sophalkim.herokuapp.com/users.json";
	
	String subreddit;
	String url;
	String after;
	
	public PostsHolder(String subreddit) {
		this.subreddit = subreddit;
		after = "";
		url = URL_TEMPLATE;
	}
	
	private void generateURL() {
		url = URL_TEMPLATE.replace("SUBREDDIT_NAME", subreddit);
		url = url.replace("AFTER", after);
	}
	
	public List<Post> fetchPosts() {
		String raw = RemoteData.readContents(url);
		List<Post> list = new ArrayList<Post>();
		try {
			JSONArray data = new JSONArray(raw);
			for (int i = 0; i < data.length(); i++) {
				JSONObject cur = data.getJSONObject(i);
				Post p = new Post();
				p.title = cur.optString("name");
				p.url = cur.optString("email");
				if (p.title != null)
					list.add(p);
			}
		} catch (Exception e) {
			Log.e("fetchPosts()", e.toString());
		}
		return list;
	}
	
	List<Post> fetchMorePosts() {
		generateURL();
		return fetchPosts();
	}
}
