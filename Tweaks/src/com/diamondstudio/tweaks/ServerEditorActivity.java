package com.diamondstudio.tweaks;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import me.denley.preferenceinjector.InjectPreference;
import me.denley.preferenceinjector.OnPreferenceChange;
import me.denley.preferenceinjector.PreferenceInjector;

public class ServerEditorActivity extends Activity {
	
	TextView mServerUrlTextView = null;
	
	@InjectPreference("key_server_url")
	String mServerUrl;
	
	@OnPreferenceChange("key_server_url")
    void onPreferenceChanged(String new_server_url) {
		if (mServerUrlTextView != null) {
			mServerUrl = new_server_url;
			mServerUrlTextView.setText(mServerUrl);
		} else {
			Log.d("XXX", "mServerUrlTextView is null");
		}
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_server_editor);
    	PreferenceInjector.inject(this, "PreferenceInjectorPrefs");
    	mServerUrlTextView = (TextView) findViewById(R.id.textview_server_url);
    	mServerUrlTextView.setText(mServerUrl);
    	mServerUrlTextView.setBackgroundResource(R.color.red);
    	//mServerUrlTextView.setBackgroundColor();
    }
    
    public void onSaveServerUrl(View v) {
    	SharedPreferences prefs = getSharedPreferences("PreferenceInjectorPrefs", Context.MODE_PRIVATE);
        // final boolean booleanPrefValue = !prefs.getBoolean("boolean_pref_key", false);
        // final int integerPrefValue = (prefs.getInt("integer_pref_key", 0) + 1) % 100;
        Date nowDate = new Date();
        
        mServerUrlTextView.setText(nowDate.toString());
        
        prefs.edit()
                .putString("key_server_url", nowDate.toString())
                .commit();
    }
    
    public void onChangeColor(View v) {
    	EditText inputBoxRed = (EditText)findViewById(R.id.editText1);
    	EditText inputBoxGreen = (EditText)findViewById(R.id.editText2);
    	EditText inputBoxBlue = (EditText)findViewById(R.id.editText3);
    	Integer red = Integer.parseInt(inputBoxRed.getText().toString());
    	Integer green = Integer.parseInt(inputBoxGreen.getText().toString());
    	Integer blue = Integer.parseInt(inputBoxBlue.getText().toString());
    	int newColor = Color.argb(0xFF, red, green, blue);
    	
    	SharedPreferences prefs = getSharedPreferences("PreferenceInjectorPrefs", Context.MODE_PRIVATE);
    	prefs.edit()
    			.putInt("view_background_color", newColor)
    			.commit();
    	
    	finish();
    }
}
