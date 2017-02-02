package com.juhanilammi.shoppedapp.activities.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.juhanilammi.shoppedapp.application.ShoppedApplication;
import com.juhanilammi.shoppedapp.modules.session.SessionManager;

import javax.inject.Inject;

/**
 * Created by Laemmi on 17.12.2016.
 */

public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> implements BaseMvpView {
}
