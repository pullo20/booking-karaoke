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
import com.example.e_booking_karaoke.Model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText User,Pass;
    Boolean valid = false;
    String user,pass;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SharedPrefManager.getInstance(this).isLoggedIn())
        {
            User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
            if (user.getStatus().equals("user")){

                startActivity(new Intent(getApplicationContext(),BottomNavigationActivity.class));
            }
            else
            {
                startActivity(new Intent(getApplicationContext(),AdminActivity.class));

            }
        }

        setContentView(R.layout.activity_main);

        User = findViewById(R.id.user);
        Pass = findViewById(R.id.pass);
        progressDialog = new ProgressDialog(this);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = User.getText().toString();
                pass = Pass.getText().toString();

                if(TextUtils.isEmpty(user)){
                    User.setError("Username Cannot be Empty");
                    valid = false;
                }else {
                    valid = true;

                if(TextUtils.isEmpty(pass)){
                    Pass.setError("Password Cannot be Empty");
                    valid = false;
                }else {
                    valid = true;


                    }
                }

                if (valid)
                {
                    progressDialog.setTitle("Memeriksa Data");
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_API_LOGIN_CI, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try{

                                JSONObject jsonObject = new JSONObject(response);
                                Boolean status = jsonObject.getBoolean("status");

                                if(status){
                                    JSONObject dt = jsonObject.getJSONObject("data");
                                    if(dt.getString("status").equals("user")){
                                        User user = new User(
                                                dt.getString("iduser"),
                                                dt.getString("username"),
                                                dt.getString("email"),
                                                dt.getString("noHp"),
                                                dt.getString("password"),
                                                dt.getString("status")
0

                                        );
                                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                        startActivity(new Intent(getApplicationContext(),BottomNavigationActivity.class));
//                                       ListActivity.ma.refresh_list();
                                        finish();
                                    }
                                    else
                                    {
                                        startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                                        finish();
                                    }
                                    finish();

                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                                }

//                                String ss = getIntent().getStringExtra("status");
//                                User.setText(getIntent().getStringExtra("username"));
//                                Toast.makeText(MainActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                            }catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    , error -> {
                        progressDialog.hide();
                        Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_SHORT).show();
                    }){
                        protected Map<String , String> getParams() throws AuthFailureError {
                            Map<String , String> params = new HashMap<>();
                            params.put("username", user);
                            params.put("password", pass);

                            return params;
                        }
                    };
                    RequestHandler.getInstance(MainActivity.this).addToRequestQueue(stringRequest);

                }

            }
           });


        findViewById(R.id.btn_daftar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DaftarActivity.class));
            }
        });
    }
}
