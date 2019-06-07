package com.example.whatsapp.interfaces;

public interface MainPresenterContract {
    interface Presenter{
        void updateUserInfo(String userName,String atribute, String value);
    }

    interface View {
        void showToast(String msg);
    }
}
