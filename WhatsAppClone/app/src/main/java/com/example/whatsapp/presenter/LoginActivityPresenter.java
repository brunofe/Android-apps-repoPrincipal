package com.example.whatsapp.presenter;

import com.example.whatsapp.interfaces.LoginActivityContract;
import com.example.whatsapp.repository.FireBase;

import java.util.Random;

public class LoginActivityPresenter implements LoginActivityContract.Presenter,LoginActivityContract {
    private FireBase base;
    private View view;

    public LoginActivityPresenter(View view) {
        this.base = new FireBase();
        this.view = view;
    }


    //gerar token
    Random randomico = new Random();
    int numeroRandomico = randomico.nextInt( 9999 - 1000 ) + 1000;
    String token = String.valueOf( numeroRandomico );

    @Override
    public void loginOrRegisterUser(String name, String cellPhone) {
        cellPhone = formatString(cellPhone);


    }

    @Override
    public String formatString(String string) {
        String formatedString = string.replace("+","");
        formatedString = formatedString.replace("-","");
        return formatedString;
    }
}
