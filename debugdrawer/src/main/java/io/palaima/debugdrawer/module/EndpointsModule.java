package io.palaima.debugdrawer.module;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.palaima.debugdrawer.R;

/**
 * Created by eligijus on 06/11/15.
 */
public class EndpointsModule implements DrawerModule {

    private final Context mContext;
    private ViewGroup mRootView;

    public EndpointsModule(Activity activity) {
        mContext = activity;
        mRootView = (ViewGroup) activity.findViewById(android.R.id.content);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {

        View view = inflater.inflate(R.layout.debug_drawer_item_endpoints, parent, false);

        return view;
    }

    @Override
    public void onOpened() {

    }

    @Override
    public void onClosed() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
