package com.example.fragmentsbasics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */

//se eu quiser criar manualmente um Fragment eu pos criar uma classe e extender fragment
public class LoginFragment extends Fragment {

    private TextView texto;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    //converte esse arquivo xml em uma view e o android consegue exibir essa tela: "R.layout.fragment_login"
    //esse fragmento vai ficar dentro desse container: "ViewGroup container"
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        texto = (TextView) view.findViewById(R.id.tv_login);
        texto.setText("Tela de Login alterada");

        return view;
    }

}
