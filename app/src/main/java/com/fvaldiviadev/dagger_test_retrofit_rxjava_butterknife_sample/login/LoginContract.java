package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login;

public interface LoginContract {

    interface Model{
        void createUser(String firstName,String lastName);
        User getUser();
    }

    interface View{
        String getFirstName();
        String getLastName();

        void showUserNotAvailable();
        void showInputError();
        void showUserSaved();

        void setFirstName(String user);
        void setLastName(String lastName);
    }

    interface Presenter{
        void setView(LoginContract.View view);
        void loginButtonClicked();
        void getCurrentUser();
    }
}
