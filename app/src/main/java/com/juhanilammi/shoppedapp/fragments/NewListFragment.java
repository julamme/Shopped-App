package com.juhanilammi.shoppedapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.juhanilammi.shoppedapp.R;
import com.juhanilammi.shoppedapp.activities.base.BaseFragment;
import com.juhanilammi.shoppedapp.activities.main.MainActivity;
import com.juhanilammi.shoppedapp.activities.signin.SignInActivity;
import com.juhanilammi.shoppedapp.application.ShoppedApplication;
import com.juhanilammi.shoppedapp.modules.session.SessionListener;
import com.juhanilammi.shoppedapp.modules.session.SessionManager;

import javax.inject.Inject;

import butterknife.BindFloat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Laemmi on 31.1.2017.
 */

public class NewListFragment extends BaseFragment<NewListView, NewListPresenter> implements NewListView, SessionListener {
    @BindView(R.id.name_list_input) EditText mRecyclerView;

    @BindView(R.id.add_item_button)
    FrameLayout mAdditemButton;

    @Inject
    SessionManager sessionManager;

    public static NewListFragment newInstance() {
        NewListFragment fragment = new NewListFragment();
        return fragment;
    }

    @Override
    public NewListPresenter createPresenter() {
        return new NewListPresenter(sessionManager);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((ShoppedApplication) getActivity().getApplication()).getComponent().inject(this);
        return inflater.inflate(R.layout.fragment_newlist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        sessionManager.setListener(this);
        getPresenter().onViewCreated();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        sessionManager.removeListener(this);
    }

    @Override
    public void onSessionExpired() {
        getActivity().finish();
    }

    @OnClick(R.id.add_item_button)
    public void onAddPressed(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle(getString(R.string.add_item));
        final EditText textInput = new EditText(getContext());
        dialogBuilder.setView(textInput).setPositiveButton(getString(R.string.save), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getPresenter().onSaveInput(textInput.getText().toString());
            }
        }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }
}
