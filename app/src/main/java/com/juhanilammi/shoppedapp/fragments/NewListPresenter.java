package com.juhanilammi.shoppedapp.fragments;

import android.util.Log;

import com.juhanilammi.shoppedapp.activities.base.BasePresenter;
import com.juhanilammi.shoppedapp.dagger.components.DaggerDataComponent;
import com.juhanilammi.shoppedapp.dagger.components.DataComponent;
import com.juhanilammi.shoppedapp.model.ShoppingList;
import com.juhanilammi.shoppedapp.modules.dataprovider.FirebaseDataProvider;
import com.juhanilammi.shoppedapp.modules.dataprovider.ShoppingListCache;
import com.juhanilammi.shoppedapp.modules.session.SessionManager;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Laemmi on 2.2.2017.
 */

public class NewListPresenter extends  BasePresenter<NewListView> {

    @Inject
    FirebaseDataProvider dataProvider;
    DataComponent component;
    SessionManager manager;

    public NewListPresenter(SessionManager sessionManager) {
        component = DaggerDataComponent.builder().build();
        component.inject(this);
        if(sessionManager == null ){
            Log.d("TEST", "NewListPresenter: sessionaman null");
        }
        manager = sessionManager;
    }


    public void onSaveInput(String string) {
    }

    public void onViewCreated() {
        if(manager != null){
            ShoppingListCache.getInstance().newList(manager.getUserID(), new ShoppingList()).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean aBoolean) {
                            Log.d("RESULT", "call: "+aBoolean);
                        }
                    });
        }


    }
}
