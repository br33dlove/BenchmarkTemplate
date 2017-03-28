package com.example.davidc.benchmarktemplate;

public interface BenchmarkService {
    void startBenchmarking(final Callback callback);
    boolean isBenchmarking(final Callback callback);
    void cancelBenchmarking(final Callback callback);

    interface Callback {
        void onFinish(final String results);
        void onError(final String error);
    }
}
