package com.example.whatsapp.interfaces;

public interface LoginActivityContract {
    interface Presenter{
        String formatString(String string);
        String generateToken();
        boolean enviaSMS(String telefone, String mensagen);
        void loginOrRegisterUser(String name,String cellPhone);
    }

    interface View {
    }
}
