package quanlyphongmachcosoyte;

import java.util.ArrayList;
import java.util.List;

public class HoaDon implements ThanhToan {
    private String maHoaDon;
    private String maBN;
    private String ngayLap;
    private List<DichVuYTe> danhSachDichVuSuDung;

    public HoaDon(String maHoaDon, String maBN, String ngayLap) {
        this.maHoaDon = maHoaDon;
        this.maBN = maBN;
        this.ngayLap = ngayLap;
        this.danhSachDichVuSuDung = new ArrayList<>();
    }

    public void themDichVu(DichVuYTe dv) {
        this.danhSachDichVuSuDung.add(dv);
    }

    @Override
    public double tinhTongTien() {
        double tongTien = 0;
        for (DichVuYTe dv : danhSachDichVuSuDung) {
            tongTien += dv.tinhChiPhi();
        }
        return tongTien;
    }

    public void xuatHoaDon() {
        System.out.println("--- THONG TIN HOA DON ---");
        System.out.println("Ma Hoa Don: " + this.maHoaDon);
        System.out.println("Ma Benh Nhan: " + this.maBN);
        System.out.println("Ngay Lap: " + this.ngayLap);
        System.out.println("--- Chi tiet dich vu ---");

        if (danhSachDichVuSuDung.isEmpty()) {
            System.out.println("Khong su dung dich vu nao.");
        } else {
            for (DichVuYTe dv : danhSachDichVuSuDung) {
                System.out.println("- " + dv.tenDV + ": " + String.format("%,.0f", dv.tinhChiPhi()) + " VND");
            }
        }

        System.out.println("-------------------------");
        System.out.println("==> TONG TIEN: " + String.format("%,.0f", tinhTongTien()) + " VND");
        System.out.println("-------------------------");
    }

    public String layMaHoaDon() {
        return maHoaDon;
    }

    public String layMaBN() {
        return maBN;
    }
    
    public String layNgayLap() {
        return ngayLap;
    }
    
    public List<DichVuYTe> layDanhSachDichVuSuDung() {
        return danhSachDichVuSuDung;
    }
    
    public String chuyenThanhChuoiLuuTapTin() {
        StringBuilder chuoiKetNoi = new StringBuilder();
        chuoiKetNoi.append(maHoaDon).append(";");
        chuoiKetNoi.append(maBN).append(";");
        chuoiKetNoi.append(ngayLap);

        if (!danhSachDichVuSuDung.isEmpty()) {
            chuoiKetNoi.append(";");
            for (int i = 0; i < danhSachDichVuSuDung.size(); i++) {
                chuoiKetNoi.append(danhSachDichVuSuDung.get(i).maDV); 
                if (i < danhSachDichVuSuDung.size() - 1) {
                    chuoiKetNoi.append(",");
                }
            }
        }
        return chuoiKetNoi.toString();
    }
}