package com.juhanilammi.shoppedapp.modules.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.juhanilammi.shoppedapp.activities.signin.SignInActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Laemmi on 17.12.2016.
 */

public class SessionManager implements FirebaseAuth.AuthStateListener {
    private static final String TAG = SessionManager.class.getSimpleName();
    SharedPreferences sharedPreferences;
    Context context;
    FirebaseUser user;
    List<SessionListener> listeners;

    @Inject
    public SessionManager(SharedPreferences sharedPreferences, Context context){
        this.context = context;
        this.sharedPreferences = sharedPreferences;
        listeners = new ArrayList<>();
    }

    public void setListener(SessionListener listener){
        listeners.add(listener);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            setLoggedIn(user);
            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
        } else {
            setLoggedOut();
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }
        // ...
    }

    private void setLoggedOut() {
        this.user = null;
        FirebaseAuth.getInstance().signOut();
        for(SessionListener listener : listeners){
            listener.onSessionExpired();
        }
    }

    private void setLoggedIn(FirebaseUser user) {
        this.user = user;
    }


    public void removeListener(SessionListener listener) {
        int index = listeners.indexOf(listener);
        listeners.remove(index);
    }
}
