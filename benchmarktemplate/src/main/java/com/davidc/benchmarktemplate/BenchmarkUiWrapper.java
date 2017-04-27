package com.davidc.benchmarktemplate;

import android.os.Bundle;

import com.davidc.uiwrapper.UiWrapper;

final class BenchmarkUiWrapper extends UiWrapper<BenchmarkUi, BenchmarkUi.Listener> {
    private final static String ARG_SAVED_INSTANCE_STATE_UI_MODEL = "ui model";
    private final BenchmarkUiModel uiModel;
    private final BenchmarkService benchmarkService;

    private BenchmarkUiWrapper(BenchmarkUiModel uiModel, BenchmarkService benchmarkService) {
        this.uiModel = uiModel;
        this.benchmarkService = benchmarkService;
    }

    static BenchmarkUiWrapper newInstance(final BenchmarkUiModelFactory modelFactory, final BenchmarkService benchmarkService) {
        return new BenchmarkUiWrapper(modelFactory.create(), benchmarkService);
    }

    static BenchmarkUiWrapper savedElseNewInstance(final BenchmarkUiModelFactory modelFactory, final BenchmarkService benchmarkService, final Bundle savedInstanceState) {
        final BenchmarkUiModel uiModel = savedInstanceState.getParcelable(ARG_SAVED_INSTANCE_STATE_UI_MODEL);
        return uiModel == null ? newInstance(modelFactory, benchmarkService) : new BenchmarkUiWrapper(uiModel, benchmarkService);
    }

    @Override
    protected void registerResources() {
        super.registerResources();
        if (uiModel.isInLoadingState() && !benchmarkService.isBenchmarking(benchmarkServiceCallback)) {
            benchmarkService.startBenchmarking(benchmarkServiceCallback);
        }
    }

    @Override
    protected void unregisterResources() {
        super.unregisterResources();
        if (benchmarkService.isBenchmarking(benchmarkServiceCallback)) {
            benchmarkService.cancelBenchmarking(benchmarkServiceCallback);
        }
    }

    @Override
    protected void showCurrentUiState(BenchmarkUi ui) {
        uiModel.onto(ui);
    }

    @Override
    protected BenchmarkUi.Listener uiListener() {
        return new BenchmarkUi.Listener() {
            @Override
            public void startBenchmarking(BenchmarkUi ui) {
                if (!benchmarkService.isBenchmarking(benchmarkServiceCallback)) {
                    uiModel.showLoadingBenchmarks(ui);
                    benchmarkService.startBenchmarking(benchmarkServiceCallback);
                }
            }
        };
    }

    private final BenchmarkService.Callback benchmarkServiceCallback = new BenchmarkService.Callback() {
        @Override
        public void onFinish(String results) {
            uiModel.showBenchmarks(ui(), results);
            uiModel.showStartBenchmarking(ui());
        }

        @Override
        public void onError(String error) {
            uiModel.showError(ui(), error);
        }
    };

    @Override
    protected void saveState(Bundle outState) {
        outState.putParcelable(ARG_SAVED_INSTANCE_STATE_UI_MODEL, uiModel);
    }
}
