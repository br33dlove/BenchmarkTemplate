package com.davidc.benchmarktemplate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.davidc.uiwrapper.UiFragment;

public class BenchmarkFragment extends UiFragment<UiWrapperRepositoryImpl, BenchmarkUi.EventsListener> implements BenchmarkUi {
    private boolean showMenuItemStart = false;
    private TextView benchmarkTextView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static BenchmarkFragment newInstance() {
        return new BenchmarkFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_benchmark, container, false);
        benchmarkTextView = (TextView) view.findViewById(R.id.benchmarkText);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.ui_benchmark_toolbar_title);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_benchmark, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        final MenuItem item = menu.findItem(R.id.start);
        item.setVisible(showMenuItemStart);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.start) {
            if (hasEventsListener()) {
                eventsListener().startBenchmarking(BenchmarkFragment.this);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showStartBenchmarking() {
        showMenuItemStart = true;
        getActivity().invalidateOptionsMenu();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void showLoadingBenchmarks() {
        showMenuItemStart = false;
        getActivity().invalidateOptionsMenu();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void showBenchmarkText(String benchmarkText) {
        benchmarkTextView.setText(benchmarkText);
    }

    @Override
    protected BenchmarkUi.EventsListener bind(UiWrapperRepositoryImpl uiWrapperRepository, String instanceId, Bundle savedInstanceState) {
        return uiWrapperRepository.bind(this, instanceId, savedInstanceState);
    }

    @Override
    protected void unbind(UiWrapperRepositoryImpl uiWrapperRepository, String instanceId, Bundle outState, boolean isConfigurationChange) {
        uiWrapperRepository.unbind(this, instanceId, outState, isConfigurationChange);
    }
}