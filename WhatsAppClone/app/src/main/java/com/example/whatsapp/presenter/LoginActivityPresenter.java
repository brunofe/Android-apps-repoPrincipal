package com.example.whatsapp.presenter;

import android.telephony.SmsManager;

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
    @Override
    public String generateToken() {
        Random randomico = new Random();
        int numeroRandomico = randomico.nextInt( 9999 - 1000 ) + 1000;
        String token = String.valueOf( numeroRandomico );
        return token;
    }

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

    public boolean enviaSMS(String telefone, String mensagen){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone, null, mensagen, null, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
