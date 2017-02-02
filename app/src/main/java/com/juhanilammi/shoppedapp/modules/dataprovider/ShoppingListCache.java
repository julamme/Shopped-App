package com.juhanilammi.shoppedapp.modules.dataprovider;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.juhanilammi.shoppedapp.model.ShoppingList;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by Laemmi on 2.2.2017.
 */

public class ShoppingListCache {
    private static ShoppingListCache instance;

    public static ShoppingListCache getInstance() {
        if(instance == null){
            instance = new ShoppingListCache();
        }
        return instance;
    }

    public ShoppingListCache() {
    }

    public Observable<Boolean> newList(final String userId, final ShoppingList list) {
        Log.d("new list", "newList: creating");
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                FirebaseDataProvider.getInstance().saveNewList(userId, list, new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if(!subscriber.isUnsubscribed()) {
                            if(aBoolean) {
                                subscriber.onNext(true);
                            } else {
                                subscriber.onNext(false);
                            }
                        }
                    }


                });
            }
        });
    }
}
