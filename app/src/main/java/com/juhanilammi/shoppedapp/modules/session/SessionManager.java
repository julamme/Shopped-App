package com.juhanilammi.shoppedapp.modules.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.AndroidException;
import android.util.AndroidRuntimeException;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.juhanilammi.shoppedapp.activities.signin.SignInActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Juhani Lammi
 *         <p>
 *         SessionManager provides methods for session control and access to logged in user.
 *         Application mainly uses userId to access correct lists.
 */

public class SessionManager implements FirebaseAuth.AuthStateListener {
    private static final String TAG = SessionManager.class.getSimpleName();
    public SharedPreferences sharedPreferences;
    public Context context;
    FirebaseUser user;
    List<SessionListener> listeners;
    SignInListener signInListener;


    /**
     * @param sharedPreferences SharedPreferences injected with dagger 2
     * @param context           Application context injected with dagger
     */
    @Inject
    public SessionManager(SharedPreferences sharedPreferences, Context context) {
        this.context = context;
        this.sharedPreferences = sharedPreferences;
        listeners = new ArrayList<>();
    }

    /**
     * @param listener A listener that is implemented by Activity or Fragment that is interested about changes in session
     */
    public void setListener(SessionListener listener) {
        listeners.add(listener);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            setLoggedIn(user);
        } else {
            setLoggedOut();
        }
    }

    /**
     * Empty the user instance, sign out of firebase instance and notify all listeners that session has expired
     * Listeners should react by returning to a state that does not signing in. This mainly means the SignInActivity
     */
    private void setLoggedOut() {
        this.user = null;
        FirebaseAuth.getInstance().signOut();
        for (SessionListener listener : listeners) {
            listener.onSessionExpired();
        }
    }

    /**
     * Save user and notify listener that a session is now considered started
     * Logs a warning with MultipleSessionListenersException if multiple listeners are connected at this point. This is a wip solution. Will monitor if multiple sessionlisteners an issue. Signin should be from one place only though
     *
     * @param user User details given by Firebase
     */
    private void setLoggedIn(FirebaseUser user) {
        this.user = user;

        Log.d(TAG, "setLoggedIn: USER "+user);
        if (signInListener != null) {
            signInListener.onSignedIn();
        }
    }

    public void removeListener(SessionListener listener) {
        int index = listeners.indexOf(listener);
        listeners.remove(index);
    }

    public void setSignInListener(SignInListener listener) {
        signInListener = listener;
    }

    public String getUserID() {

        return user.getUid();

    }

    private static final class MultipleSessionListenersException extends RuntimeException {
        public MultipleSessionListenersException() {
            super();
        }

        public MultipleSessionListenersException(String s) {
            super(s);
        }

        public MultipleSessionListenersException(String s, Throwable throwable) {
            super(s, throwable);
        }

        public MultipleSessionListenersException(Throwable throwable) {
            super(throwable);
        }

    }


}
