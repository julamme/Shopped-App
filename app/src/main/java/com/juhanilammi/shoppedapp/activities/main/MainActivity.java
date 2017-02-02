package com.juhanilammi.shoppedapp.activities.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.firebase.database.FirebaseDatabase;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.juhanilammi.shoppedapp.R;
import com.juhanilammi.shoppedapp.activities.base.BaseActivity;
import com.juhanilammi.shoppedapp.activities.signin.SignInActivity;
import com.juhanilammi.shoppedapp.application.ShoppedApplication;
import com.juhanilammi.shoppedapp.dagger.modules.ApplicationModule;
import com.juhanilammi.shoppedapp.fragments.NewListFragment;
import com.juhanilammi.shoppedapp.modules.session.SessionListener;
import com.juhanilammi.shoppedapp.modules.session.SessionManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Laemmi on 17.12.2016.
 */

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView, SessionListener {

    @Inject
    SessionManager sessionManager;
    @BindView(R.id.new_list_button)
    FrameLayout button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((ShoppedApplication) getApplication()).getComponent().inject(this);

        sessionManager.setListener(this);

    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(sessionManager);
    }

    @Override
    public void onSessionExpired() {
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
        this.finish();
    }

    @OnClick(R.id.new_list_button)
    public void onNewListClicked(){
        Fragment fragment = NewListFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_holder, fragment);
        transaction.commit();
        //getPresenter().saveDemoList(sessionManager.getUserID());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sessionManager.removeListener(this);
    }
}
