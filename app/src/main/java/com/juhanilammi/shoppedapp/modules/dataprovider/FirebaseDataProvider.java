package com.juhanilammi.shoppedapp.modules.dataprovider;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.juhanilammi.shoppedapp.model.ShoppingList;

import rx.Observable;
import rx.Observer;

/**
 * Created by Laemmi on 19.12.2016.
 */

public class FirebaseDataProvider {
    private static FirebaseDataProvider instance;
    private DatabaseReference mDatabase;

    public static FirebaseDataProvider getInstance() {
        if(instance == null) {
            instance = new FirebaseDataProvider();
        }
        return instance;
    }

    public FirebaseDataProvider(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    public void saveNewList(final String userId, final ShoppingList list, final Observer obs){

        mDatabase.child("users").child(userId).push().setValue(list).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d("firebase", "onComplete:  ok");
                    obs.onNext(true);
                } else {
                    Log.d("firebase", "onComplete:  failed");
                    obs.onNext(false);
                }
            }
        });
    }
}
