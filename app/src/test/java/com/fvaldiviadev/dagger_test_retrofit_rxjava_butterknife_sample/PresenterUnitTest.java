package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.LoginContract;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.Presenter.LoginPresenter;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterUnitTest {

    LoginPresenter presenter;
    User user;

    LoginContract.Model mockedModel;
    LoginContract.View mockedView;

    @Before
    public void initialization(){
        mockedModel=mock(LoginContract.Model.class);
        mockedView=mock(LoginContract.View.class);

        user=new User("John", "Doe");

        when(mockedModel.getUser()).thenReturn(user);

        when(mockedView.getFirstName()).thenReturn("John");
        when(mockedView.getLastName()).thenReturn("Doe");

        presenter = new LoginPresenter(mockedModel);
        presenter.setView(mockedView);
    }
    @Test
    public void noExistsInteractionWithView(){
        presenter.getCurrentUser();
        verifyZeroInteractions(mockedView);
    }

    @Test
    public void showErrorMessageWhenUserIsNull(){

        when(mockedModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        //Interaction with model
        verify(mockedModel,times(1)).getUser();

        verify(mockedView,never()).setFirstName("John");
        verify(mockedView,never()).setLastName("Doe");
        verify(mockedView,times(1)).showUserNotAvailable();

    }

    @Test
    public void createErrorMessageIfAnyFieldIsEmpty(){
        //First test: firstname empty
        when(mockedView.getFirstName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockedView,times(1)).getFirstName();
        verify(mockedView,never()).getLastName();
        verify(mockedView,times(1)).showInputError();

        //Second test: firstName with value and lastName empty
        when(mockedView.getFirstName()).thenReturn("John");
        when(mockedView.getLastName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockedView,times(2)).getFirstName(); //The method is called twice: on previous test and now
        verify(mockedView,times(1)).getLastName();
        verify(mockedView,times(2)).showInputError();


    }

    @Test
    public void saveValidUser(){
        when(mockedView.getFirstName()).thenReturn("John");
        when(mockedView.getLastName()).thenReturn("Doe");

        presenter.loginButtonClicked();

        verify(mockedView,times(2)).getFirstName(); //The method is called twice: on previous test and now
        verify(mockedView,times(2)).getLastName();

        verify(mockedModel,times(1)).createUser("John","Doe");

        verify(mockedView,times(1)).showUserSaved();
    }

}
