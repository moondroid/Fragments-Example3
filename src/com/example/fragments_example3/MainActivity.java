package com.example.fragments_example3;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements
		FragmentTitles.FragmentListener {

	private int orientation;
	private FragmentManager fragmentManager;
	private FragmentTitles fTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fragmentManager = getSupportFragmentManager();

		orientation = getResources().getConfiguration().orientation;

		// programmatically add the first fragment
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		fTitles = (FragmentTitles) fragmentManager.findFragmentByTag("titles");
		if (orientation == Configuration.ORIENTATION_PORTRAIT
				&& fTitles == null) {
			fTitles = new FragmentTitles();
			fragmentTransaction.add(R.id.fragment_container, fTitles, "titles");
			fragmentTransaction.commit();
		}

	}

	@Override
	public void onTitleClick(int position) {

		if (orientation == Configuration.ORIENTATION_PORTRAIT) {
			changeFragment(position);
		} else {
			FragmentDetail fDetail = (FragmentDetail) fragmentManager
					.findFragmentById(R.id.fragment_detail);
			fDetail.setContent(position);
		}
	}

	private void changeFragment(int position) {
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		// set animation for the fragments that are entering and exiting in this
		// transaction
		// setCustomAnimations (int enter, int exit, int popEnter, int popExit)
		// The popEnter and popExit animations will be played for enter/exit
		// operations specifically when popping the back stack
		 fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit,
		 R.anim.pop_enter, R.anim.pop_exit);

		// replace first fragment with second fragment
		FragmentDetail fDetail = FragmentDetail.newInstance(position);
		fragmentTransaction.replace(R.id.fragment_container, fDetail);

		// you might want to call addToBackStack(), in order to add the
		// transaction to a back stack of fragment transactions.
		// This back stack is managed by the activity and allows the user to
		// return to the previous fragment state, by pressing the Back button.
		fragmentTransaction.addToBackStack(null);

		fragmentTransaction.commit();

	}

}
