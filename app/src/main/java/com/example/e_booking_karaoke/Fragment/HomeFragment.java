package com.example.e_booking_karaoke.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.e_booking_karaoke.AdminActivity;
import com.example.e_booking_karaoke.BottomNavigationActivity;
import com.example.e_booking_karaoke.Constants;
import com.example.e_booking_karaoke.MainActivity;
import com.example.e_booking_karaoke.Model.User;
import com.example.e_booking_karaoke.R;
import com.example.e_booking_karaoke.RequestHandler;
import com.example.e_booking_karaoke.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);


    }
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_API_LOGIN_CI, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
////                progressDialog.dismiss();
//                try {
////
//                    JSONObject jsonObject = new JSONObject(response);
////                    Boolean status = jsonObject.getBoolean("status");
//
//
//                    JSONObject dt = jsonObject.getJSONObject("data");
//                    User user = new User(
//                            dt.getString("iduser"),
//                            dt.getString("username"),
//                            dt.getString("email"),
//                            dt.getString("noHp"),
//                            dt.getString("password"),
//                            dt.getString("status"));
//
//
//                    SharedPrefManager.getInstance(getActivity()).userLogin(user);
//
////                            startActivity(new Intent(getActivity(), BottomNavigationActivity.class));
////                                       ListActivity.ma.refresh_list();
////                            finish();
//
////                                String ss = getIntent().getStringExtra("status");
////                                User.setText(getIntent().getStringExtra("username"));
////                                Toast.makeText(MainActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
//                }
//            }
//        } , error -> {
////            progressDialog.hide();
//            Toast.makeText(getActivity(), error.toString(),Toast.LENGTH_SHORT).show();
//        }) {
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("name", "user");
////                params.put("password", pass);
//
//                return params;
//            }
//        };
//        RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
//
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
