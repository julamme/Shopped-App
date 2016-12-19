package com.juhanilammi.shoppedapp.modules.dataprovider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.juhanilammi.shoppedapp.model.ShoppingList;

/**
 * Created by Laemmi on 19.12.2016.
 */

public class FirebaseDataProvider {
    private DatabaseReference mDatabase;

    public FirebaseDataProvider(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    public void saveNewList(String userId, ShoppingList list){
        mDatabase.child("users").child(userId).push().setValue(list);
    }
}
