package com.juhanilammi.shoppedapp.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.common.api.GoogleApiClient;
import com.juhanilammi.shoppedapp.application.ShoppedApplication;
import com.juhanilammi.shoppedapp.modules.session.SessionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Laemmi on 17.12.2016.
 */
@Module
public class ApplicationModule {
    ShoppedApplication application;

    public ApplicationModule(ShoppedApplication application){
        this.application = application;
    }

    @Provides @Singleton
    public Context providesContext(){
        return application;
    }

    @Provides @Singleton
    public SharedPreferences providesSharedPreferences(){
        return application.getSharedPreferences(application.getPackageName(), Context.MODE_PRIVATE);
    }
    @Provides @Singleton
    public SessionManager providesSessionManager(SharedPreferences preferences, Context context){
        return new SessionManager(preferences, context);
    }

}
