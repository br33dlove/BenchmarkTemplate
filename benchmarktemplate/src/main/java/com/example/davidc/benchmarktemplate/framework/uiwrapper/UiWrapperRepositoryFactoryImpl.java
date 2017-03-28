package com.example.davidc.benchmarktemplate.framework.uiwrapper;

import com.example.davidc.uiwrapper.UiWrapperRepositoryFactory;

public class UiWrapperRepositoryFactoryImpl implements UiWrapperRepositoryFactory<UiWrapperRepositoryImpl> {
    private final UiWrapperFactory uiWrapperFactory;

    public UiWrapperRepositoryFactoryImpl(UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    @Override
    public UiWrapperRepositoryImpl create() {
        return new UiWrapperRepositoryImpl(uiWrapperFactory);
    }
}
