package com.juhanilammi.shoppedapp.dagger.components;

import com.juhanilammi.shoppedapp.activities.main.MainPresenter;
import com.juhanilammi.shoppedapp.dagger.modules.ApplicationModule;
import com.juhanilammi.shoppedapp.dagger.modules.DataModule;
import com.juhanilammi.shoppedapp.fragments.NewListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Laemmi on 19.12.2016.
 */
@Singleton
@Component(modules = {DataModule.class})
public interface DataComponent {

    void inject(MainPresenter presenter);

    void inject(NewListPresenter presenter);
}
