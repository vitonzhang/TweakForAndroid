// Generated code from Preference Injector. Do not modify!
package com.diamondstudio.tweaks;

import android.view.View;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import java.util.HashMap;
import me.denley.preferenceinjector.PreferenceInjector.Injector;

public class MainActivity$$SharedPreferenceInjector<T extends com.diamondstudio.tweaks.MainActivity> implements Injector<T> {
    private HashMap<T, SharedPreferences> prefsMap = new HashMap<T, SharedPreferences>();
    private HashMap<T, OnSharedPreferenceChangeListener> listenerMap = new HashMap<T, OnSharedPreferenceChangeListener>();

    @Override public void inject(Context context, final T target, SharedPreferences prefs) {
        initializeTarget(target, prefs);

        OnSharedPreferenceChangeListener listener = new OnSharedPreferenceChangeListener() {
            @Override public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                updateTarget(target, prefs, key);
            }
        };

        prefsMap.put(target, prefs);
        listenerMap.put(target, listener);
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override public void stopListening(T target) {
        SharedPreferences prefs = prefsMap.remove(target);
        OnSharedPreferenceChangeListener listener = listenerMap.remove(target);
        if (prefs!=null && listener!=null) {
            prefs.unregisterOnSharedPreferenceChangeListener(listener);
        }
    }

    private void initializeTarget(T target, SharedPreferences prefs) {
        if (prefs.contains("key_server_url")) {
            java.lang.String key_server_url = prefs.getString("key_server_url", null);
            target.server_url = key_server_url;
        }

    }

    private void updateTarget(T target, SharedPreferences prefs, String key) {
        if (key.equals("key_server_url")) {
            if (prefs.contains("key_server_url")) {
                java.lang.String key_server_url = prefs.getString("key_server_url", null);
                target.onPreferenceChanged(key_server_url);
            }
        } else if (key.equals("view_background_color")) {
            if (prefs.contains("view_background_color")) {
                int view_background_color = prefs.getInt("view_background_color", 0);
                target.onBackgroundChanged(view_background_color);
            }
        }
    };

}
