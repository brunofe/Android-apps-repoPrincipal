package com.example.whatsapp.interfaces;

public interface LoginActivityContract {
    interface Presenter{
        String formatString(String string);
        void loginOrRegisterUser(String name,String cellPhone);
    }

    interface View {
    }
}
