package quanlyphongmachcosoyte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QL_BenhNhan {
    private List<BenhNhan> danhSachBN;
    private static final String TAP_TIN_BENH_NHAN = "data_benhnhan.txt";

    public QL_BenhNhan() {
        this.danhSachBN = new ArrayList<>();
    }

    public List<BenhNhan> layDanhSachBN() {
        return this.danhSachBN;
    }

    public void themBN(BenhNhan bn) {
        if (timKiemBN(bn.layMaBN()) != null) {
            System.out.println("Loi: Ma BN " + bn.layMaBN() + " da ton tai.");
            return;
        }
        this.danhSachBN.add(bn);
        System.out.println("Da them benh nhan " + bn.hoTen + " vao danh sach.");
    }

    public BenhNhan timKiemBN(String maBN) {
        for (BenhNhan bn : danhSachBN) {
            if (bn.layMaBN().equalsIgnoreCase(maBN)) {
                return bn;
            }
        }
        return null;
    }

    public void xuatDanhSach() {
        if (danhSachBN.isEmpty()) {
            System.out.println("Danh sach benh nhan rong.");
            return;
        }
        System.out.println("--- DANH SACH BENH NHAN ---");
        for (BenhNhan bn : danhSachBN) {
            bn.xuat();
            System.out.println("--------------------");
        }
    }

    public void xoaBN(String maBN) {
        BenhNhan bn = timKiemBN(maBN);
        if (bn != null) {
            danhSachBN.remove(bn);
            System.out.println("Da xoa benh nhan: " + bn.hoTen);
        } else {
            System.out.println("Khong tim thay benh nhan voi ma: " + maBN);
        }
    }

    public void suaBN(String maBN, Scanner scanner) {
        BenhNhan bn = timKiemBN(maBN);
        if (bn != null) {
            System.out.println("Tim thay benh nhan: " + bn.hoTen);
            System.out.print("Nhap benh ly moi (enter de bo qua): ");
            String benhLyMoi = scanner.nextLine();
            if (!benhLyMoi.trim().isEmpty()) {
                bn.thietLapBenhLy(benhLyMoi);
                System.out.println("Da cap nhat benh ly!");
            }
        } else {
            System.out.println("Khong tim thay benh nhan voi ma: " + maBN);
        }
    }
    
    public boolean kiemTraRong() {
        return this.danhSachBN.isEmpty();
    }
    
    public void luuVaoTapTin() {
        try (PrintWriter vietRa = new PrintWriter(new FileWriter(TAP_TIN_BENH_NHAN))) {
            for (BenhNhan bn : danhSachBN) {
                vietRa.println(bn.chuyenThanhChuoiLuuTapTin());
            }
        } catch (IOException e) {
            System.err.println("Loi khi luu file benh nhan: " + e.getMessage());
        }
    }

    public void docDuLieuTuTapTin() {
        File tapTin = new File(TAP_TIN_BENH_NHAN);
        if (!tapTin.exists()) {
            return;
        }
        this.danhSachBN.clear();
        try (Scanner docTapTin = new Scanner(tapTin)) {
            while (docTapTin.hasNextLine()) {
                String dong = docTapTin.nextLine();
                String[] phanTach = dong.split(";");
                try {
                    if (phanTach.length == 8) {
                        BenhNhan bn = new BenhNhan(phanTach[0], phanTach[1], phanTach[2], phanTach[3], phanTach[4], 
                                phanTach[5], phanTach[6], phanTach[7]);
                        this.danhSachBN.add(bn);
                    }
                } catch (Exception e) {
                    System.err.println("Loi parsing line BN: " + dong);
                }
            }
        } catch (FileNotFoundException e) {
        }
    }
}