package com.worldfriends.spectrum.ittpa_homepage;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class MenuOneFragment extends SherlockFragment {

	TextView textTitleBar;
	View v;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


		v = inflater.inflate(R.layout.menuone, container, false);


		textTitleBar = (TextView) getSherlockActivity().findViewById(R.id.textTitle);
		textTitleBar.setText("Thanh hoa ITTPA hompage");


		return v;
	}



}


