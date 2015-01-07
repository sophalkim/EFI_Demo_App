package ssk.project.efi_demo_app.practice;

import ssk.project.efi_demo_app.CopyScanFragment;
import ssk.project.efi_demo_app.NavigationDrawerFragment;
import ssk.project.efi_demo_app.R;
import ssk.project.efi_demo_app.reddit_reader_fragment.RedditReaderFragment;
import ssk.project.efi_demo_app.ruby_on_rails_json_parser_fragment.Ruby_on_Rails_JSON_Parser_Fragment;
import ssk.project.efi_demo_app.settings.FaxFragment;
import ssk.project.efi_demo_app.settings.GeneralSetupFragment;
import ssk.project.efi_demo_app.settings.InitialSetupFragment;
import ssk.project.efi_demo_app.settings.InkSettingsFragment;
import ssk.project.efi_demo_app.settings.MachineInfoFragment;
import ssk.project.efi_demo_app.settings.NetworkFragment;
import ssk.project.efi_demo_app.settings.PrintReportsFragment;
import ssk.project.efi_demo_app.view_pager_fragment.ViewPagerFragment;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity2 extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;
	private CharSequence mTitle;
	int menuNumber = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
		mTitle = getTitle();
	}
	
	
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		switch (position) {
		case 0: 	fragmentManager.beginTransaction().replace(R.id.container,
				CopyScanFragment.newInstance(this)).commit();
				break;
		case 1: 	fragmentManager.beginTransaction().replace(R.id.container,
					RedditReaderFragment.newInstance("fitness")).commit();
					break;
		case 2:     fragmentManager.beginTransaction().replace(R.id.container,
					Ruby_on_Rails_JSON_Parser_Fragment.newInstance()).commit();
					menuNumber = 2;
					break;			
		case 3: 	fragmentManager.beginTransaction().replace(R.id.container,
					ViewPagerFragment.newInstance()).commit();
					menuNumber = 3;
					break;
		case 4: 	fragmentManager.beginTransaction().replace(R.id.container,
					InkSettingsFragment.newInstance()).commit();
					break;
		case 5: 	fragmentManager.beginTransaction().replace(R.id.container,
					GeneralSetupFragment.newInstance()).commit();
					break;
		case 6: 	fragmentManager.beginTransaction().replace(R.id.container,
					FaxFragment.newInstance()).commit();
					break;
		case 7: 	fragmentManager.beginTransaction().replace(R.id.container,
					NetworkFragment.newInstance()).commit();
					break;
		case 8: 	fragmentManager.beginTransaction().replace(R.id.container,
					PrintReportsFragment.newInstance()).commit();
					break;
		case 9: 	fragmentManager.beginTransaction().replace(R.id.container,
					MachineInfoFragment.newInstance()).commit();
					break;
		case 10: 	fragmentManager.beginTransaction().replace(R.id.container,
					InitialSetupFragment.newInstance()).commit();
					break;
		default:	fragmentManager.beginTransaction().replace(R.id.container,
					PlaceholderFragment.newInstance(position + 1)).commit();
					break;
		}
	}
	
	public void onSectionAttached(int number) {
		switch (number) {
		case 1: mTitle = getString(R.string.title_section1); break;
		case 2: mTitle = getString(R.string.title_section2); break;
		case 3: mTitle = getString(R.string.title_section3); break;
		}
	}
	
	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (mNavigationDrawerFragment.isDrawerOpen()) {
			restoreActionBar();
			return true;
		}
		switch (menuNumber) {
		case 0: return true;
		case 2: getMenuInflater().inflate(R.menu.menu_add_user, menu); return true;
		case 3: getMenuInflater().inflate(R.menu.preview, menu); return true;
		}
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public static class PlaceholderFragment extends Fragment {
		
		private static final String ARG_SECTION_NUMBER = "section_number";
		
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment pf = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			pf.setArguments(args);
			return pf;
		}
		
		public PlaceholderFragment() {}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
		
		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity2) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}
	
}
