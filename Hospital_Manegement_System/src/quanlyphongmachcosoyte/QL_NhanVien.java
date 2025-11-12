package quanlyphongmachcosoyte;

// <--- THÊM MỚI CÁC IMPORT ĐỂ XỬ LÝ FILE --->
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
    // <--- THÊM MỚI: Tên file để lưu trữ
    private static final String FILE_NHANVIEN = "data_nhanvien.txt";

    // Constructor
    public QL_NhanVien() {
        this.danhSachNV = new ArrayList<>();
    }

    /**
     * Thêm một nhân viên mới (có thể là Bác Sĩ, Điều Dưỡng, KTV
     * nhờ vào tính đa hình).
     */
    public void themNV(NhanVien nv) {
        // Kiểm tra xem mã NV đã tồn tại chưa
        if (timKiemNV(nv.getMaNV()) != null) {
            System.out.println("Loi: Ma NV " + nv.getMaNV() + " da ton tai.");
            return;
        }
        this.danhSachNV.add(nv);
        System.out.println("Da them nhan vien: " + nv.hoTen);
    }

    /**
     * Tìm kiếm nhân viên theo Mã NV.
     * Trả về đối tượng NhanVien nếu tìm thấy, ngược lại trả về null.
     */
    public NhanVien timKiemNV(String maNV) {
        for (NhanVien nv : danhSachNV) {
            // Sử dụng getMaNV()
            if (nv.getMaNV().equalsIgnoreCase(maNV)) {
                return nv;
            }
        }
        return null;
    }

    /**
     * Xóa nhân viên khỏi danh sách dựa trên mã NV.
     */
    public void xoaNV(String maNV) {
        NhanVien nv = timKiemNV(maNV);
        if (nv != null) {
            danhSachNV.remove(nv);
            System.out.println("Da xoa nhan vien: " + nv.hoTen + " (Ma: " + maNV + ")");
        } else {
            System.out.println("Khong tim thay nhan vien voi ma: " + maNV);
        }
    }

    /**
     * Sửa thông tin nhân viên (ví dụ: sửa hệ số lương).
     * Thuộc tính heSoLuong là 'protected' trong NhanVien,
     * nên ta có thể truy cập trực tiếp.
     */
    public void suaNV(String maNV, Scanner scanner) {
        NhanVien nv = timKiemNV(maNV);
        if (nv != null) {
            System.out.println("Tim thay nhan vien: " + nv.hoTen);
            System.out.print("Nhap He So Luong moi (enter de bo qua): ");
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                try {
                    double heSoMoi = Double.parseDouble(input);
                    nv.heSoLuong = heSoMoi; // 'heSoLuong' là protected, có thể truy cập
                    System.out.println("Da cap nhat he so luong!");
                } catch (Exception e) {
                    System.out.println("Loi: Nhap sai dinh dang so.");
                }
            }
            // Bạn có thể thêm các câu hỏi để sửa các thông tin khác ở đây
        } else {
            System.out.println("Khong tim thay nhan vien voi ma: " + maNV);
        }
    }

    /**
     * Xuất danh sách toàn bộ nhân viên, sử dụng tính đa hình của hàm xuat().
     */
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

    /**
     * Tính tổng quỹ lương phải trả cho tất cả nhân viên trong tháng.
     * Sử dụng tính đa hình của hàm TinhLuong().
     */
    public void tinhTongQuyLuong() {
        double tongLuong = 0;
        if (danhSachNV.isEmpty()) {
            System.out.println("Chua co nhan vien nao de tinh luong.");
            return;
        }
        
        for (NhanVien nv : danhSachNV) {
            // Tự động gọi TinhLuong() của lớp con
            tongLuong += nv.TinhLuong(); 
        }
        System.out.println("=================================");
        System.out.println("==> TONG QUY LUONG THANG: " + String.format("%.0f", tongLuong) + " VND");
        System.out.println("=================================");
    }
    
    // <--- THÊM MỚI: Hàm kiểm tra danh sách rỗng --->
    public boolean isRong() {
        return this.danhSachNV.isEmpty();
    }
    
    // <--- THÊM MỚI: Hàm lưu danh sách vào file --->
    public void luuVaoFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NHANVIEN))) {
            for (NhanVien nv : danhSachNV) {
                // Sử dụng tính đa hình, tự động gọi toFileString() của BacSi, DieuDuong...
                out.println(nv.toFileString());
            }
        } catch (IOException e) {
            System.err.println("Loi khi luu file nhan vien: " + e.getMessage());
        }
    }
    
    // <--- THÊM MỚI: Hàm đọc danh sách từ file --->
    public void docTuFile() {
        File file = new File(FILE_NHANVIEN);
        if (!file.exists()) {
            System.out.println("File " + FILE_NHANVIEN + " khong ton tai. Bo qua viec doc.");
            return;
        }

        this.danhSachNV.clear(); // Xóa dữ liệu cũ trước khi đọc
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");

                if (parts.length < 1) continue;

                String loaiNV = parts[0];
                try {
                    if (loaiNV.equals("BacSi") && parts.length == 10) {
                        // parts[1] -> parts[9]
                        BacSi bs = new BacSi(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], 
                                Double.parseDouble(parts[8]), Double.parseDouble(parts[9]));
                        this.danhSachNV.add(bs);
                    } else if (loaiNV.equals("DieuDuong") && parts.length == 11) {
                        // parts[1] -> parts[10]
                        DieuDuong dd = new DieuDuong(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], 
                                Double.parseDouble(parts[8]), Double.parseDouble(parts[9]), Integer.parseInt(parts[10]));
                        this.danhSachNV.add(dd);
                    } else if (loaiNV.equals("KiThuatVien") && parts.length == 12) {
                        // parts[1] -> parts[11]
                        KiThuatVien ktv = new KiThuatVien(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], 
                                Double.parseDouble(parts[8]), Double.parseDouble(parts[9]), Double.parseDouble(parts[10]), Integer.parseInt(parts[11]));
                        this.danhSachNV.add(ktv);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Loi dinh dang so khi doc file NV: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            // Trường hợp này đã kiểm tra ở trên, nhưng vẫn giữ an toàn
        } catch (Exception e) {
            System.err.println("Loi khong xac dinh khi doc file NV: " + e.getMessage());
        }
    }
}