package com.example.e_booking_karaoke;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.e_booking_karaoke.Fragment.BookingFragment;
import com.example.e_booking_karaoke.Fragment.FragmentBookingan;
import com.example.e_booking_karaoke.Fragment.HomeFragment;
import com.example.e_booking_karaoke.Fragment.ProfilFragment;
import com.example.e_booking_karaoke.Model.Booking;
import com.example.e_booking_karaoke.PackInterface.InterfaceUserBoking;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class BottomNavigationActivity extends AppCompatActivity implements InterfaceUserBoking {

    Fragment fragment = null;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        loadFragment(new HomeFragment());
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);


    }
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_booking:
                fragment = new BookingFragment();
                break;
            case R.id.navigation_profil:
                fragment = new ProfilFragment();
                break;
            case R.id.navigation_bokingan:
                fragment = new FragmentBookingan();
                break;

        }

        return loadFragment(fragment);
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void aksiUserBoking(Booking data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BottomNavigationActivity.this);
        final CharSequence[] dialogitem = {"Batalkan Pesanan"};
        builder.setTitle("");
        builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                       Toast.makeText(getApplicationContext(),data.getIdtransaksi(),Toast.LENGTH_LONG).show();
                        new AlertDialog.Builder(BottomNavigationActivity.this)
                                .setMessage("Yakin Ingin Membatalkan Pesanan..!")
                                .setCancelable(false)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
//
                                        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, Constants.URL_API_TRANSAKSI_CI+"/"+data.getIdtransaksi(), new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
//                                                dialog.hide();
//                                                dialog.dismiss();
                                                Toast.makeText(BottomNavigationActivity.this,"Pesanan Atas Nama "+ data.getNama_pemesan() +" Dibatalkan.!", Toast.LENGTH_LONG).show();
                                                loadFragment(new FragmentBookingan());
//                                                ListActivity.data.refresh_list();

                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
//                                                dialog.hide();
//                                                dialog.dismiss();
                                            }
                                        }){
                                            protected HashMap<String, String> getParams() throws AuthFailureError {
                                                Map<String, String> params= new HashMap<>();
                                                params.put("idtransaksi",data.getIdtransaksi());
                                                return (HashMap<String, String>) params;

                                            }
                                        };
                                        RequestHandler.getInstance(BottomNavigationActivity.this).addToRequestQueue(stringRequest);
//                                        dialogInterface.dismiss();
                                    }
                                }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).show();

                    }
                }).show();
//        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//        final ProgressDialog dialog = new ProgressDialog(getApplicationContext());
//        dialog.setMessage("Loading Delete Data");
//        final CharSequence[] dialogitem = {"Batalkan Pesanan"};
//        builder.setTitle(data.getNama_pemesan());
//        builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
////                switch (i){
////                    case 0 :
////
////                               break;
////                }
//            }
//        });
//
//        builder.create().show();
    }
//        Toast.makeText(getApplicationContext(),data.getNama_pemesan(),Toast.LENGTH_LONG).show();
}

