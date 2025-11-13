package quanlyphongmachcosoyte.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import quanlyphongmachcosoyte.model.DichVuYTe;
import quanlyphongmachcosoyte.model.Thuoc;
import quanlyphongmachcosoyte.model.XetNghiem;

public class QL_DichVu {
    private List<DichVuYTe> danhSachDV;
    private static final String TAP_TIN_DICH_VU = "data_dichvu.txt";

    public QL_DichVu() {
        this.danhSachDV = new ArrayList<>();
    }

    public List<DichVuYTe> layDanhSachDV() {
        return this.danhSachDV;
    }

    public void themDV(DichVuYTe dv) {
        this.danhSachDV.add(dv);
        System.out.println("Da them dich vu " + dv.layTenDV() + " vao danh sach.");
    }

    public DichVuYTe timKiemDV(String maDV) {
        for (DichVuYTe dv : danhSachDV) {
            if (dv.layMaDV().equalsIgnoreCase(maDV)) {
                return dv;
            }
        }
        return null;
    }

    public void xuatDanhSach() {
        if (danhSachDV.isEmpty()) {
            System.out.println("Danh sach dich vu rong.");
            return;
        }
        System.out.println("--- DANH SACH DICH VU Y TE ---");
        for (DichVuYTe dv : danhSachDV) {
            dv.hienThi();
            System.out.println("-------------------------");
        }
    }

    public void xoaDV(String maDV) {
        DichVuYTe dv = timKiemDV(maDV);
        if (dv != null) {
            danhSachDV.remove(dv);
            System.out.println("Da xoa dich vu: " + dv.layTenDV());
        } else {
            System.out.println("Khong tim thay dich vu voi ma: " + maDV);
        }
    }

    public void suaDV(String maDV, Scanner scanner) {
        DichVuYTe dv = timKiemDV(maDV);
        if (dv != null) {
            System.out.println("Tim thay dich vu: " + dv.layTenDV());
            System.out.print("Nhap gia tien moi: ");
            try {
                double giaMoi = scanner.nextDouble();
                scanner.nextLine();
                dv.thietLapGiaTien(giaMoi);
                System.out.println("Da cap nhat gia thanh cong!");
            } catch (Exception e) {
                System.out.println("Loi: Vui long nhap so.");
                scanner.nextLine();
            }
        } else {
            System.out.println("Khong tim thay dich vu voi ma: " + maDV);
        }
    }

    public boolean kiemTraRong() {
        return this.danhSachDV.isEmpty();
    }

    public void luuVaoTapTin() {
        try (PrintWriter vietRa = new PrintWriter(new FileWriter(TAP_TIN_DICH_VU))) {
            for (DichVuYTe dv : danhSachDV) {
                vietRa.println(dv.chuyenThanhChuoiLuuTapTin());
            }
        } catch (IOException e) {
            System.err.println("Loi khi luu file dich vu: " + e.getMessage());
        }
    }

    public void docDuLieuTuTapTin() {
        File tapTin = new File(TAP_TIN_DICH_VU);
        if (!tapTin.exists()) {
            return;
        }
        this.danhSachDV.clear();
        try (Scanner docTapTin = new Scanner(tapTin)) {
            while (docTapTin.hasNextLine()) {
                String dong = docTapTin.nextLine();
                String[] phanTach = dong.split(";");
                if (phanTach.length < 1)
                    continue;
                String loaiDV = phanTach[0];
                try {
                    if (loaiDV.equals("Thuoc") && phanTach.length == 7) {
                        Thuoc t = new Thuoc(phanTach[1], phanTach[2], Double.parseDouble(phanTach[3]), phanTach[4],
                                Double.parseDouble(phanTach[5]), phanTach[6].charAt(0));
                        this.danhSachDV.add(t);
                    } else if (loaiDV.equals("XetNghiem") && phanTach.length == 7) {
                        XetNghiem xn = new XetNghiem(phanTach[1], phanTach[2], phanTach[3].charAt(0),
                                Double.parseDouble(phanTach[4]),
                                phanTach[5], phanTach[6]);
                        this.danhSachDV.add(xn);
                    }
                } catch (Exception e) {
                    System.err.println("Loi parsing DV: " + dong);
                }
            }
        } catch (FileNotFoundException e) {
        }
    }
}