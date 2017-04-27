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

public final class UiWrapperFactory {
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
