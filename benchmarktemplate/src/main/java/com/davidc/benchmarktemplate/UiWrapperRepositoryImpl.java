package com.davidc.benchmarktemplate;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.BaseUiWrapperRepository;
import com.davidc.uiwrapper.UiWrapper;

import java.util.HashMap;
import java.util.Map;

public final class UiWrapperRepositoryImpl extends BaseUiWrapperRepository {
    private final UiWrapperFactory uiWrapperFactory;
    private final Map<String, UiWrapper<BenchmarkUi, BenchmarkUi.Listener, BenchmarkUiModel>> benchmarkUiWrapperMap = new HashMap<>();

    UiWrapperRepositoryImpl(UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    BenchmarkUi.Listener bind(final BenchmarkUi ui, final String instanceId, final Bundle savedInstanceState) {
        return bind(ui, instanceId, benchmarkUiWrapperMap, new UiWrapperProvider<BenchmarkUi, BenchmarkUi.Listener, BenchmarkUiModel>() {
            @Override
            @NonNull
            public UiWrapper<BenchmarkUi, BenchmarkUi.Listener, BenchmarkUiModel> uiWrapper() {
                return uiWrapperFactory.createBenchmarkUiWrapper(savedInstanceState);
            }
        });
    }

    void unbind(final BenchmarkUi ui, final String instanceId, final Bundle outState, final boolean isConfigurationChange) {
        unbind(instanceId, benchmarkUiWrapperMap, outState, isConfigurationChange);
    }
}
