package com.example.fragments_example3;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentTitles extends ListFragment {

	// To allow a Fragment to communicate up to its Activity,
	// you can define an interface in the Fragment class and implement it within
	// the Activity
	private FragmentListener mCallback;

	private static int mPosition = 0;
	
	// Container Activity must implement this interface
	public interface FragmentListener {
		public void onTitleClick(int position);
	}

	// The Fragment captures the interface implementation during its onAttach()
	// lifecycle method
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			mCallback = (FragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement FragmentListener");
		}
	}



	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_activated_1, Shakespeare.TITLES);
		setListAdapter(adapter);
		
		this.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		this.getListView().setItemChecked(mPosition, true);
		
	}

	@Override
	public void onResume() {
		super.onResume();
		this.getListView().setItemChecked(mPosition, true);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		mCallback.onTitleClick(position);
		mPosition = position;
	}

}
