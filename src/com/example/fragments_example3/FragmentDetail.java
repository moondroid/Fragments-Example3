package com.example.fragments_example3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDetail extends Fragment {

	private TextView tvDetail;

	public static FragmentDetail newInstance(int index) {

		FragmentDetail f = new FragmentDetail();
		Bundle args = new Bundle();
		args.putInt("index", index);
		f.setArguments(args);

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_detail, container, false);

		tvDetail = (TextView) v.findViewById(R.id.tvDetail);

		Bundle b = getArguments();
		int index = 0;
		if (b != null) {
			index = getArguments().getInt("index", 0);
		}
		tvDetail.setText(Shakespeare.DIALOGUE[index]);

		return v;
	}

	public void setContent(int index) {

		if (tvDetail != null) {
			tvDetail.setText(Shakespeare.DIALOGUE[index]);
		}

	}

}
