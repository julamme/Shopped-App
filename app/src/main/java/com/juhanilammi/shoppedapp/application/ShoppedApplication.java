package com.juhanilammi.shoppedapp.application;

import android.app.Application;
import android.util.Log;

import com.juhanilammi.shoppedapp.dagger.components.ApplicationComponent;

import com.juhanilammi.shoppedapp.dagger.components.DaggerApplicationComponent;
import com.juhanilammi.shoppedapp.dagger.modules.ApplicationModule;

/**
 * Created by Laemmi on 17.12.2016.
 */

public class ShoppedApplication extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("APPPPP", "onCreate: ");
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        component.inject(this);
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
