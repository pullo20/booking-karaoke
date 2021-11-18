package com.example.e_booking_karaoke.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.e_booking_karaoke.BankActivity;
import com.example.e_booking_karaoke.Constants;
import com.example.e_booking_karaoke.Model.Booking;
import com.example.e_booking_karaoke.Model.User;
import com.example.e_booking_karaoke.R;
import com.example.e_booking_karaoke.RequestHandler;
import com.example.e_booking_karaoke.SharedPrefManager;
import com.example.e_booking_karaoke.SplashScreen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class BookingFragment extends Fragment {
        TimePickerDialog timePickerDialog;
        private int harga = 0 , buka = 0;
    private Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    private OnFragmentInteractionListener mListener;
    private String Vwaktu;



    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false);
    }
    private void getDataTr()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_API_TRANSAKSI_CI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressDialog.dismiss();
                try{

//                    progressDialog.hide();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject o = null;


                    if (jsonObject.getBoolean("status")){
                        for (int i = 0; i<jsonArray.length(); i++) {
                            o = jsonArray.getJSONObject(i);
//                        Booking item = new Booking(
//                                o.getString("idtransaksi"),
//                                o.getString("tgl_transaksi"),
//                                o.getString("status"),
//                                o.getString("nama_pemesan"),
//                                o.getString("type_room"),
//                                o.getString("waktu_mulai"),
//                                o.getString("no_room"),
//                                o.getString("user_id"),
//                                o.getString("jam"),
//                                o.getString("nik"),
//                                o.getString("no_telp"),
//                                o.getString("harga"),
//                                o.getString("waktu_selesai")
//
//                        );
//                        listItems.add(item);
////
//                     adapter = new BookingAdapter(listItems,getApplicationContext());
////                        recyclerView.setAdapter(adapter);
                        }
//                                new Handler().postDelayed(() -> {
//
//                                    Intent mainIntent = new Intent(BankActivity.this,MainActivity.class);
////                    BackRun backRun = new BackRun();
////                    backRun.userLogin();
////                    backRun.getData();
//                                    startActivity(mainIntent);
//                                    finish();
//                                }, 1000);
//
//                    }
//                        Toast.makeText(getApplicationContext(),dt.getString("waktu_mulai"),Toast.LENGTH_SHORT).show();
//                                tt.setText(o.getString("waktu_mulai")+"    "+o.getString("tgl_transaksi"));
                        assert o != null;
//                                Wmulai = o.getString(String.valueOf(jsonObject.getBoolean("status")));
//                        TglBook = o.getString("tgl_transaksi");
                        Vwaktu  = o.getString("waktu_mulai");
//                        Toast.makeText(getActivity(),Vwaktu,Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(getApplicationContext(), SplashScreen.class));

//                                finish();
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"Tidak Terkoneksi",Toast.LENGTH_SHORT).show();

                    }
                }catch (JSONException e) {
                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),"ini"+e.toString(),Toast.LENGTH_LONG).show();
//                            tt.setText(e.toString());

                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                progressDialog.hide();
                Toast.makeText(getActivity(), error.toString(),Toast.LENGTH_SHORT).show();

            }
        })

        {
            protected Map<String , String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("name", "kl");
                return params;
            }
        };
        RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        getDataTr();

        EditText Bnama,Btlp,Bnik;
        Spinner spinnerR = view.findViewById(R.id.typeroom);
        Spinner spinner = view.findViewById(R.id.noroom);
        Spinner Bdurasi = view.findViewById(R.id.durasi);
        Bnama = view.findViewById(R.id.BRnama);
        Bnama.setText(Vwaktu);
//        Bnik = view.findViewById(R.id.BRnik);
        Btlp= view.findViewById(R.id.BRPonsel);
        TextView TWmulai,Tharga;
        Tharga = view.findViewById(R.id.Bharga);
        TWmulai = view.findViewById(R.id.Wmulai);

        Booking booking = SharedPrefManager.getInstance(getActivity()).getBook();
        Bnama.setText(booking.getWaktu_mulai());

        TWmulai.setOnClickListener(v -> {
            /**
             * Calendar untuk mendapatkan waktu saat ini
             */
            Calendar calendar = Calendar.getInstance();

            /**
             * Initialize TimePicker Dialog
             */
           TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), (view1, hourOfDay, minute) -> {
               /**
                * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                */
               TWmulai.setText(hourOfDay+":"+minute);
               buka = hourOfDay;
           },
                    /**
                     * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                     */
                    calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                    /**
                     * Cek apakah format waktu menggunakan 24-hour format
                     */
                    DateFormat.is24HourFormat(getActivity()));

            timePickerDialog.show();
        });

        spinnerR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
               String STroom = parent.getItemAtPosition(pos).toString();
               if (STroom.equals("Small")){
                     harga = 15000;
                   Tharga.setText(formatRupiah.format(harga));

                   ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()),
                    R.array.no_small, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
               }
               else if (STroom.equals("Medium")){
                   harga = 25000;

                   Tharga.setText(formatRupiah.format(harga));


                   ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()),
                   R.array.no_medium, android.R.layout.simple_spinner_item);
                   adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   spinner.setAdapter(adapter);
               }
               else{
                   harga = 45000;
                   Tharga.setText(formatRupiah.format(harga));

                   ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()),
                   R.array.no_large, android.R.layout.simple_spinner_item);
                   adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   spinner.setAdapter(adapter);
               }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Bdurasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String STdurasi = parent.getItemAtPosition(pos).toString();
                 if (STdurasi.equals("2"))
                {
                    harga = harga * 2;
                    Tharga.setText(formatRupiah.format(harga));


                }
                else if (STdurasi.equals("3"))
                {
                    harga = harga * 3;
                    Tharga.setText(formatRupiah.format(harga));


                }
                else if (STdurasi.equals("4"))
                {
                    harga = harga * 4;
                    Tharga.setText(formatRupiah.format(harga));


                }
                else if (STdurasi.equals("5"))
                {
                    harga = harga * 5;
                    Tharga.setText(formatRupiah.format(harga));


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


                view.findViewById(R.id.btn_booking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Snama, Snik, SWmulai, Styperoom, Snoroom, Sdurasi, Stlp, Sharga;
                boolean validd = false;
                ProgressDialog pd = new ProgressDialog(getActivity());
                User user = SharedPrefManager.getInstance(getActivity()).getUser();

                Snama = Bnama.getText().toString();
//                Snik = Bnik.getText().toString();
                Stlp = Btlp.getText().toString();
                SWmulai = TWmulai.getText().toString();
                Sdurasi = Bdurasi.getSelectedItem().toString();
                Styperoom = spinnerR.getSelectedItem().toString();
                Snoroom = spinner.getSelectedItem().toString();
                Sharga = String.valueOf(harga);
//                buka = Integer.parseInt(SWmulai);
//                if (Sdurasi.equals("1"))
//                {
//                    harga = harga*1;
//                }
//                else if (Sdurasi.equals("2"))
//                {
//                    harga = harga * 2;
//                }
//                else if (Sdurasi.equals("3"))
//                {
//                    harga = harga * 3;
//                }
//                else if (Sdurasi.equals("4"))
//                {
//                    harga = harga * 4;
//                }
//                else if (Sdurasi.equals("5"))
//                {
//                    harga = harga * 5;
//                }


                if (TextUtils.isEmpty(Snama)) {
                    Bnama.setError("Username Cannot be Empty");
                    validd = false;
                } else {
                    validd = true;
                        if (TextUtils.isEmpty(Stlp)) {
                            Btlp.setError("Email Cannot be Empty");
                            validd = false;
                        } else {
                            validd = true;

                            if (TextUtils.isEmpty(SWmulai)) {
                                TWmulai.setError("Password Cannot be Empty");
                                validd = false;
                            } else {
                                validd = true;
                            }
                        }


                }
//                AtomicReference<String> Vwaktu = new AtomicReference<>("ueueueu");
                if (buka >= 10 || buka < 2) {
                    if (validd && !(SWmulai.equals(Vwaktu))) {
                        pd.setTitle("Menambah Data");
                        pd.setMessage("Loading...");
                        pd.show();

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_API_TRANSAKSI_CI, response -> {
                            pd.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getActivity(), "klik OK Untuk Mendaftarkan Pesanan Anda..!", Toast.LENGTH_SHORT).show();
                                if (jsonObject.getBoolean("status")) {
//                                Vwaktu.set(SWmulai);
                                    startActivity(new Intent(getActivity(), BankActivity.class));
//                                    ListActivity.ma.refresh_list();
                                    getActivity().finish();
                                } else {
                                    Toast.makeText(getActivity(), jsonObject.getString("Jaringan Anda Bermasalah"), Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }, error -> {
                            pd.hide();
                            Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                        }) {
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("nama_pemesan", Snama);
                                params.put("type_room", Styperoom);
                                params.put("no_room", Snoroom);
                                params.put("waktu_mulai", SWmulai);
                                params.put("jam", Sdurasi);
//                                params.put("nik", Snik);
                                params.put("no_telp", Stlp);
                                params.put("user_id", user.getIdUser());
                                params.put("harga", Sharga);


                                return params;
                            }
                        };
                        RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);
                    } else {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Ini Sudah Di Booking")
                                .setMessage("Coba Pilih Waktu Yang Lain")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }

                }
                else
                {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Toko Baru Buka Jam 10:00 - 02:00")
                            .setMessage("Pilihlah Waktu Yang Benar!")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }
        });



    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
