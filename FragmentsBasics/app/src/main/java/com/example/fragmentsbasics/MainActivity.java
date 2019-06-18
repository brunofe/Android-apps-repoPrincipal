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

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    LoginFragment loginFragment = new LoginFragment();
                    CadastroFragment cadastroFragment=new CadastroFragment();;

                    if(status){
                         if(cadastroFragment.isAdded()){
                             fragmentTransaction.hide(cadastroFragment);
                         }
                         fragmentTransaction.add(R.id.rl_container_fragmento, loginFragment);
                         fragmentTransaction.commit();
                         botaoLogar.setText("Cadastre-se");
                         status=false;
                      }else {
                        if(loginFragment.isAdded()){
                            fragmentTransaction.hide(loginFragment);
                        }
                         fragmentTransaction.add(R.id.rl_container_fragmento, cadastroFragment);
                         fragmentTransaction.commit();
                         botaoLogar.setText("Logar");
                         status=true;
                     }
            }
        }));
    }
}
