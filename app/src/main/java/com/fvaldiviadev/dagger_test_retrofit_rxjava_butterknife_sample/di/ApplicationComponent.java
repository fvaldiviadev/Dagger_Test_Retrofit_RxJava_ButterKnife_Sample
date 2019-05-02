package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.di;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.view.login.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules= ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity target);
}
