package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.di;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.LoginContract;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.LoginRepository;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.LoginRepositoryImpl;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.Model.LoginModel;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.Presenter.LoginPresenter;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.StreamMostViewed;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    public LoginContract.Presenter provideLoginPresenger(LoginContract.Model model){
        return new LoginPresenter(model);
    }

    @Provides
    public LoginContract.Model provideLoginModel(LoginRepository repository){
        return new LoginModel(repository);
    }

    @Provides
    public LoginRepository proviceLoginRepository(){
        return new LoginRepositoryImpl();
    }

    @Provides
    public StreamMostViewed provideStreamMostViewed(){
        return new StreamMostViewed();
    }
}
