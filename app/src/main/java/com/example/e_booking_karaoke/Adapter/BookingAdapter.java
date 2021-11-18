package com.example.e_booking_karaoke.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.e_booking_karaoke.AdminActivity;
import com.example.e_booking_karaoke.BottomNavigationActivity;
import com.example.e_booking_karaoke.Constants;
import com.example.e_booking_karaoke.Fragment.FragmentBookingan;
import com.example.e_booking_karaoke.Model.Booking;
import com.example.e_booking_karaoke.R;
import com.example.e_booking_karaoke.RequestHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder>{

    private List<Booking> listItems;
    private Context context;
    private ProgressDialog dialog;



    public BookingAdapter(List<Booking> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nama,tiperoom,noroom,durasi,waktu,tlp,tglbooking,stts;
        CardView card_view;

        public ViewHolder(View itemView ) {
            super(itemView);
            nama = itemView.findViewById(R.id.cardNama);
            tiperoom = itemView.findViewById(R.id.cardTypeRoom);
            noroom = itemView.findViewById(R.id.cardNoRoom);
            durasi = itemView.findViewById(R.id.cardDurasi);
            waktu = itemView.findViewById(R.id.cardWaktu);
            tlp = itemView.findViewById(R.id.cardNotlp);
            tglbooking = itemView.findViewById(R.id.cardTgl);
            card_view = itemView.findViewById(R.id.cardBooking);
            stts =  itemView.findViewById(R.id.cardstatus);
        }


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_admin, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Booking listItem = listItems.get(position);
        holder.nama.setText(listItem.getNama_pemesan());
        holder.tiperoom.setText("Type Room : "+listItem.getType_room());
        holder.noroom.setText("No.Room : "+listItem.getNo_room());
        holder.tlp.setText("Phone : "+listItem.getNo_telp());
        holder.durasi.setText("Durasi : "+listItem.getJam()+"jam");
        holder.waktu.setText("Waktu : "+listItem.getWaktu_mulai()+" - "+listItem.getWaktu_selesai());
        holder.stts.setText("Status : "+listItem.getStatus());
        holder.tglbooking.setText(listItem.getTgl_transaksi());

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                final ProgressDialog dialog = new ProgressDialog(view.getContext());
//                dialog.setMessage("Loading Delete Data");
                final CharSequence[] dialogitem = {"Check IN","Check OUT"};
                builder.setTitle(listItem.getNama_pemesan());
                builder.setItems(dialogitem, (dialogInterface, i) -> {
                    switch (i){
                        case 0 :
//                       Toast.makeText(getApplicationContext(),data.getIdtransaksi(),Toast.LENGTH_LONG).show();
                                new AlertDialog.Builder(view.getContext())
                                        .setMessage("Klik Lanjut Untuk Mengkonfirmasi")
                                        .setCancelable(false)
                                        .setPositiveButton("Lanjut", (dialog1, which1) -> {
//
                                            StringRequest stringRequest = new StringRequest(Request.Method.PUT, Constants.URL_API_TRANSAKSI_CI+"/"+listItem.getIdtransaksi(), new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
//                                                dialog.hide();
//                                                dialog.dismiss();
                                                    Toast.makeText(view.getContext(),"Pesanan Atas Nama "+ listItem.getNama_pemesan() +" Check In", Toast.LENGTH_LONG).show();
//                                                                loadFragment(new FragmentBookingan());
                                                    AdminActivity.ma.refresh_list();

                                                }
                                            }, error -> {
//                                                dialog.hide();
//                                                dialog.dismiss();
                                            }){
                                                protected HashMap<String, String> getParams() throws AuthFailureError {
                                                    Map<String, String> params= new HashMap<>();
                                                    params.put("status","in");
                                                    return (HashMap<String, String>) params;

                                                }
                                            };
                                            RequestHandler.getInstance(view.getContext()).addToRequestQueue(stringRequest);
//                                        dialogInterface.dismiss();
                                        }).setNegativeButton("Batal", (dialog12, which) -> dialog12.dismiss()).show();
                            break;
                            case 1 :
                                new AlertDialog.Builder(view.getContext())
                                        .setMessage("Klik Lanjut Untuk Mengkonfirmasi")
                                        .setCancelable(false)
                                        .setPositiveButton("Lanjut", (dialog1, which1) -> {
//
                                            StringRequest stringRequest = new StringRequest(Request.Method.PUT, Constants.URL_API_TRANSAKSI_CI+"/"+listItem.getIdtransaksi(), new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
//                                                dialog.hide();
//                                                dialog.dismiss();
                                                    Toast.makeText(view.getContext(),"Pesanan Atas Nama "+ listItem.getNama_pemesan() +" Check Out", Toast.LENGTH_LONG).show();
//                                                                loadFragment(new FragmentBookingan());
                                                    AdminActivity.ma.refresh_list();

                                                }
                                            }, error -> {
//                                                dialog.hide();
//                                                dialog.dismiss();
                                            }){
                                                protected HashMap<String, String> getParams() throws AuthFailureError {
                                                    Map<String, String> params= new HashMap<>();
                                                    params.put("status","out");
                                                    return (HashMap<String, String>) params;

                                                }
                                            };
                                            RequestHandler.getInstance(view.getContext()).addToRequestQueue(stringRequest);
//                                        dialogInterface.dismiss();
                                        }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog12, int which) {
                                        dialog12.dismiss();
                                    }
                                }).show();
                                break;
                    }
                });

                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}