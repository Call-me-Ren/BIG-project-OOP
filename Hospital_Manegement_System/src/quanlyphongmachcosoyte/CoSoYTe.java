package quanlyphongmachcosoyte;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import quanlyphongmachcosoyte.controller.QL_BenhNhan;
import quanlyphongmachcosoyte.controller.QL_DichVu;
import quanlyphongmachcosoyte.controller.QL_GiaoDich;
import quanlyphongmachcosoyte.controller.QL_NhanVien;
import quanlyphongmachcosoyte.model.BacSi;
import quanlyphongmachcosoyte.model.BenhNhan;
import quanlyphongmachcosoyte.model.DieuDuong;
import quanlyphongmachcosoyte.model.HoaDon;
import quanlyphongmachcosoyte.model.KiThuatVien;
import quanlyphongmachcosoyte.model.Thuoc;
import quanlyphongmachcosoyte.model.XetNghiem;
import quanlyphongmachcosoyte.view.GiaoDienCoSoYTe;

public class CoSoYTe {
    private static QL_BenhNhan quanLyBenhNhan = new QL_BenhNhan();
    private static QL_DichVu quanLyDichVu = new QL_DichVu();
    private static QL_GiaoDich quanLyGiaoDich = new QL_GiaoDich();
    private static QL_NhanVien quanLyNhanVien = new QL_NhanVien();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Đọc dữ liệu trước khi khởi tạo giao diện
        docTatCaDuLieu();

        SwingUtilities.invokeLater(() -> {
            // Khởi tạo giao diện và truyền Controllers vào Constructor
            GiaoDienCoSoYTe app = new GiaoDienCoSoYTe(
                    quanLyBenhNhan,
                    quanLyNhanVien,
                    quanLyDichVu,
                    quanLyGiaoDich);
            app.setVisible(true);
        });
    }

    public static void docTatCaDuLieu() {
        System.out.println("Dang khoi dong... Doc du lieu tu file.");
        quanLyBenhNhan.docDuLieuTuTapTin();
        quanLyDichVu.docDuLieuTuTapTin();
        quanLyNhanVien.docDuLieuTuTapTin();
        quanLyGiaoDich.docDuLieuTuTapTin(quanLyDichVu);

        if (quanLyBenhNhan.kiemTraRong() && quanLyDichVu.kiemTraRong() && quanLyNhanVien.kiemTraRong()
                && quanLyGiaoDich.layDanhSachHoaDon().isEmpty()) {
            System.out.println("Khong tim thay file du lieu. Dang tao du lieu mau...");
            themDuLieuMau();
            luuTatCaDuLieu();
        } else {
            System.out.println("Doc du lieu thanh cong!");
        }
    }

    public static void luuTatCaDuLieu() {
        System.out.println("Dang luu du lieu ra file...");
        quanLyBenhNhan.luuVaoTapTin();
        quanLyDichVu.luuVaoTapTin();
        quanLyNhanVien.luuVaoTapTin();
        quanLyGiaoDich.luuVaoTapTin();
    }

    public static void themDuLieuMau() {
        System.out.println("Dang them du lieu mau...");
        quanLyBenhNhan.themBN(new BenhNhan("CCCD001", "Nguyen Van A", "10/10/2000", "Nam", "0123456789",
                "BN001", "10/11/2025", "Cam cum"));
        quanLyBenhNhan.themBN(new BenhNhan("CCCD002", "Thi Be B", "12/01/1995", "Nu", "0987654321",
                "BN002", "11/11/2025", "Dau bung"));

        quanLyDichVu.themDV(new Thuoc("T001", "Paracetamol", 15000, "Vien", 1.0, 'B'));
        quanLyDichVu.themDV(new Thuoc("T002", "Berberin", 20000, "Vien", 1.0, 'C'));

        quanLyDichVu.themDV(new XetNghiem("XN001", "Xet nghiem mau", 'A', 150000, "May ly tam", "Mau"));
        quanLyDichVu.themDV(new XetNghiem("XN002", "Sieu am o bung", 'B', 200000, "May sieu am", "Hinh anh"));

        quanLyNhanVien.themNV(new BacSi("CCCD100", "Nguyen Van Bac Si", "01/01/1980", "Nam", "011111111", "BS001",
                "Truong Khoa", 5.0, 5000000));
        quanLyNhanVien.themNV(new DieuDuong("CCCD101", "Tran Thi Dieu Duong", "02/02/1990", "Nu", "022222222", "DD001",
                "Dieu duong truong", 3.5, 2000000, 10));
        quanLyNhanVien.themNV(new KiThuatVien("CCCD102", "Le Van KTV", "03/03/1995", "Nam", "033333333", "KTV001",
                "KTV Xet nghiem", 3.0, 1000000, 500000, 20));

        HoaDon hd1 = new HoaDon("HD001", "BN001", "12/11/2025");
        hd1.themDichVu(quanLyDichVu.timKiemDV("T001"));
        hd1.themDichVu(quanLyDichVu.timKiemDV("XN001"));
        quanLyGiaoDich.themHoaDon(hd1);

        HoaDon hd2 = new HoaDon("HD002", "BN002", "13/11/2025");
        hd2.themDichVu(quanLyDichVu.timKiemDV("T002"));
        quanLyGiaoDich.themHoaDon(hd2);
    }
}