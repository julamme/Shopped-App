package com.juhanilammi.shoppedapp.activities.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.juhanilammi.shoppedapp.activities.base.BaseActivity;
import com.juhanilammi.shoppedapp.dagger.modules.ApplicationModule;

/**
 * Created by Laemmi on 17.12.2016.
 */

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return new MainPresenter();
    }
}
