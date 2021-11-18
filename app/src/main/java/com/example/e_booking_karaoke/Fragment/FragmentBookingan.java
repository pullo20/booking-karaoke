package com.example.e_booking_karaoke.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.e_booking_karaoke.Adapter.BookingUserAdapter;
import com.example.e_booking_karaoke.Constants;
import com.example.e_booking_karaoke.Model.Booking;
import com.example.e_booking_karaoke.R;
import com.example.e_booking_karaoke.RequestHandler;
import com.example.e_booking_karaoke.SharedPrefManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentBookingan extends Fragment {
    private List<Booking> listItems;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ProgressDialog progressDialog;

    public FragmentBookingan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bokingan, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerbokinguser);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressDialog = new ProgressDialog(getActivity());
        listItems = new ArrayList<>();
//        ma = this;
        refresh_list();

    }

    private void refresh_list() {
        listItems.clear();
        progressDialog.setTitle("Memeriksa Data");
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_API_TRANSAKSI_CI+"?user_id="+ SharedPrefManager.getInstance(getActivity()).getUser().getIdUser(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try{

                    progressDialog.hide();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

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
//                        Toast.makeText(getActivity(),o.getString("harga"),Toast.LENGTH_SHORT).show();


                        listItems.add(item);

                        adapter = new BookingUserAdapter(listItems);
                        recyclerView.setAdapter(adapter);


                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"Data Masih Kosong",Toast.LENGTH_LONG).show();

                }
            }


        }, error -> {
            progressDialog.hide();
            Toast.makeText(getActivity(), error.toString(),Toast.LENGTH_SHORT).show();

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
}

