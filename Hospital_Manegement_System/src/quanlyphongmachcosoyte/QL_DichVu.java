package quanlyphongmachcosoyte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QL_DichVu {
    private List<DichVuYTe> danhSachDV;
    private static final String FILE_DICHVU = "data_dichvu.txt";

    public QL_DichVu() {
        this.danhSachDV = new ArrayList<>();
    }

    // Hàm trả về danh sách để GUI sử dụng (FIX LỖI ĐỎ)
    public List<DichVuYTe> getDanhSachDV() {
        return this.danhSachDV;
    }

    public void themDV(DichVuYTe dv) {
        this.danhSachDV.add(dv);
        System.out.println("Da them dich vu " + dv.tenDV + " vao danh sach.");
    }

    public DichVuYTe timKiemDV(String maDV) {
        for (DichVuYTe dv : danhSachDV) {
            if (dv.maDV.equalsIgnoreCase(maDV)) {
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
            dv.HienThi();
            System.out.println("-------------------------");
        }
    }

    public void xoaDV(String maDV) {
        DichVuYTe dv = timKiemDV(maDV);
        if (dv != null) {
            danhSachDV.remove(dv);
            System.out.println("Da xoa dich vu: " + dv.tenDV);
        } else {
            System.out.println("Khong tim thay dich vu voi ma: " + maDV);
        }
    }

    public void suaDV(String maDV, Scanner scanner) {
        DichVuYTe dv = timKiemDV(maDV);
        if (dv != null) {
            System.out.println("Tim thay dich vu: " + dv.tenDV);
            System.out.print("Nhap gia tien moi: ");
            try {
                double giaMoi = scanner.nextDouble();
                scanner.nextLine(); 
                dv.setGiaTien(giaMoi);
                System.out.println("Da cap nhat gia thanh cong!");
            } catch (Exception e) {
                System.out.println("Loi: Vui long nhap so.");
                scanner.nextLine();
            }
        } else {
            System.out.println("Khong tim thay dich vu voi ma: " + maDV);
        }
    }
    
    public boolean isRong() {
        return this.danhSachDV.isEmpty();
    }
    
    public void luuVaoFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_DICHVU))) {
            for (DichVuYTe dv : danhSachDV) {
                out.println(dv.toFileString());
            }
        } catch (IOException e) {
            System.err.println("Loi khi luu file dich vu: " + e.getMessage());
        }
    }

    public void docTuFile() {
        File file = new File(FILE_DICHVU);
        if (!file.exists()) {
            return;
        }
        this.danhSachDV.clear();
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length < 1) continue;
                String loaiDV = parts[0];
                try {
                    if (loaiDV.equals("Thuoc") && parts.length == 7) {
                        Thuoc t = new Thuoc(parts[1], parts[2], Double.parseDouble(parts[3]), parts[4], 
                                Double.parseDouble(parts[5]), parts[6].charAt(0));
                        this.danhSachDV.add(t);
                    } else if (loaiDV.equals("XetNghiem") && parts.length == 7) {
                        XetNghiem xn = new XetNghiem(parts[1], parts[2], parts[3].charAt(0), Double.parseDouble(parts[4]), 
                                parts[5], parts[6]);
                        this.danhSachDV.add(xn);
                    }
                } catch (Exception e) {
                    System.err.println("Loi parsing DV: " + line);
                }
            }
        } catch (FileNotFoundException e) {
        }
    }
}