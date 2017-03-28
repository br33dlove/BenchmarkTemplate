package com.davidc.benchmarktemplate;

import com.davidc.uiwrapper.Ui;

interface BenchmarkUi extends Ui {
    void showStartBenchmarking();
    void showLoadingBenchmarks();
    void showBenchmarkText(final String benchmarkText);

    interface EventsListener extends Ui.EventsListener {
        void startBenchmarking(final BenchmarkUi ui);
    }
}