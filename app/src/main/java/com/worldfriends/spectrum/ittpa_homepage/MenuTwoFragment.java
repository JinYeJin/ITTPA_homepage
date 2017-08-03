package com.worldfriends.spectrum.ittpa_homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class MenuTwoFragment extends SherlockFragment {

	TextView textTitleBar;
	View v;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		v = inflater.inflate(R.layout.menutwo, container, false);

		textTitleBar = (TextView) getSherlockActivity().findViewById(R.id.textTitle);
		textTitleBar.setText("Menu 2");
		
		return v;
	}

}