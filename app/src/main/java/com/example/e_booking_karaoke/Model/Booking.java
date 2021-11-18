package com.example.e_booking_karaoke.Model;

public class Booking {

    private String idtransaksi, tgl_transaksi, status, nama_pemesan, type_room, waktu_mulai, no_room, user_id, jam, nik, no_telp, harga, waktu_selesai;


    public String getIdtransaksi() {
        return idtransaksi;
    }

    public String getTgl_transaksi() {
        return tgl_transaksi;
    }

    public String getStatus() {
        return status;
    }

    public String getNama_pemesan() {
        return nama_pemesan;
    }

    public String getType_room() {
        return type_room;
    }

    public String getWaktu_mulai() {
        return waktu_mulai;
    }

    public String getNo_room() {
        return no_room;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getJam() {
        return jam;
    }

    public String getNik() {
        return nik;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public String getHarga() {
        return harga;
    }

    public String getWaktu_selesai() {
        return waktu_selesai;
    }

    public Booking(String idtransaksi, String tgl_transaksi, String status, String nama_pemesan, String type_room, String waktu_mulai, String no_room, String user_id, String jam, String nik, String no_telp, String harga, String waktu_selesai)
    {
        this.idtransaksi = idtransaksi;
        this.tgl_transaksi = tgl_transaksi;
        this.status = status;
        this.nama_pemesan = nama_pemesan;
        this.type_room = type_room;
        this.waktu_mulai = waktu_mulai;
        this.no_room = no_room;
        this.user_id = user_id;
        this.jam = jam;
        this.nik = nik;
        this.no_telp = no_telp;
        this.harga = harga;
        this.waktu_selesai = waktu_selesai;
    }

}
