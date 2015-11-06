package io.palaima.debugdrawer.module;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import io.palaima.debugdrawer.R;

/**
 * Created by eligijus on 06/11/15.
 */
public class EndpointsModule implements DrawerModule {

    private final Context context;
    private final String[] endpoints;
    private String selectedEndpoint;
    private OnEndpointChanged listener;

    public EndpointsModule(Activity activity, String[] endpoints, String selectedEndpoint) {
        this.context = activity;
        this.endpoints = endpoints;
        this.selectedEndpoint = selectedEndpoint;
        this.listener = (OnEndpointChanged) activity;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.debug_drawer_item_endpoints, parent, false);
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

    public interface OnEndpointChanged {
        void onEndpointChange(String urlText);
    }
}
