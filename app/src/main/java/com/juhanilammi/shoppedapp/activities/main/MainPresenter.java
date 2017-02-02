package com.juhanilammi.shoppedapp.activities.main;

import android.util.Log;

import com.juhanilammi.shoppedapp.activities.base.BasePresenter;
import com.juhanilammi.shoppedapp.dagger.components.DaggerDataComponent;
import com.juhanilammi.shoppedapp.dagger.components.DaggerMainComponent;
import com.juhanilammi.shoppedapp.dagger.components.DataComponent;
import com.juhanilammi.shoppedapp.dagger.components.MainComponent;
import com.juhanilammi.shoppedapp.dagger.modules.ApplicationModule;
import com.juhanilammi.shoppedapp.dagger.modules.DataModule;
import com.juhanilammi.shoppedapp.model.ShoppingItem;
import com.juhanilammi.shoppedapp.model.ShoppingList;
import com.juhanilammi.shoppedapp.modules.dataprovider.FirebaseDataProvider;
import com.juhanilammi.shoppedapp.modules.session.SessionManager;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Laemmi on 17.12.2016.
 */

public class MainPresenter extends BasePresenter<MainView>{

    @Inject
    FirebaseDataProvider dataProvider;
    DataComponent component;
    SessionManager manager;

    @Inject
    public MainPresenter(SessionManager sessionManager){
        component = DaggerDataComponent.builder().build();
        component.inject(this);
        manager = sessionManager;
    }

}
