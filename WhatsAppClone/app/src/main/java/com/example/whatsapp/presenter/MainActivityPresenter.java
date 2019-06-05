package com.example.whatsapp.presenter;
import com.example.whatsapp.interfaces.CallBackInserir;
import com.example.whatsapp.interfaces.presenterContract;
import com.example.whatsapp.repository.FireBase;

public class MainActivityPresenter implements presenterContract {
    private FireBase base;
    private View view;

    public MainActivityPresenter(View view) {
        this.base = new FireBase();
        this.view = view;
    }

    public void updateUserInfo(String userName,String atribute, String value) {
        base.updateDatabase(userName,atribute,value, new CallBackInserir() {
            @Override
            public void onSucess(String msg) {
                view.showToast(msg);
            }

            @Override
            public void onError(String msg) {
                view.showToast(msg);
            }
        });
    }

    public interface View {
        void showToast(String msg);
    }
}
