package quanlyphongmachcosoyte;

// <--- THÊM MỚI CÁC IMPORT ĐỂ XỬ LÝ FILE --->
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Sửa: Thêm import

// Sửa: Thêm hàm xoaDV, suaDV
public class QL_DichVu {
    private List<DichVuYTe> danhSachDV;
    // <--- THÊM MỚI: Tên file để lưu trữ
    private static final String FILE_DICHVU = "data_dichvu.txt";

    public QL_DichVu() {
        this.danhSachDV = new ArrayList<>();
    }

    // Thêm dịch vụ (có thể là Thuốc hoặc Xét Nghiệm)
    public void themDV(DichVuYTe dv) {
        this.danhSachDV.add(dv);
        System.out.println("Da them dich vu " + dv.tenDV + " vao danh sach.");
    }

    // Tìm dịch vụ theo Mã Dịch Vụ
    public DichVuYTe timKiemDV(String maDV) {
        for (DichVuYTe dv : danhSachDV) {
            if (dv.maDV.equalsIgnoreCase(maDV)) {
                return dv;
            }
        }
        return null;
    }

    // Xuất toàn bộ danh sách dịch vụ
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

    // Sửa: Thêm hàm xóa
    public void xoaDV(String maDV) {
        DichVuYTe dv = timKiemDV(maDV);
        if (dv != null) {
            danhSachDV.remove(dv);
            System.out.println("Da xoa dich vu: " + dv.tenDV);
        } else {
            System.out.println("Khong tim thay dich vu voi ma: " + maDV);
        }
    }

    // Sửa: Thêm hàm sửa
    public void suaDV(String maDV, Scanner scanner) {
        DichVuYTe dv = timKiemDV(maDV);
        if (dv != null) {
            System.out.println("Tim thay dich vu: " + dv.tenDV);
            System.out.println("Gia goc hien tai: " + dv.giaTien);
            System.out.print("Nhap gia tien moi: ");
            try {
                double giaMoi = scanner.nextDouble();
                scanner.nextLine(); // Tiêu thụ ký tự enter
                dv.setGiaTien(giaMoi);
                System.out.println("Da cap nhat gia thanh cong!");
            } catch (Exception e) {
                System.out.println("Loi: Vui long nhap so. Huy cap nhat.");
                scanner.nextLine(); // Xóa bộ đệm nếu nhập sai
            }
        } else {
            System.out.println("Khong tim thay dich vu voi ma: " + maDV);
        }
    }
    
    // <--- THÊM MỚI: Hàm kiểm tra danh sách rỗng --->
    public boolean isRong() {
        return this.danhSachDV.isEmpty();
    }
    
    // <--- THÊM MỚI: Hàm lưu danh sách vào file --->
    public void luuVaoFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_DICHVU))) {
            for (DichVuYTe dv : danhSachDV) {
                out.println(dv.toFileString());
            }
        } catch (IOException e) {
            System.err.println("Loi khi luu file dich vu: " + e.getMessage());
        }
    }

    // <--- THÊM MỚI: Hàm đọc danh sách từ file --->
    public void docTuFile() {
        File file = new File(FILE_DICHVU);
        if (!file.exists()) {
            System.out.println("File " + FILE_DICHVU + " khong ton tai. Bo qua viec doc.");
            return;
        }

        this.danhSachDV.clear(); // Xóa dữ liệu cũ
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");

                if (parts.length < 1) continue;

                String loaiDV = parts[0];
                try {
                    if (loaiDV.equals("Thuoc") && parts.length == 7) {
                        // Loại;maDV;tenDV;giaTien;donViTinh;hesoThuoc;xeploai
                        // 0    1      2      3        4          5          6
                        Thuoc t = new Thuoc(parts[1], parts[2], Double.parseDouble(parts[3]), parts[4], 
                                Double.parseDouble(parts[5]), parts[6].charAt(0));
                        this.danhSachDV.add(t);
                    } else if (loaiDV.equals("XetNghiem") && parts.length == 7) {
                        // Loại;maDV;tenDV;xeploai;giaTien;yeuCauThietBi;loaiXetNghiem
                        // 0    1      2      3       4         5                6
                        XetNghiem xn = new XetNghiem(parts[1], parts[2], parts[3].charAt(0), Double.parseDouble(parts[4]), 
                                parts[5], parts[6]);
                        this.danhSachDV.add(xn);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Loi dinh dang so khi doc file DV: " + line);
                } catch (Exception e) {
                    System.err.println("Loi parsing line DV: " + line + " | Loi: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            // Đã kiểm tra
        }
    }
}