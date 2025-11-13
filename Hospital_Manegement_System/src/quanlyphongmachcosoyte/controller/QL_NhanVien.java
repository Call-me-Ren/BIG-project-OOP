package quanlyphongmachcosoyte.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import quanlyphongmachcosoyte.model.BacSi;
import quanlyphongmachcosoyte.model.DieuDuong;
import quanlyphongmachcosoyte.model.KiThuatVien;
import quanlyphongmachcosoyte.model.NhanVien;

public class QL_NhanVien {
    private List<NhanVien> danhSachNV;
    private static final String TAP_TIN_NHAN_VIEN = "data_nhanvien.txt";

    public QL_NhanVien() {
        this.danhSachNV = new ArrayList<>();
    }

    public List<NhanVien> layDanhSachNV() {
        return this.danhSachNV;
    }

    public void themNV(NhanVien nv) {
        if (timKiemNV(nv.layMaNV()) != null) {
            System.out.println("Loi: Ma NV " + nv.layMaNV() + " da ton tai.");
            return;
        }
        this.danhSachNV.add(nv);
        System.out.println("Da them nhan vien: " + nv.layHoTen());
    }

    public NhanVien timKiemNV(String maNV) {
        for (NhanVien nv : danhSachNV) {
            if (nv.layMaNV().equalsIgnoreCase(maNV)) {
                return nv;
            }
        }
        return null;
    }

    public void xoaNV(String maNV) {
        NhanVien nv = timKiemNV(maNV);
        if (nv != null) {
            danhSachNV.remove(nv);
            System.out.println("Da xoa nhan vien: " + nv.layHoTen());
        } else {
            System.out.println("Khong tim thay nhan vien voi ma: " + maNV);
        }
    }

    public void suaNV(String maNV, Scanner scanner) {
        NhanVien nv = timKiemNV(maNV);
        if (nv != null) {
            System.out.println("Tim thay nhan vien: " + nv.layHoTen());
            System.out.print("Nhap He So Luong moi (enter de bo qua): ");
            String duLieuNhap = scanner.nextLine();
            if (!duLieuNhap.trim().isEmpty()) {
                try {
                    double heSoMoi = Double.parseDouble(duLieuNhap);
                    nv.thietLapHeSoLuong(heSoMoi);
                    System.out.println("Da cap nhat he so luong!");
                } catch (Exception e) {
                    System.out.println("Loi: Nhap sai dinh dang so.");
                }
            }
        } else {
            System.out.println("Khong tim thay nhan vien voi ma: " + maNV);
        }
    }

    public void xuatDanhSach() {
        if (danhSachNV.isEmpty()) {
            System.out.println("Danh sach nhan vien rong.");
            return;
        }
        System.out.println("--- DANH SACH TOAN BO NHAN VIEN ---");
        for (NhanVien nv : danhSachNV) {
            nv.xuat();
            System.out.println("---------------------------------");
        }
    }

    public void tinhTongQuyLuong() {
        double tongLuong = 0;
        if (danhSachNV.isEmpty()) {
            System.out.println("Chua co nhan vien nao de tinh luong.");
            return;
        }
        for (NhanVien nv : danhSachNV) {
            tongLuong += nv.tinhLuong();
        }
        System.out.println("=================================");
        System.out.println("==> TONG QUY LUONG THANG: " + String.format("%.0f", tongLuong) + " VND");
        System.out.println("=================================");
    }

    public boolean kiemTraRong() {
        return this.danhSachNV.isEmpty();
    }

    public void luuVaoTapTin() {
        try (PrintWriter vietRa = new PrintWriter(new FileWriter(TAP_TIN_NHAN_VIEN))) {
            for (NhanVien nv : danhSachNV) {
                vietRa.println(nv.chuyenThanhChuoiLuuTapTin());
            }
        } catch (IOException e) {
            System.err.println("Loi khi luu file nhan vien: " + e.getMessage());
        }
    }

    public void docDuLieuTuTapTin() {
        File tapTin = new File(TAP_TIN_NHAN_VIEN);
        if (!tapTin.exists()) {
            return;
        }
        this.danhSachNV.clear();
        try (Scanner docTapTin = new Scanner(tapTin)) {
            while (docTapTin.hasNextLine()) {
                String dong = docTapTin.nextLine();
                String[] phanTach = dong.split(";");
                if (phanTach.length < 1)
                    continue;
                String loaiNV = phanTach[0];
                try {
                    if (loaiNV.equals("BacSi") && phanTach.length == 10) {
                        BacSi bs = new BacSi(phanTach[1], phanTach[2], phanTach[3], phanTach[4], phanTach[5],
                                phanTach[6], phanTach[7],
                                Double.parseDouble(phanTach[8]), Double.parseDouble(phanTach[9]));
                        this.danhSachNV.add(bs);
                    } else if (loaiNV.equals("DieuDuong") && phanTach.length == 11) {
                        DieuDuong dd = new DieuDuong(phanTach[1], phanTach[2], phanTach[3], phanTach[4], phanTach[5],
                                phanTach[6], phanTach[7],
                                Double.parseDouble(phanTach[8]), Double.parseDouble(phanTach[9]),
                                Integer.parseInt(phanTach[10]));
                        this.danhSachNV.add(dd);
                    } else if (loaiNV.equals("KiThuatVien") && phanTach.length == 12) {
                        KiThuatVien ktv = new KiThuatVien(phanTach[1], phanTach[2], phanTach[3], phanTach[4],
                                phanTach[5], phanTach[6], phanTach[7],
                                Double.parseDouble(phanTach[8]), Double.parseDouble(phanTach[9]),
                                Double.parseDouble(phanTach[10]), Integer.parseInt(phanTach[11]));
                        this.danhSachNV.add(ktv);
                    }
                } catch (Exception e) {
                    System.err.println("Loi parsing NV: " + dong);
                }
            }
        } catch (FileNotFoundException e) {
        }
    }
}