package com.example.e_booking_karaoke.Holderisasi;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_booking_karaoke.R;

public class HolderUserBoking extends RecyclerView.ViewHolder {

    private TextView nama,tiperoom,noroom,durasi,waktu,tlp,tglbooking,stts;
    private String idtransaksi, user_id, nik, harga;
    public HolderUserBoking(@NonNull View itemView) {
        super(itemView);
        nama =  itemView.findViewById(R.id.cardNamaBokingan);
        tiperoom= itemView.findViewById(R.id.cardTypeRoomBokingan);
        noroom = itemView.findViewById(R.id.cardNoRoomBokingan);
        durasi =  itemView.findViewById(R.id.cardDurasiBokingan);
        waktu = itemView.findViewById(R.id.cardWaktuBokingan);
        tlp = itemView.findViewById(R.id.cardNotlpBokingan);
        tglbooking =  itemView.findViewById(R.id.cardTglBokingan);
        stts = itemView.findViewById(R.id.cardStatusBokingan);
    }

    public void setIdtransaksi(String idtransaksi) {
        this.idtransaksi = idtransaksi;
    }

    public void setStts(String status) {
        this.stts.setText(status);
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public void setDurasi(String durasi) {
        this.durasi.setText(durasi);
    }

    public void setNoroom(String noroom) {
        this.noroom.setText(noroom);
    }

    public void setNama(String nama) {
        this.nama.setText(nama);
    }

    public void setTglbooking(String tglbooking) {
        this.tglbooking.setText(tglbooking);
    }

    public void setTiperoom(String tiperoom) {
        this.tiperoom.setText(tiperoom);
    }


    public void setTlp(String tlp) {
        this.tlp.setText(tlp);
    }

    public void setWaktu(String waktu) {
        this.waktu.setText(waktu);
    }
}
