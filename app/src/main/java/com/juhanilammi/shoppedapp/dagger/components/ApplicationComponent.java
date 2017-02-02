package com.juhanilammi.shoppedapp.dagger.components;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.juhanilammi.shoppedapp.activities.base.BaseActivity;
import com.juhanilammi.shoppedapp.activities.main.MainActivity;
import com.juhanilammi.shoppedapp.activities.signin.SignInActivity;
import com.juhanilammi.shoppedapp.application.ShoppedApplication;
import com.juhanilammi.shoppedapp.dagger.modules.ApplicationModule;
import com.juhanilammi.shoppedapp.fragments.NewListFragment;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

/**
 * Created by Laemmi on 17.12.2016.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(ShoppedApplication app);

    void inject(SignInActivity signInActivity);

    void inject(MainActivity mainActivity);

     void inject(NewListFragment newListFragment);
    // <V extends MvpView, P extends MvpPresenter> void inject(BaseActivity<V, P> vpBaseActivity);
}
