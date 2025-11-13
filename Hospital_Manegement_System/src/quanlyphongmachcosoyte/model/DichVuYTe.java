package quanlyphongmachcosoyte.model;

abstract public class DichVuYTe {
    private String maDV;
    private String tenDV;
    private double giaTien;
    private char xepLoai;

    // Getter & Setter
    public String layMaDV() {
        return maDV;
    };

    public void thietLapMaDV(String maDV) {
        this.maDV = maDV;
    };

    public String layTenDV() {
        return tenDV;
    };

    public void thietLapTenDV(String tenDV) {
        this.tenDV = tenDV;
    };

    public double layGiaTien() {
        return giaTien;
    };

    public void thietLapGiaTien(double giaMoi) {
        if (giaMoi > 0) {
            this.giaTien = giaMoi;
        }
    }

    public char layXepLoai() {
        return xepLoai;
    };

    public void thietLapXepLoai(char xeploai) {
        this.xepLoai = xepLoai;
    };

    // Constructor
    public DichVuYTe(String maDV, String tenDV, double giaTien, char xeploai) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giaTien = giaTien;
        this.xepLoai = xepLoai;
    }

    abstract public void hienThi();

    abstract public double tinhChiPhi();

    abstract public String chuyenThanhChuoiLuuTapTin();

}