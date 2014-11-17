package ssk.project.efi_demo_app;

import ssk.project.efi_demo_app.reddit_reader_fragment.RedditReaderFragment;
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

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;
	private CharSequence mTitle;
	boolean preview = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		switch (position) {
		case 0: 	fragmentManager.beginTransaction().replace(R.id.container,
					CopyScanFragment.newInstance(this)).commit();
					preview = false;
					break;
		case 1: 	fragmentManager.beginTransaction().replace(R.id.container,
					RedditReaderFragment.newInstance("fitness")).commit();
					preview = false;
					break;
		case 2:     fragmentManager.beginTransaction().replace(R.id.container,
					RedditReaderFragment.newInstance("fitness")).commit();
					preview = false;
					break;
		case 3: 	fragmentManager.beginTransaction().replace(R.id.container,
					ViewPagerFragment.newInstance()).commit();
					preview = true;
					break;
		case 4: 	fragmentManager.beginTransaction().replace(R.id.container,
					InkSettingsFragment.newInstance()).commit();
					preview = false;
					break;
		case 5: 	fragmentManager.beginTransaction().replace(R.id.container,
					GeneralSetupFragment.newInstance()).commit();
					preview = false;
					break;
		case 6: 	fragmentManager.beginTransaction().replace(R.id.container,
					FaxFragment.newInstance()).commit();
					preview = false;
					break;
		case 7: 	fragmentManager.beginTransaction().replace(R.id.container,
					NetworkFragment.newInstance()).commit();
					preview = false;
					break;
		case 8: 	fragmentManager.beginTransaction().replace(R.id.container,
					PrintReportsFragment.newInstance()).commit();
					preview = false;
					break;
		case 9: 	fragmentManager.beginTransaction().replace(R.id.container,
					MachineInfoFragment.newInstance()).commit();
					preview = false;
					break;
		case 10: 	fragmentManager.beginTransaction().replace(R.id.container,
					InitialSetupFragment.newInstance()).commit();
					preview = false;
					break;
		
		default: 	fragmentManager
					.beginTransaction()
					.replace(R.id.container,
					PlaceholderFragment.newInstance(position + 1)).commit();
					break;
		}
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
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
		if (!mNavigationDrawerFragment.isDrawerOpen() && !preview) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			restoreActionBar();
			return true;
		}
		if (preview) {
			getMenuInflater().inflate(R.menu.preview, menu);
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

}
