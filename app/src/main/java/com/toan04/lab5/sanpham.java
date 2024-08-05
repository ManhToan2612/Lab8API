package com.toan04.lab5;

public class sanpham {
    private String MaMP, TenSP, MoTa;

    public sanpham() {
    }

    public sanpham(String maMP, String tenSP, String moTa) {
        MaMP = maMP;
        TenSP = tenSP;
        MoTa = moTa;
    }

    public String getMaMP() {
        return MaMP;
    }

    public void setMaMP(String maMP) {
        MaMP = maMP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }
}
