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

import com.davidc.uiwrapper.UiWrapper;

final class BenchmarkUiWrapper extends UiWrapper<BenchmarkUi, BenchmarkUi.Listener, BenchmarkUiModel> {
    private final BenchmarkService benchmarkService;

    private BenchmarkUiWrapper(BenchmarkUiModel uiModel, BenchmarkService benchmarkService) {
        super(uiModel);
        this.benchmarkService = benchmarkService;
    }

    static BenchmarkUiWrapper newInstance(final BenchmarkUiModelFactory modelFactory, final BenchmarkService benchmarkService) {
        return new BenchmarkUiWrapper(modelFactory.create(), benchmarkService);
    }

    static BenchmarkUiWrapper savedElseNewInstance(final BenchmarkUiModelFactory modelFactory, final BenchmarkService benchmarkService, final Bundle savedInstanceState) {
        final BenchmarkUiModel uiModel = savedUiModel(savedInstanceState);
        return uiModel == null ? newInstance(modelFactory, benchmarkService) : new BenchmarkUiWrapper(uiModel, benchmarkService);
    }

    @Override
    protected void registerResources() {
        super.registerResources();
        if (uiModel().isInLoadingState() && !benchmarkService.isBenchmarking(benchmarkServiceCallback)) {
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
    protected BenchmarkUi.Listener uiListener() {
        return new BenchmarkUi.Listener() {
            @Override
            public void startBenchmarking(BenchmarkUi ui) {
                if (!benchmarkService.isBenchmarking(benchmarkServiceCallback)) {
                    uiModel().showLoadingBenchmarks(ui);
                    benchmarkService.startBenchmarking(benchmarkServiceCallback);
                }
            }
        };
    }

    private final BenchmarkService.Callback benchmarkServiceCallback = new BenchmarkService.Callback() {
        @Override
        public void onFinish(String results) {
            uiModel().showBenchmarks(ui(), results);
            uiModel().showStartBenchmarking(ui());
        }

        @Override
        public void onError(String error) {
            uiModel().showError(ui(), error);
        }
    };
}
