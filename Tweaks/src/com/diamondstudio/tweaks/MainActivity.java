package com.diamondstudio.tweaks;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import me.denley.preferenceinjector.InjectPreference;
import me.denley.preferenceinjector.OnPreferenceChange;
import me.denley.preferenceinjector.PreferenceDefault;
import me.denley.preferenceinjector.PreferenceInjector;

public class MainActivity extends ActionBarActivity {

	static final String LOG_TAG = "Perf_Tag";
	
    @InjectPreference("key_server_url")
    String server_url = "initialized value in code";
    
    @OnPreferenceChange("key_server_url")
    void onPreferenceChanged(String new_server_url) {
    	// Todo: What is the thread context of this method. 
    	server_url = new_server_url;
    	Log.d(LOG_TAG, "Current url: " + server_url);
    }
    
    @OnPreferenceChange("view_background_color")
    void onBackgroundChanged(int newColor) {
    	mDemoView.setBackgroundColor(newColor);
    }
    
    private View mDemoView;
    
    public void onButtonClick(View v) {
    	Log.d(LOG_TAG, "onChangeServerUrl touched!");
    	Intent intent = new Intent(this, ServerEditorActivity.class);
    	startActivity(intent);
    }
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDemoView = (View)findViewById(R.id.view1);
        PreferenceInjector.inject(this, "PreferenceInjectorPrefs");
        Log.d(LOG_TAG, "server_url: " + server_url);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
}
