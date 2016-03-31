package com.crm.local;

import android.app.AlertDialog;
import android.content.Context;

public class LocalAlertDialog {

	private Context context;
	private String title;
	

	public LocalAlertDialog(Context context, String title) {
		super();
		this.context = context;
		this.title = title;
	}

	public AlertDialog alertDialog(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
		AlertDialog dialog = builder.create();
		dialog.setTitle(this.title);
		
		return dialog;
	}
}
