package com.davidc.benchmarktemplate;

import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiWrapperRepositoryFactory;

@SuppressWarnings("unused")
public final class UiWrapperRepositoryFactoryImpl implements UiWrapperRepositoryFactory<UiWrapperRepositoryImpl> {
    private final UiWrapperFactory uiWrapperFactory;

    public UiWrapperRepositoryFactoryImpl(UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    @Override
    @NonNull
    public UiWrapperRepositoryImpl create() {
        return new UiWrapperRepositoryImpl(uiWrapperFactory);
    }
}
