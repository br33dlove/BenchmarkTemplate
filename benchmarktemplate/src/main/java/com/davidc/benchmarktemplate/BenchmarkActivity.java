package com.davidc.benchmarktemplate;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.davidc.uiwrapper.SingleContentContainerWithAppBarActivity;

public final class BenchmarkActivity extends SingleContentContainerWithAppBarActivity<UiWrapperRepositoryImpl> {

    @Override
    protected void setupActionBar(@NonNull ActionBar actionBar) {
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    @Override
    @NonNull
    protected Fragment initialFragment() {
        return BenchmarkFragment.newInstance();
    }
}
