package com.example.whatsapp.interfaces;

public interface OldLoginActivityContract {
    interface Presenter{
        String formatString(String string);
        String generateToken();
        boolean enviaSMS(String telefone, String mensagen);
        void loginOrRegisterUser(String name,String cellPhone);
    }

    interface View {
    }
}
