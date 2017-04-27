package com.davidc.benchmarktemplate;

public interface BenchmarkService {
    void startBenchmarking(final Callback callback);
    boolean isBenchmarking(final Callback callback);
    void cancelBenchmarking(final Callback callback);

    interface Callback {
        @SuppressWarnings("unused")
        void onFinish(final String results);
        @SuppressWarnings("unused")
        void onError(final String error);
    }
}
