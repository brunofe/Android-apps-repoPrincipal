package com.example.whatsapp.repository;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.interfaces.CallBackInserir;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FireBase {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public void updateDatabase(String userName, String attribute, String value, final CallBackInserir callBackInserir) {
        databaseReference.child(userName).child(attribute).setValue(value);

         databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String msg = "Sucesso ao adicionar: "+dataSnapshot.getValue().toString();
                callBackInserir.onSucess(msg);
                Log.i("FIREBASE", msg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                String msg = "Erro: "+ databaseError.getMessage();
                callBackInserir.onError(msg);
                Log.i("FIREBASE", msg);
            }
        });
    }
}
