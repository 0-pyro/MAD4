package com.example.thirdtimesthecharm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        EditText email, pass;
        Button button;
        TextView redir;
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        button = findViewById(R.id.button);
        redir = findViewById(R.id.redirect);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString().trim();
                String passw = pass.getText().toString().trim();

                if(user.isEmpty()) {
                    email.setError("Enter mail");
                }
                if(passw.isEmpty()) {
                    pass.setError("Enter pass");
                }
                else {
                    mAuth.createUserWithEmailAndPassword(user, passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Registraion Fail" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        redir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));

            }
        });
    }
}