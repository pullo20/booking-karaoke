package com.example.e_booking_karaoke;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DaftarActivity extends AppCompatActivity {
EditText name,nohp,email,pass;
String Snama,Snohp,Semail,Spass,stts;
Boolean validd;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        name = findViewById(R.id.nama_depan);
        nohp = findViewById(R.id.noPonsel);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        progressDialog = new ProgressDialog(this);


        findViewById(R.id.btn_singup).setOnClickListener(v -> {
            Snama = name.getText().toString();
            Snohp = nohp.getText().toString();
            Semail = email.getText().toString();
            Spass = pass.getText().toString();

            if(TextUtils.isEmpty(Snama)){
                name.setError("Username Cannot be Empty");
                validd = false;
            }else {
                validd = true;

                if(TextUtils.isEmpty(Snohp)){
                    nohp.setError("No. Telpon Cannot be Empty");
                    validd = false;
                }else {
                    validd = true;

                    if(TextUtils.isEmpty(Semail)){
                        email.setError("Email Cannot be Empty");
                        validd = false;
                    }else {
                        validd = true;

                        if(TextUtils.isEmpty(Spass)){
                            pass.setError("Password Cannot be Empty");
                            validd = false;
                        }else {
                            validd = true;
                        }
                    }

                }
            }
            if(validd) {
                progressDialog.setTitle("Menambah Data");
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_API_REGISTER_CI, response -> {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(DaftarActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        if (jsonObject.getString("message").equals("berhasil ditambahkan")) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                                    ListActivity.ma.refresh_list();
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }, error -> { 
                    progressDialog.hide();
                    Toast.makeText(DaftarActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }) {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", name.getText().toString());
                        params.put("email", email.getText().toString());
                        params.put("noHp", nohp.getText().toString());
                        params.put("password", pass.getText().toString());
                        params.put("status", "user");
                        return params;
                    }
                };
                RequestHandler.getInstance(DaftarActivity.this).addToRequestQueue(stringRequest);
            }

        });
    }
}
