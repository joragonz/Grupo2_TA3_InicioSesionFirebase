package com.example.grupo2_ta3_iniciosesionfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class PerfilUsuario extends AppCompatActivity {
    TextView txt_id, txt_name, txt_email;
    ImageView imv_photo;
    DatabaseReference db_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        Intent intent = getIntent();
        HashMap<String, String> info_user = (HashMap<String, String>) intent.getSerializableExtra("info_user");

        txt_id = findViewById(R.id.txt_userId);
        txt_name = findViewById(R.id.txt_nombre);
        txt_email = findViewById(R.id.txt_correo);
        imv_photo = findViewById(R.id.imv_foto);

        txt_id.setText(info_user.get("user_id"));
        txt_name.setText(info_user.get("user_name"));
        txt_email.setText(info_user.get("user_email"));
        String photo = info_user.get("user_photo");
        Picasso.with(getApplicationContext()).load(photo).into(imv_photo);

        iniciarBaseDeDatos();

    }



    public void iniciarBaseDeDatos(){
        db_reference = FirebaseDatabase.getInstance().getReference().child("Grupos");
        }



    public void cerrarSesion(View view){
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("msg", "cerrarSesion");
        startActivity(intent);
    }
    public void irRegistros(View view){
        Intent intent = new Intent(this, registro.class);
        startActivity(intent);
    }
}
