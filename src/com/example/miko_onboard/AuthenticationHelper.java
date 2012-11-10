package com.example.miko_onboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AuthenticationHelper {
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	private DatabaseHelper dbh;
	
	public AuthenticationHelper(Context context) {
		this.settings = PreferenceManager.getDefaultSharedPreferences(context);
		this.editor = settings.edit();
	}
	public void loginUser(String username) {
		dbh = new DatabaseHelper();
		dbh.SaveUser(username);
		editor.putString("username", username);
	} 
	
	public void logoutUser() {
		editor.remove("username");
	}
	
	public boolean isUserLoggedIn(String username) {
		return false;
	}
}
