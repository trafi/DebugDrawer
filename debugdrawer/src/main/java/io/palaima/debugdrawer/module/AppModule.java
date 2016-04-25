package io.palaima.debugdrawer.module;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import io.palaima.debugdrawer.R;

/**
 * Created by eligijus on 06/11/15.
 */
public class AppModule implements DrawerModule {

    private final Context context;
    private final String[] endpoints;
    private String selectedEndpoint;
    private AppModuleListener listener;

    public AppModule(Activity activity, String[] endpoints, String selectedEndpoint) {
        this.context = activity;
        this.endpoints = endpoints;
        this.selectedEndpoint = selectedEndpoint;
        this.listener = (AppModuleListener) activity;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.debug_drawer_item_app, parent, false);
        Switch logSwitch = (Switch) view.findViewById(R.id.enable_localytics_toasts);
        logSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onLocalyticsLogSwitchCheckedChanged(isChecked);
            }
        });
        Spinner spinner = (Spinner) view.findViewById(R.id.debug_network_endpoint);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.debug_view_spinner_item, endpoints);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        for (int i = 0; i < endpoints.length; i++) {
            if (endpoints[i].equals(selectedEndpoint)) {
                spinner.setSelection(i);
                break;
            }
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedEndpoint = endpoints[position];
                listener.onEndpointChange(endpoints[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        view.findViewById(R.id.button_onboarding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOnboardingButtonClick();
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

    public interface AppModuleListener {
        void onEndpointChange(String urlText);

        void onOnboardingButtonClick();

        void onLocalyticsLogSwitchCheckedChanged(boolean isChecked);
    }
}
