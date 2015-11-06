package io.palaima.debugdrawer.module;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.jakewharton.madge.MadgeFrameLayout;

import io.palaima.debugdrawer.R;

/**
 * Created by eligijus on 06/11/15.
 */
public class MadgeModule implements DrawerModule {

    private final Context mContext;
    private ViewGroup mRootView;

    public MadgeModule(Activity activity) {
        mContext = activity;
        mRootView = (ViewGroup) activity.findViewById(android.R.id.content);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        ViewGroup contentView = (ViewGroup) mRootView.getChildAt(0);
        ViewGroup scrimInsets = (ViewGroup) contentView.getChildAt(0);
        View contentRelativeView = scrimInsets.getChildAt(0);

        scrimInsets.removeView(contentRelativeView);

        final MadgeFrameLayout madgeFrameLayout = new MadgeFrameLayout(mContext);
        madgeFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        scrimInsets.addView(madgeFrameLayout);
        madgeFrameLayout.addView(contentRelativeView);

        View view = inflater.inflate(R.layout.debug_drawer_item_madge, parent, false);
        Switch debugEnableMadge = (Switch) view.findViewById(R.id.debug_enable_madge);
        debugEnableMadge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                madgeFrameLayout.setOverlayEnabled(isChecked);
            }
        });
        Switch debugEnableRatio = (Switch) view.findViewById(R.id.debug_enable_ratio);
        debugEnableRatio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                madgeFrameLayout.setOverlayRatioEnabled(isChecked);
            }
        });
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
