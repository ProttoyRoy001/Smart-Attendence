package com.example.smartattendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.smartattendence.R.id.loginbuttonId;
import static com.example.smartattendence.R.id.passwordId;
import static com.example.smartattendence.R.id.textViewId;
import static com.example.smartattendence.R.id.usernameId;

public class Login extends AppCompatActivity {

    private EditText usernameEditText,passwordEditText;
    private Button loginButton;
    private TextView textView;
    private int counter =3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText =(EditText) findViewById(R.id.usernameId);
        passwordEditText =(EditText) findViewById(R.id.passwordId);
        loginButton= (Button)findViewById(R.id.loginbuttonId);
        textView = (TextView) findViewById(R.id.textViewId);
        textView.setText("Number of attempts remaining : 3");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(username.equals("admin")&& password.equals("admin"))
                {

                    Intent intent = new Intent(Login.this,Start.class);
                    startActivity(intent);

                }
                else
                    {
                    counter--;
                    textView.setText("Number of attempts remaining :"+counter);
                    if(counter==0)
                    {
                        loginButton.setEnabled(false);
                    }
                }

            }
        });
    }
}
