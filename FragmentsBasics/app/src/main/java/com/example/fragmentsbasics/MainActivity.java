package com.example.fragmentsbasics;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button botaoLogar;
    public Boolean status=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoLogar = findViewById(R.id.bt_logar);


        botaoLogar.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    FragmentManager fm= getSupportFragmentManager();
                    FragmentTransaction ft;

                    LoginFragment loginFragment = new LoginFragment();
                    CadastroFragment cadastroFragment=new CadastroFragment();

                    if(status){
                        ft = fm.beginTransaction();
                        if(cadastroFragment.isAdded()){
                            ft.replace(R.id.rl_container_fragmento,loginFragment);
                        } else {
                            ft.add(R.id.rl_container_fragmento, loginFragment);
                        }
                         ft.addToBackStack(null);
                         ft.commit();
                         botaoLogar.setText("Cadastre-se");
                         status=false;

                      }else {
                         ft = fm.beginTransaction();
                         ft.replace(R.id.rl_container_fragmento,cadastroFragment);
                         ft.addToBackStack(null);
                         ft.commit();
                         botaoLogar.setText("Logar");
                         status=true;
                     }
            }
        }));
    }
}