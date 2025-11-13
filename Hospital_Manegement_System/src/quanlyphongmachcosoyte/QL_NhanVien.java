package quanlyphongmachcosoyte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QL_NhanVien {
    private List<NhanVien> danhSachNV;
    private static final String FILE_NHANVIEN = "data_nhanvien.txt";

    public QL_NhanVien() {
        this.danhSachNV = new ArrayList<>();
    }

    // Hàm trả về danh sách để GUI sử dụng (FIX LỖI ĐỎ)
    public List<NhanVien> getDanhSachNV() {
        return this.danhSachNV;
    }

    public void themNV(NhanVien nv) {
        if (timKiemNV(nv.getMaNV()) != null) {
            System.out.println("Loi: Ma NV " + nv.getMaNV() + " da ton tai.");
            return;
        }
        this.danhSachNV.add(nv);
        System.out.println("Da them nhan vien: " + nv.hoTen);
    }

    public NhanVien timKiemNV(String maNV) {
        for (NhanVien nv : danhSachNV) {
            if (nv.getMaNV().equalsIgnoreCase(maNV)) {
                return nv;
            }
        }
        return null;
    }

    public void xoaNV(String maNV) {
        NhanVien nv = timKiemNV(maNV);
        if (nv != null) {
            danhSachNV.remove(nv);
            System.out.println("Da xoa nhan vien: " + nv.hoTen);
        } else {
            System.out.println("Khong tim thay nhan vien voi ma: " + maNV);
        }
    }

    public void suaNV(String maNV, Scanner scanner) {
        NhanVien nv = timKiemNV(maNV);
        if (nv != null) {
            System.out.println("Tim thay nhan vien: " + nv.hoTen);
            System.out.print("Nhap He So Luong moi (enter de bo qua): ");
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                try {
                    double heSoMoi = Double.parseDouble(input);
                    nv.heSoLuong = heSoMoi;
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
            tongLuong += nv.TinhLuong(); 
        }
        System.out.println("=================================");
        System.out.println("==> TONG QUY LUONG THANG: " + String.format("%.0f", tongLuong) + " VND");
        System.out.println("=================================");
    }
    
    public boolean isRong() {
        return this.danhSachNV.isEmpty();
    }
    
    public void luuVaoFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NHANVIEN))) {
            for (NhanVien nv : danhSachNV) {
                out.println(nv.toFileString());
            }
        } catch (IOException e) {
            System.err.println("Loi khi luu file nhan vien: " + e.getMessage());
        }
    }
    
    public void docTuFile() {
        File file = new File(FILE_NHANVIEN);
        if (!file.exists()) {
            return;
        }
        this.danhSachNV.clear();
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length < 1) continue;
                String loaiNV = parts[0];
                try {
                    if (loaiNV.equals("BacSi") && parts.length == 10) {
                        BacSi bs = new BacSi(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], 
                                Double.parseDouble(parts[8]), Double.parseDouble(parts[9]));
                        this.danhSachNV.add(bs);
                    } else if (loaiNV.equals("DieuDuong") && parts.length == 11) {
                        DieuDuong dd = new DieuDuong(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], 
                                Double.parseDouble(parts[8]), Double.parseDouble(parts[9]), Integer.parseInt(parts[10]));
                        this.danhSachNV.add(dd);
                    } else if (loaiNV.equals("KiThuatVien") && parts.length == 12) {
                        KiThuatVien ktv = new KiThuatVien(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], 
                                Double.parseDouble(parts[8]), Double.parseDouble(parts[9]), Double.parseDouble(parts[10]), Integer.parseInt(parts[11]));
                        this.danhSachNV.add(ktv);
                    }
                } catch (Exception e) {
                    System.err.println("Loi parsing NV: " + line);
                }
            }
        } catch (FileNotFoundException e) {
        }
    }
}