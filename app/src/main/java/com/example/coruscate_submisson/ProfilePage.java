package com.example.coruscate_submisson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coruscate_submisson.Model.UserModel;

public class ProfilePage extends AppCompatActivity {

    TextView name,city,email,phone,website,company;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        name=findViewById(R.id.tv_name);
        city=findViewById(R.id.tv_address);
        email=findViewById(R.id.tv_email);
        phone=findViewById(R.id.tv_phone);
        website=findViewById(R.id.tv_website);
        company=findViewById(R.id.tv_company);
        Intent i =getIntent();
      //  UserModel u= (UserModel) i.getSerializableExtra("data");
       // Toast.makeText(this, "Name"+i.getStringExtra("name"), Toast.LENGTH_SHORT).show();
        name.setText(i.getStringExtra("name"));
        city.setText(i.getStringExtra("city"));
        email.setText(i.getStringExtra("email"));
        phone.setText(i.getStringExtra("phone"));
        website.setText(i.getStringExtra("website"));
        company.setText(i.getStringExtra("company"));

    }
}
