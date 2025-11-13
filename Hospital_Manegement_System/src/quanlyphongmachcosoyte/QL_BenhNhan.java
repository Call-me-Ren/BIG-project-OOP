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
    private static final String FILE_BENHNHAN = "data_benhnhan.txt";

    public QL_BenhNhan() {
        this.danhSachBN = new ArrayList<>();
    }

    // Hàm trả về danh sách để GUI sử dụng (FIX LỖI ĐỎ)
    public List<BenhNhan> getDanhSachBN() {
        return this.danhSachBN;
    }

    public void themBN(BenhNhan bn) {
        if (timKiemBN(bn.getMaBN()) != null) {
            System.out.println("Loi: Ma BN " + bn.getMaBN() + " da ton tai.");
            return;
        }
        this.danhSachBN.add(bn);
        System.out.println("Da them benh nhan " + bn.hoTen + " vao danh sach.");
    }

    public BenhNhan timKiemBN(String maBN) {
        for (BenhNhan bn : danhSachBN) {
            if (bn.getMaBN().equalsIgnoreCase(maBN)) {
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
                bn.setBenhLy(benhLyMoi);
                System.out.println("Da cap nhat benh ly!");
            }
        } else {
            System.out.println("Khong tim thay benh nhan voi ma: " + maBN);
        }
    }
    
    public boolean isRong() {
        return this.danhSachBN.isEmpty();
    }
    
    public void luuVaoFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_BENHNHAN))) {
            for (BenhNhan bn : danhSachBN) {
                out.println(bn.toFileString());
            }
        } catch (IOException e) {
            System.err.println("Loi khi luu file benh nhan: " + e.getMessage());
        }
    }

    public void docTuFile() {
        File file = new File(FILE_BENHNHAN);
        if (!file.exists()) {
            return;
        }
        this.danhSachBN.clear();
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");
                try {
                    if (parts.length == 8) {
                        BenhNhan bn = new BenhNhan(parts[0], parts[1], parts[2], parts[3], parts[4], 
                                parts[5], parts[6], parts[7]);
                        this.danhSachBN.add(bn);
                    }
                } catch (Exception e) {
                    System.err.println("Loi parsing line BN: " + line);
                }
            }
        } catch (FileNotFoundException e) {
        }
    }
}