package com.example.ft.aidt; import android.content.Intent; import android.support.annotation.NonNull; import android.support.v7.app.AppCompatActivity; import android.os.Bundle; import android.text.TextUtils; import android.view.View; import android.widget.Button; import android.widget.EditText; import android.widget.ImageView; import android.widget.ProgressBar; import android.widget.TextView; import android.widget.Toast; import com.google.android.gms.tasks.OnCompleteListener; import com.google.android.gms.tasks.Task; import com.google.firebase.auth.AuthResult; import com.google.firebase.auth.FirebaseAuth; public class SignUpActivity extends AppCompatActivity {
40
private Button up; private TextView loginb; private EditText em,pas; private FirebaseAuth auth; private ImageView jk; @Override protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_sign_up); loginb = (TextView) findViewById(R.id.textView5); auth = FirebaseAuth.getInstance(); loginb.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { back(); } }); em=(EditText) findViewById(R.id.editText3); pas=(EditText) findViewById(R.id.editText5); up=(Button) findViewById(R.id.button3); jk = (ImageView) findViewById(R.id.imageView7); jk.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { Toast.makeText(getApplicationContext(),"Welcome! Here you can register your account to start using the
41
application.",Toast.LENGTH_LONG).show(); } }); up.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { String email =em.getText().toString().trim(); String password = pas.getText().toString().trim(); if(TextUtils.isEmpty(password)) { Toast.makeText(getApplicationContext(),"You cannot leave this blank.",Toast.LENGTH_LONG).show(); } if(TextUtils.isEmpty(email)) { Toast.makeText(getApplicationContext(),"You cannot leave this blank.",Toast.LENGTH_LONG).show(); } auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() { @Override public void onComplete(@NonNull Task<AuthResult> task) { Toast.makeText(SignUpActivity.this,"Create user complete"+task.isSuccessful(),Toast.LENGTH_SHORT).show();
42
if(!task.isSuccessful()) { Toast.makeText(SignUpActivity.this,"Sign up failed.",Toast.LENGTH_SHORT).show(); } else { startActivity(new Intent(SignUpActivity.this,Actual.class)); finish(); } } }); } }); } public void back() { Intent intent = new Intent(SignUpActivity.this,LoginActivity.class); startActivity(intent); } }