package com.example.aulafirebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aulafirebase.models.Pessoa;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class PessoaDetalheActivity extends AppCompatActivity {
    EditText editNome, editCelular, editEmail;
    Button btnSalvar;
    String id = "";
    FirebaseFirestore db;
    Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pessoa_detalhe);
        db = FirebaseFirestore.getInstance();
        btnSalvar = (Button)findViewById(R.id.buttonSalvar);
        editNome = (EditText)findViewById(R.id.editTextNome);
        editCelular = (EditText) findViewById(R.id.editTextEmail);
        editCelular = (EditText) findViewById(R.id.editTextCelular);
        if(getIntent().hasExtra(id)) {
            id = getIntent().getStringExtra("id");
            db.collection("usuarios").document(id).get().addOnCompleteListener
                    (new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            pessoa = task.getResult().toObject(Pessoa.class);
                            pessoa.setId(task.getResult().getId());
                        }
                    });
        }

        btnSalvar.setOnClickListener(v -> {
            if(id.equals("")){
                pessoa = new Pessoa();
                pessoa.setNome(editNome.getText().toString());
                pessoa.setCelular(editCelular.getText().toString());
                pessoa.setEmail(editEmail.getText().toString());
                db.collection("pessoas").add(pessoa);
            }else{
                pessoa.setNome(editNome.getText().toString());
                pessoa.setCelular(editCelular.getText().toString());
                pessoa.setEmail(editEmail.getText().toString());
                db.collection("pessoas").document(id).set(pessoa);
            }
            Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}