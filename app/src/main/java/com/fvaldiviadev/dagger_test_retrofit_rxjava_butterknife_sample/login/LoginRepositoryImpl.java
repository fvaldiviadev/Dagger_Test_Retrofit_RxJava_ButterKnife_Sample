package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login;

public class LoginRepositoryImpl implements LoginRepository {

    //Methods for persistence (WebService, SQLite,...)

    private User user;

    @Override
    public void saveUser(User user) {
        if(user==null){
            user=getUser();
        }
        this.user=user;
    }

    @Override
    public User getUser() {

        if(user==null){
            user=new User("John","Doe");
            user.setId(0);
            return user;
        }else{
            return user;
        }
    }
}
