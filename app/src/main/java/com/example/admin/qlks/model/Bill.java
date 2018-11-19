package com.example.admin.qlks.model;

import java.util.Date;

public class Bill {
    private String maHoaDon;
    private Date ngayMua;

    public Bill() {

    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public Bill(String maHoaDon, Date ngayMua) {

        this.maHoaDon = maHoaDon;
        this.ngayMua = ngayMua;
    }
}
