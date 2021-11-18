package com.example.e_booking_karaoke.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.e_booking_karaoke.Holderisasi.HolderUserBoking;
import com.example.e_booking_karaoke.Model.Booking;
import com.example.e_booking_karaoke.PackInterface.InterfaceUserBoking;
import com.example.e_booking_karaoke.R;

import java.util.List;

public class BookingUserAdapter extends RecyclerView.Adapter<HolderUserBoking> {
    private List<Booking> listItems;

    private InterfaceUserBoking it;
    public BookingUserAdapter(List<Booking> listItems) {
        this.listItems = listItems;
    }
    @NonNull
    @Override
    public HolderUserBoking onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_bokingan, parent, false);
        return new HolderUserBoking(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderUserBoking holder, int position) {
        final Booking listItem = listItems.get(position);
        holder.setNama(listItem.getNama_pemesan());
        holder.setTiperoom("Type Room : " + listItem.getType_room());
        holder.setNoroom("No.Room : " + listItem.getNo_room());
        holder.setTlp("Phone : " + listItem.getNo_telp());
        holder.setDurasi("Durasi : " + listItem.getJam() + "jam");
        holder.setStts("Status :"+ listItem.getStatus());
        holder.setWaktu("Waktu : " + listItem.getWaktu_mulai() + " - " + listItem.getWaktu_selesai());
        holder.setTglbooking(listItem.getTgl_transaksi());
        holder.setIdtransaksi(listItem.getIdtransaksi());
        holder.setNik(listItem.getNik());
        holder.setHarga(listItem.getHarga());
        holder.setUser_id(listItem.getUser_id());

        holder.itemView.setOnClickListener(v -> {
            Booking bk = new Booking(listItem.getIdtransaksi(),listItem.getTgl_transaksi(),listItem.getStatus(),listItem.getNama_pemesan(),listItem.getType_room(),listItem.getWaktu_mulai(),listItem.getNo_room(),listItem.getUser_id(),listItem.getJam(),listItem.getNik(),listItem.getNo_telp(),listItem.getHarga(),listItem.getWaktu_selesai());
            it = (InterfaceUserBoking) v.getContext();
            it.aksiUserBoking(bk);
//            holder.itemView.setVisibility(View.INVISIBLE);

        });
    }
    @Override
    public int getItemCount() {
        return listItems.size();
    }
}
