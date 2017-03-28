package com.davidc.benchmarktemplate;

import android.os.Bundle;

import com.davidc.uiwrapper.BaseUiWrapperRepository;
import com.davidc.uiwrapper.UiWrapper;
import com.davidc.uiwrapper.UiWrapperRepository;

import java.util.HashMap;
import java.util.Map;

public class UiWrapperRepositoryImpl extends BaseUiWrapperRepository implements UiWrapperRepository {
    private final UiWrapperFactory uiWrapperFactory;
    private final Map<String, UiWrapper<BenchmarkUi, BenchmarkUi.EventsListener>> benchmarkUiWrapperMap = new HashMap<>();

    UiWrapperRepositoryImpl(UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    BenchmarkUi.EventsListener bind(final BenchmarkUi ui, final String instanceId, final Bundle savedInstanceState) {
        return bind(ui, instanceId, benchmarkUiWrapperMap, new UiWrapperProvider<BenchmarkUi, BenchmarkUi.EventsListener>() {
            @Override
            public UiWrapper<BenchmarkUi, BenchmarkUi.EventsListener> uiWrapper() {
                return uiWrapperFactory.createBenchmarkUiWrapper(savedInstanceState);
            }
        });
    }

    void unbind(final BenchmarkUi ui, final String instanceId, final Bundle outState, final boolean isConfigurationChange) {
        unbind(instanceId, benchmarkUiWrapperMap, outState, isConfigurationChange);
    }
}
