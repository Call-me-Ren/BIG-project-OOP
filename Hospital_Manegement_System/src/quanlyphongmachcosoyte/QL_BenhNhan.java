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

// Sửa: Thêm hàm xoaBN, suaBN
// Sửa: Sửa logic timKiemBN
public class QL_BenhNhan {
    private List<BenhNhan> danhSachBN;
    // <--- THÊM MỚI: Tên file để lưu trữ
    private static final String FILE_BENHNHAN = "data_benhnhan.txt";

    // Constructor
    public QL_BenhNhan() {
        this.danhSachBN = new ArrayList<>();
    }

    // Thêm bệnh nhân
    public void themBN(BenhNhan bn) {
        // <--- SỬA: Kiểm tra trùng mã BN --->
        if (timKiemBN(bn.getMaBN()) != null) {
            System.out.println("Loi: Ma BN " + bn.getMaBN() + " da ton tai.");
            return;
        }
        this.danhSachBN.add(bn);
        System.out.println("Da them benh nhan " + bn.hoTen + " vao danh sach.");
    }

    // Tìm bệnh nhân theo Mã Bệnh Nhân
    public BenhNhan timKiemBN(String maBN) {
        for (BenhNhan bn : danhSachBN) {
            // Sửa: Tìm theo getMaBN()
            if (bn.getMaBN().equalsIgnoreCase(maBN)) {
                return bn;
            }
        }
        return null;
    }

    // Xuất toàn bộ danh sách bệnh nhân
    public void xuatDanhSach() {
        if (danhSachBN.isEmpty()) {
            System.out.println("Danh sach benh nhan rong.");
            return;
        }
        System.out.println("--- DANH SACH BENH NHAN ---");
        for (BenhNhan bn : danhSachBN) {
            bn.xuat(); // Gọi hàm xuat() của mỗi bệnh nhân
            System.out.println("--------------------");
        }
    }

    // Sửa: Thêm hàm xóa
    public void xoaBN(String maBN) {
        BenhNhan bn = timKiemBN(maBN);
        if (bn != null) {
            danhSachBN.remove(bn);
            System.out.println("Da xoa benh nhan: " + bn.hoTen);
        } else {
            System.out.println("Khong tim thay benh nhan voi ma: " + maBN);
        }
    }

    // Sửa: Thêm hàm sửa
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
            // Thêm các câu hỏi sửa thông tin khác (họ tên, sđt...) ở đây
        } else {
            System.out.println("Khong tim thay benh nhan voi ma: " + maBN);
        }
    }
    
    // <--- THÊM MỚI: Hàm kiểm tra danh sách rỗng --->
    public boolean isRong() {
        return this.danhSachBN.isEmpty();
    }
    
    // <--- THÊM MỚI: Hàm lưu danh sách vào file --->
    public void luuVaoFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_BENHNHAN))) {
            for (BenhNhan bn : danhSachBN) {
                out.println(bn.toFileString());
            }
        } catch (IOException e) {
            System.err.println("Loi khi luu file benh nhan: " + e.getMessage());
        }
    }

    // <--- THÊM MỚI: Hàm đọc danh sách từ file --->
    public void docTuFile() {
        File file = new File(FILE_BENHNHAN);
        if (!file.exists()) {
            System.out.println("File " + FILE_BENHNHAN + " khong ton tai. Bo qua viec doc.");
            return;
        }

        this.danhSachBN.clear(); // Xóa dữ liệu cũ
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");

                try {
                    // maID;hoTen;ngaySinh;gioiTinh;sdt;maBN;ngayVaoVien;benhLy
                    // 0     1       2         3         4    5      6           7
                    if (parts.length == 8) {
                        BenhNhan bn = new BenhNhan(parts[0], parts[1], parts[2], parts[3], parts[4], 
                                parts[5], parts[6], parts[7]);
                        this.danhSachBN.add(bn);
                    }
                } catch (Exception e) {
                    System.err.println("Loi parsing line BN: " + line + " | Loi: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            // Đã kiểm tra
        }
    }
}