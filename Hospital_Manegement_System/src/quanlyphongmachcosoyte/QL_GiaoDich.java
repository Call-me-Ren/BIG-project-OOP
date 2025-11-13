package quanlyphongmachcosoyte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QL_GiaoDich {
    private List<HoaDon> danhSachHoaDon;
    private static final String TAP_TIN_HOA_DON = "data_hoadon.txt";

    public QL_GiaoDich() {
        this.danhSachHoaDon = new ArrayList<>();
    }
    
    public List<HoaDon> layDanhSachHoaDon() {
        return this.danhSachHoaDon;
    }

    public void themHoaDon(HoaDon hd) {
        this.danhSachHoaDon.add(hd);
        System.out.println("Da lap hoa don " + hd.layMaHoaDon() + " cho BN " + hd.layMaBN());
    }

    public HoaDon timKiemHD(String maHD) {
        for (HoaDon hd : danhSachHoaDon) {
            if (hd.layMaHoaDon().equalsIgnoreCase(maHD)) {
                return hd;
            }
        }
        return null;
    }

    public void xoaHD(String maHD) {
        HoaDon hd = timKiemHD(maHD);
        if (hd != null) {
            danhSachHoaDon.remove(hd);
            System.out.println("Da xoa hoa don: " + hd.layMaHoaDon());
        } else {
            System.out.println("Khong tim thay hoa don voi ma: " + maHD);
        }
    }

    public void xuatLichSuGiaoDich() {
        if (danhSachHoaDon.isEmpty()) {
            System.out.println("Chua co giao dich nao.");
            return;
        }
        System.out.println("--- LICH SU GIAO DICH (HOA DON) ---");
        for (HoaDon hd : danhSachHoaDon) {
            hd.xuatHoaDon();
            System.out.println("---------------------------------");
        }
    }

    public double tinhTongDoanhThu() { 
        double tong = 0;
        for (HoaDon hd : danhSachHoaDon) {
            tong += hd.tinhTongTien();
        }
        return tong;
    }

    public void luuVaoTapTin() {
        try (PrintWriter vietRa = new PrintWriter(new FileWriter(TAP_TIN_HOA_DON))) {
            for (HoaDon hd : danhSachHoaDon) {
                vietRa.println(hd.chuyenThanhChuoiLuuTapTin());
            }
        } catch (IOException e) {
            System.err.println("Loi khi luu file hoa don: " + e.getMessage());
        }
    }

    public void docDuLieuTuTapTin(QL_DichVu qlDichVu) {
        File tapTin = new File(TAP_TIN_HOA_DON);
        if (!tapTin.exists()) {
            return;
        }
        this.danhSachHoaDon.clear();
        int soLuong = 0;
        try (Scanner docTapTin = new Scanner(tapTin)) {
            while (docTapTin.hasNextLine()) {
                String dong = docTapTin.nextLine();
                String[] phanTach = dong.split(";", -1); 
                
                if (phanTach.length >= 3) {
                    try {
                        String maHD = phanTach[0];
                        String maBN = phanTach[1];
                        String ngayLap = phanTach[2];
                        
                        HoaDon hd = new HoaDon(maHD, maBN, ngayLap);
                        
                        if (phanTach.length == 4 && !phanTach[3].isEmpty()) {
                            String[] maDVs = phanTach[3].split(",");
                            for (String maDV : maDVs) {
                                DichVuYTe dv = qlDichVu.timKiemDV(maDV.trim());
                                if (dv != null) {
                                    hd.themDichVu(dv);
                                } else {
                                    System.err.println("Loi: Khong tim thay Dich Vu voi ma: " + maDV + " khi doc file Hoa Don.");
                                }
                            }
                        }
                        this.danhSachHoaDon.add(hd);
                        soLuong++;
                    } catch (Exception e) {
                        System.err.println("Loi parsing Hoa Don: " + dong);
                    }
                }
            }
            System.out.println("Da doc " + soLuong + " hoa don tu file.");
        } catch (FileNotFoundException e) {
        }
    }
}