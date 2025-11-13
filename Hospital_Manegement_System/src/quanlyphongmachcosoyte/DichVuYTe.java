package quanlyphongmachcosoyte;

abstract public class DichVuYTe {
    protected String maDV;
    protected String tenDV;
    protected double giaTien;
    protected char xeploai;

    public DichVuYTe(String maDV, String tenDV, double giaTien, char xeploai) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giaTien = giaTien;
        this.xeploai = xeploai;
    }

    // Đã đổi tên HienThi -> hienThi
    abstract public void hienThi(); 

    abstract public double tinhChiPhi();
    
    // Đã đổi tên toFileString -> chuyenThanhChuoiLuuTapTin
    abstract public String chuyenThanhChuoiLuuTapTin();

    // Đã đổi tên setGiaTien -> thietLapGiaTien
    public void thietLapGiaTien(double giaMoi) {
        if (giaMoi > 0) {
            this.giaTien = giaMoi;
        }
    }
}