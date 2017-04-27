package com.davidc.benchmarktemplate;

public final class BenchmarkUiModelFactory {
    private final static BenchmarkUiModel.ButtonState DEFAULT_BUTTON_STATE = BenchmarkUiModel.ButtonState.START_BENCHMARKING;
    private final static String DEFAULT_RESULTS = "";
    private final static String DEFAULT_ERROR = "";

    BenchmarkUiModel create() {
        return new BenchmarkUiModel(DEFAULT_BUTTON_STATE, DEFAULT_RESULTS, DEFAULT_ERROR);
    }
}
