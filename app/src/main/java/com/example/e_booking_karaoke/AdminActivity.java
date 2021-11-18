package com.example.e_booking_karaoke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.e_booking_karaoke.Adapter.BookingAdapter;
import com.example.e_booking_karaoke.Model.Booking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {
    public static AdminActivity ma;
    protected Cursor cursor;
    ArrayList<Booking> thelist;
    ListView listview;
    List<Booking> listItems;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

//        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
//        getSupportActionBar().setTitle("My new title"); // set the top title
//        String title = actionBar.getTitle().toString(); // get the title
//        actionBar.hide();
//        getSupportActionBar().setTitle("data");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerAdmin);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminActivity.this));
        progressDialog = new ProgressDialog(this);
        listItems = new ArrayList<>();
        ma = this;
        refresh_list();
    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.add, menu);
//        return true;
//    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {

            Intent tes = new Intent(AdminActivity.this, BottomNavigationActivity.class);
            startActivity(tes);
        }
        return super.onOptionsItemSelected(item);
    }
    public void refresh_list() {
        listItems.clear();
        adapter = new BookingAdapter(listItems,getApplicationContext());
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        progressDialog.setTitle("Memeriksa Data");
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_API_TRANSAKSI_CI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try{

                    progressDialog.hide();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    Toast.makeText(AdminActivity.this,jsonObject.getString("status"),Toast.LENGTH_SHORT).show();
                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject o = jsonArray.getJSONObject(i);
                        Booking item = new Booking(
                                o.getString("idtransaksi"),
                                o.getString("tgl_transaksi"),
                                o.getString("status"),
                                o.getString("nama_pemesan"),
                                o.getString("type_room"),
                                o.getString("waktu_mulai"),
                                o.getString("no_room"),
                                o.getString("user_id"),
                                o.getString("jam"),
                                o.getString("nik"),
                                o.getString("no_telp"),
                                o.getString("harga"),
                                o.getString("waktu_selesai")

                                );
                        listItems.add(item);

                        adapter = new BookingAdapter(listItems,getApplicationContext());
                        recyclerView.setAdapter(adapter);


                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"ini"+e.toString(),Toast.LENGTH_LONG).show();

                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(AdminActivity.this, error.toString(),Toast.LENGTH_SHORT).show();

            }
        })

        {
            protected Map<String , String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("name", "kl");
                return params;
            }
        };
        RequestHandler.getInstance(AdminActivity.this).addToRequestQueue(stringRequest);
//        stringRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return 50000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return 50000;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//
//            }
//        });


    }
}
