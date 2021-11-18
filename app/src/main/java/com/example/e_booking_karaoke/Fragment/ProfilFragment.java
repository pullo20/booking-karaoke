package com.example.e_booking_karaoke.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_booking_karaoke.EditActivity;
import com.example.e_booking_karaoke.MainActivity;
import com.example.e_booking_karaoke.Model.User;
import com.example.e_booking_karaoke.R;
import com.example.e_booking_karaoke.SharedPrefManager;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ProfilFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private SwipeRefreshLayout sw ;

    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        User user = SharedPrefManager.getInstance(getActivity()).getUser();
        TextView TPnama,Tnama,Temail,Tphone;
        TPnama =  view.findViewById(R.id.profil_name);
        Tnama =  view.findViewById(R.id.Pnama);
        Temail =  view.findViewById(R.id.Pemail);
        Tphone =  view.findViewById(R.id.Pphone);
//        sw = view.findViewById(R.id.swiperefresh);
//        onRefresh();
        TPnama.setText(user.getName());
        Tnama.setText(user.getName());
        Temail.setText(user.getEmail());
        Tphone.setText(user.getNoHp());

        view.findViewById(R.id.btn_loguot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
             getActivity().finish();
                SharedPrefManager.getInstance(getActivity()).logout();
//                Toast.makeText(getActivity(),user.getEmail(),Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), EditActivity.class));

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
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sw.setRefreshing(false);
            }
        }, 5000);
    }
}
