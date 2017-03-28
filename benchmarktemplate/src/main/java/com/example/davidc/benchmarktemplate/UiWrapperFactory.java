package com.example.davidc.benchmarktemplate;

import android.os.Bundle;

public class UiWrapperFactory {
    private final BenchmarkUiModelFactory benchmarkUiModelFactory;
    private final BenchmarkService benchmarkService;

    public UiWrapperFactory(BenchmarkUiModelFactory benchmarkUiModelFactory, BenchmarkService benchmarkService) {
        this.benchmarkUiModelFactory = benchmarkUiModelFactory;
        this.benchmarkService = benchmarkService;
    }

    BenchmarkUiWrapper createBenchmarkUiWrapper(final Bundle savedInstanceState) {
        return savedInstanceState == null
                ? BenchmarkUiWrapper.newInstance(benchmarkUiModelFactory, benchmarkService)
                : BenchmarkUiWrapper.savedElseNewInstance(benchmarkUiModelFactory, benchmarkService, savedInstanceState);
    }
}
