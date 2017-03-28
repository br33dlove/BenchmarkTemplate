package com.example.davidc.benchmarktemplate;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.example.davidc.uiwrapper.SingleContentContainerWithAppBarActivity;

public class BenchmarkActivity extends SingleContentContainerWithAppBarActivity<UiWrapperRepositoryImpl> {

    @Override
    protected void setupActionBar(ActionBar actionBar) {
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    @Override
    protected Fragment initialFragment() {
        return BenchmarkFragment.newInstance();
    }
}
