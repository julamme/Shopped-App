package com.juhanilammi.shoppedapp.dagger.modules;

import com.juhanilammi.shoppedapp.modules.dataprovider.FirebaseDataProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Laemmi on 19.12.2016.
 */

@Module
public class DataModule {

    @Provides @Singleton
    FirebaseDataProvider providesDataProvider(){
        return new FirebaseDataProvider();
    }
}
