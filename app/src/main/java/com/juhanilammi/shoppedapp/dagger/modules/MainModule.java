package com.juhanilammi.shoppedapp.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.juhanilammi.shoppedapp.activities.main.MainPresenter;
import com.juhanilammi.shoppedapp.modules.dataprovider.FirebaseDataProvider;
import com.juhanilammi.shoppedapp.modules.session.SessionManager;

import javax.inject.Singleton;

import dagger.Module;

/**
 * Created by Laemmi on 19.12.2016.
 */
@Module(includes = {ApplicationModule.class, DataModule.class})
public interface MainModule {
    Context context();
    SharedPreferences sharedPreferences();
    SessionManager sessionManager();
    FirebaseDataProvider firebaseDataProvider();

    void inject(MainPresenter presenter);
}
