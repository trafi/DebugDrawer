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
    private final String[] mapStyles;
    private String selectedMapStyle;
    private AppModuleListener listener;

    public AppModule(Activity activity, String[] endpoints, String selectedEndpoint,
                     String[] mapStyles, String selectedMapStyle) {
        this.context = activity;
        this.listener = (AppModuleListener) activity;
        this.endpoints = endpoints;
        this.selectedEndpoint = selectedEndpoint;
        this.mapStyles = mapStyles;
        this.selectedMapStyle = selectedMapStyle;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.debug_drawer_module_app, parent, false);

        Spinner endpointSpinner = (Spinner) view.findViewById(R.id.debug_network_endpoint);
        ArrayAdapter<String> endpointAdapter = new ArrayAdapter<>(context,
                R.layout.debug_view_spinner_item, endpoints);
        endpointAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endpointSpinner.setAdapter(endpointAdapter);
        for (int i = 0; i < endpoints.length; i++) {
            if (endpoints[i].equals(selectedEndpoint)) {
                endpointSpinner.setSelection(i);
                break;
            }
        }
        endpointSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (endpoints[position].equals(selectedEndpoint)) {
                    return;
                }
                selectedEndpoint = endpoints[position];
                listener.onEndpointChange(selectedEndpoint);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner mapStyleSpinner = (Spinner) view.findViewById(R.id.map_style_endpoint);
        ArrayAdapter<String> mapStyleAdapter = new ArrayAdapter<>(context,
                R.layout.debug_view_spinner_item, mapStyles);
        mapStyleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mapStyleSpinner.setAdapter(mapStyleAdapter);
        for (int i = 0; i < mapStyles.length; i++) {
            if (mapStyles[i].equals(selectedMapStyle)) {
                mapStyleSpinner.setSelection(i);
                break;
            }
        }
        mapStyleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mapStyles[position].equals(selectedMapStyle)) {
                    return;
                }
                selectedMapStyle = mapStyles[position];
                listener.onMapStyleChange(selectedMapStyle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Switch logSwitch = (Switch) view.findViewById(R.id.enable_localytics_toasts);
        logSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onLocalyticsLogSwitchCheckedChanged(isChecked);
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
        void onEndpointChange(String url);

        void onMapStyleChange(String url);

        void onOnboardingButtonClick();

        void onLocalyticsLogSwitchCheckedChanged(boolean isChecked);
    }
}
