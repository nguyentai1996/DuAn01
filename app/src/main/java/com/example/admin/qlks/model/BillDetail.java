package com.example.admin.qlks.model;

public class BillDetail {
    private int maHDCT;
    private Bill bill;
    private Room room;
    private int soLuongMua;

    public BillDetail() {

    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public BillDetail(int maHDCT, Bill bill, Room room, int soLuongMua) {

        this.maHDCT = maHDCT;
        this.bill = bill;
        this.room = room;
        this.soLuongMua = soLuongMua;
    }
}
