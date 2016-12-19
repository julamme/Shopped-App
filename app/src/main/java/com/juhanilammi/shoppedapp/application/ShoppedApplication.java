package com.juhanilammi.shoppedapp.application;

import android.app.Application;
import android.util.Log;

import com.juhanilammi.shoppedapp.dagger.components.ApplicationComponent;

import com.juhanilammi.shoppedapp.dagger.components.DaggerApplicationComponent;
import com.juhanilammi.shoppedapp.dagger.components.DataComponent;
import com.juhanilammi.shoppedapp.dagger.modules.ApplicationModule;
import com.juhanilammi.shoppedapp.dagger.modules.DataModule;

/**
 * Created by Laemmi on 17.12.2016.
 */

public class ShoppedApplication extends Application {

    ApplicationComponent applicationComponent;
    DataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);

    }


    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
