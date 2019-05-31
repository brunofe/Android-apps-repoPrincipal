package firebaseapp.cursoandroid.com;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {



                                                //utilizando esse metodo é possivel recuperar a instancia
                                                //e fazer alteraçoes direto no banco de dados
    private DatabaseReference fireDatabaseReference = FirebaseDatabase.getInstance().getReference();

    //criando nós para adcionar dados
    private DatabaseReference usuarioReferencia = fireDatabaseReference.child("usuarios");
    private DatabaseReference produtoReferencia = fireDatabaseReference.child("produtos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adicionando dados:
        Usuario usuario = new Usuario();
        usuario.setNome("Ana Helena");
        usuario.setSobrenome("Silva");
        usuario.setSexo("feminino");
        usuario.setIdade(35);

        usuarioReferencia.child("001").child("nome").setValue("Jamilton Damasceno");
        usuarioReferencia.child("002").child("nome").setValue("Maria Almeida");
        usuarioReferencia.child("003").setValue( usuario );


        Produto produto = new Produto();
        produto.setCor("Azul");
        produto.setDescricao("detergente lava louça");
        produto.setFabricante("Azulin");

        produtoReferencia.child("001").setValue( produto );



        /*********** recupeando dados ***********/
        produtoReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
