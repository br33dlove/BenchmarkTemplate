//Copyright 2017 David Cryer
//
//        Licensed under the Apache License, Version 2.0 (the "License");
//        you may not use this file except in compliance with the License.
//        You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//        Unless required by applicable law or agreed to in writing, software
//        distributed under the License is distributed on an "AS IS" BASIS,
//        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//        See the License for the specific language governing permissions and
//        limitations under the License.

package com.davidc.benchmarktemplate;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

public final class BenchmarkFragment extends UiFragment<UiWrapperRepositoryImpl, BenchmarkUi.Listener> implements BenchmarkUi {
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
            if (hasListener()) {
                listener().startBenchmarking(BenchmarkFragment.this);
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
    protected BenchmarkUi.Listener bind(@NonNull UiWrapperRepositoryImpl uiWrapperRepository, @NonNull String instanceId, Bundle savedInstanceState) {
        return uiWrapperRepository.bind(this, instanceId, savedInstanceState);
    }

    @Override
    protected void unbind(@NonNull UiWrapperRepositoryImpl uiWrapperRepository, @NonNull String instanceId, Bundle outState, boolean isConfigurationChange) {
        uiWrapperRepository.unbind(this, instanceId, outState, isConfigurationChange);
    }
}
