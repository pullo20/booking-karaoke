package com.example.e_booking_karaoke;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.e_booking_karaoke.Model.Booking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashScreen extends AppCompatActivity {
//    List<Booking> listItems;
//    private RecyclerView.Adapter adapter;
//    public static String Wmulai,TglBook;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        TextView tt = findViewById(R.id.sp);


//        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_API_TRANSAKSI_CI, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
////                progressDialog.dismiss();
//                try{
//
////                    progressDialog.hide();
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//                    JSONObject o = null;
//
//
//                    if (jsonObject.getBoolean("status")){
//                    for (int i = 0; i<jsonArray.length(); i++) {
//                             o = jsonArray.getJSONObject(i);
////                        Booking item = new Booking(
////                                o.getString("idtransaksi"),
////                                o.getString("tgl_transaksi"),
////                                o.getString("status"),
////                                o.getString("nama_pemesan"),
////                                o.getString("type_room"),
////                                o.getString("waktu_mulai"),
////                                o.getString("no_room"),
////                                o.getString("user_id"),
////                                o.getString("jam"),
////                                o.getString("nik"),
////                                o.getString("no_telp"),
////                                o.getString("harga"),
////                                o.getString("waktu_selesai")
////
////                        );
////                        listItems.add(item);
//////
////                     adapter = new BookingAdapter(listItems,getApplicationContext());
//////                        recyclerView.setAdapter(adapter);
//                    }
                        new Handler().postDelayed(() -> {

                            Intent mainIntent = new Intent(SplashScreen.this,MainActivity.class);
//                    BackRun backRun = new BackRun();
//                    backRun.userLogin();
//                    backRun.getData();
                            startActivity(mainIntent);
                            finish();
                        }, 1000);
////
////                    }
////                        Toast.makeText(getApplicationContext(),dt.getString("waktu_mulai"),Toast.LENGTH_SHORT).show();
//                            tt.setText(o.getString("waktu_mulai")+"    "+o.getString("tgl_transaksi"));
//                            Wmulai = o.getString("waktu_mulai");
//                            TglBook = o.getString("tgl_transaksi");
//                    }
//                    else
//                    {
//                        Toast.makeText(getApplicationContext(),"Tidak Terkoneksi",Toast.LENGTH_SHORT).show();
//
//                    }
//                }catch (JSONException e) {
//                    e.printStackTrace();
////                    Toast.makeText(getApplicationContext(),"ini"+e.toString(),Toast.LENGTH_LONG).show();
//                    tt.setText(e.toString());
//
//                }
//            }
//
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                progressDialog.hide();
//                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
//
//            }
//        })
//
//        {
//            protected Map<String , String> getParams() throws AuthFailureError {
//                Map<String , String> params = new HashMap<>();
//                params.put("name", "kl");
//                return params;
//            }
//        };
//        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}
