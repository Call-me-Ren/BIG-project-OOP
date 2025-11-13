package quanlyphongmachcosoyte.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import quanlyphongmachcosoyte.CoSoYTe; // CẦN IMPORT CLASS CoSoYTe ĐỂ GỌI HÀM LƯU
import quanlyphongmachcosoyte.controller.QL_BenhNhan;
import quanlyphongmachcosoyte.controller.QL_DichVu;
import quanlyphongmachcosoyte.controller.QL_GiaoDich;
import quanlyphongmachcosoyte.controller.QL_NhanVien;
import quanlyphongmachcosoyte.model.BacSi;
import quanlyphongmachcosoyte.model.BenhNhan;
import quanlyphongmachcosoyte.model.DichVuYTe;
import quanlyphongmachcosoyte.model.DieuDuong;
import quanlyphongmachcosoyte.model.HoaDon;
import quanlyphongmachcosoyte.model.KiThuatVien;
import quanlyphongmachcosoyte.model.NhanVien;
import quanlyphongmachcosoyte.model.Thuoc;

public class GiaoDienCoSoYTe extends JFrame {

    // 1. XÓA KHAI BÁO TẠO ĐỐI TƯỢNG (CHỈ GIỮ LẠI KHAI BÁO)
    private QL_BenhNhan quanLyBenhNhan;
    private QL_NhanVien quanLyNhanVien;
    private QL_DichVu quanLyDichVu;
    private QL_GiaoDich quanLyGiaoDich;

    private JTabbedPane khungTap;

    private DefaultTableModel moHinhBangBenhNhan;
    private JTable bangBenhNhan;

    private DefaultTableModel moHinhBangNhanVien;
    private JTable bangNhanVien;
    private JTextField oNhapNV_MaID, oNhapNV_HoTen, oNhapNV_NgaySinh, oNhapNV_GioiTinh, oNhapNV_SDT;
    private JTextField oNhapNV_MaNV, oNhapNV_ChucVu, oNhapNV_HeSoLuong;
    private JComboBox<String> hopChonLoaiNV;

    private DefaultTableModel moHinhBangDichVu, moHinhBangChiTietHD;
    private JTextField oNhapHDBN, oNhapHDNgayLap, oNhapHDMaHD;

    // 2. CONSTRUCTOR MỚI: NHẬN CÁC CONTROLLER TỪ CLASS CoSoYTe
    public GiaoDienCoSoYTe(
            QL_BenhNhan qlBenhNhan,
            QL_NhanVien qlNhanVien,
            QL_DichVu qlDichVu,
            QL_GiaoDich qlGiaoDich) {
        // Gán Controllers
        this.quanLyBenhNhan = qlBenhNhan;
        this.quanLyNhanVien = qlNhanVien;
        this.quanLyDichVu = qlDichVu;
        this.quanLyGiaoDich = qlGiaoDich;

        // XÓA TẤT CẢ CÁC LỆNH .docDuLieuTuTapTin() Ở ĐÂY! (vì đã thực hiện trong
        // CoSoYTe.main)

        setTitle("He Thong Quan Ly Co So Y Te");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                luuDuLieuVaThoat();
            }
        });

        khungTap = new JTabbedPane();

        JPanel bangBenhNhan = taoBangQuanLyBenhNhan();
        khungTap.addTab("Quan Ly Benh Nhan", null, bangBenhNhan, "Quan ly thong tin benh nhan");

        JPanel bangNhanVien = taoBangQuanLyNhanVien();
        khungTap.addTab("Quan Ly Nhan Vien", null, bangNhanVien, "Quan ly nhan su");

        JPanel bangGiaoDich = taoBangQuanLyGiaoDich();
        khungTap.addTab("Quan Ly Giao Dich", null, bangGiaoDich, "Quan ly va thong ke hoa don");

        JPanel bangLapHoaDon = taoBangLapHoaDon();
        khungTap.addTab("Lap Hoa Don", null, bangLapHoaDon, "Tao hoa don thanh toan moi");

        add(khungTap);
    }

    private JPanel taoBangQuanLyBenhNhan() {
        JPanel bang = new JPanel(new BorderLayout());

        JPanel bangTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField oNhapTimKiemBN = new JTextField(15);
        JButton nutTimBN = new JButton("Tim theo Ma BN");
        bangTimKiem.add(new JLabel("Tim kiem: "));
        bangTimKiem.add(oNhapTimKiemBN);
        bangTimKiem.add(nutTimBN);
        bang.add(bangTimKiem, BorderLayout.NORTH);

        String[] tenCot = { "Ma ID", "Ho Ten", "Ngay Sinh", "Gioi Tinh", "SDT", "Ma BN", "Ngay Vao", "Benh Ly" };
        moHinhBangBenhNhan = new DefaultTableModel(tenCot, 0);
        bangBenhNhan = new JTable(moHinhBangBenhNhan);

        napDuLieuBenhNhanLenBang(quanLyBenhNhan.layDanhSachBN());
        bang.add(new JScrollPane(bangBenhNhan), BorderLayout.CENTER);

        JPanel bangNhapLieu = new JPanel(new GridLayout(4, 4, 10, 10));
        bangNhapLieu.setBorder(BorderFactory.createTitledBorder("Thong Tin Benh Nhan"));

        JTextField oNhapMaID = new JTextField();
        JTextField oNhapHoTen = new JTextField();
        JTextField oNhapNgaySinh = new JTextField();
        JTextField oNhapGioiTinh = new JTextField();
        JTextField oNhapSDT = new JTextField();
        JTextField oNhapMaBN = new JTextField();
        JTextField oNhapNgayVao = new JTextField();
        JTextField oNhapBenhLy = new JTextField();

        bangNhapLieu.add(new JLabel("Ma ID (CCCD):"));
        bangNhapLieu.add(oNhapMaID);
        bangNhapLieu.add(new JLabel("Ho Ten:"));
        bangNhapLieu.add(oNhapHoTen);
        bangNhapLieu.add(new JLabel("Ngay Sinh:"));
        bangNhapLieu.add(oNhapNgaySinh);
        bangNhapLieu.add(new JLabel("Gioi Tinh:"));
        bangNhapLieu.add(oNhapGioiTinh);
        bangNhapLieu.add(new JLabel("SDT:"));
        bangNhapLieu.add(oNhapSDT);
        bangNhapLieu.add(new JLabel("Ma BN:"));
        bangNhapLieu.add(oNhapMaBN);
        bangNhapLieu.add(new JLabel("Ngay Vao:"));
        bangNhapLieu.add(oNhapNgayVao);
        bangNhapLieu.add(new JLabel("Benh Ly:"));
        bangNhapLieu.add(oNhapBenhLy);

        bangBenhNhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int dong = bangBenhNhan.getSelectedRow();
                if (dong >= 0) {
                    oNhapMaID.setText(moHinhBangBenhNhan.getValueAt(dong, 0).toString());
                    oNhapHoTen.setText(moHinhBangBenhNhan.getValueAt(dong, 1).toString());
                    oNhapNgaySinh.setText(moHinhBangBenhNhan.getValueAt(dong, 2).toString());
                    oNhapGioiTinh.setText(moHinhBangBenhNhan.getValueAt(dong, 3).toString());
                    oNhapSDT.setText(moHinhBangBenhNhan.getValueAt(dong, 4).toString());
                    oNhapMaBN.setText(moHinhBangBenhNhan.getValueAt(dong, 5).toString());
                    oNhapNgayVao.setText(moHinhBangBenhNhan.getValueAt(dong, 6).toString());
                    oNhapBenhLy.setText(moHinhBangBenhNhan.getValueAt(dong, 7).toString());
                }
            }
        });

        JPanel bangNut = new JPanel(new FlowLayout());
        JButton nutThem = new JButton("Them Moi");
        JButton nutSua = new JButton("Sua Thong Tin");
        JButton nutXoa = new JButton("Xoa Chon");
        JButton nutLamMoi = new JButton("Lam Moi / Hien Tat Ca");

        bangNut.add(nutThem);
        bangNut.add(nutSua);
        bangNut.add(nutXoa);
        bangNut.add(nutLamMoi);

        JPanel bangDuoi = new JPanel(new BorderLayout());
        bangDuoi.add(bangNhapLieu, BorderLayout.CENTER);
        bangDuoi.add(bangNut, BorderLayout.SOUTH);
        bang.add(bangDuoi, BorderLayout.SOUTH);

        nutTimBN.addActionListener(e -> {
            String tuKhoa = oNhapTimKiemBN.getText().trim().toLowerCase();
            if (tuKhoa.isEmpty())
                return;
            List<BenhNhan> ketQua = new ArrayList<>();
            for (BenhNhan bn : quanLyBenhNhan.layDanhSachBN()) {
                if (bn.layMaBN().toLowerCase().contains(tuKhoa))
                    ketQua.add(bn);
            }
            napDuLieuBenhNhanLenBang(ketQua);
        });

        nutThem.addActionListener(e -> {
            try {
                if (oNhapMaBN.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Chua nhap Ma BN!");
                    return;
                }
                BenhNhan bn = new BenhNhan(
                        oNhapMaID.getText(), oNhapHoTen.getText(), oNhapNgaySinh.getText(),
                        oNhapGioiTinh.getText(), oNhapSDT.getText(), oNhapMaBN.getText(),
                        oNhapNgayVao.getText(), oNhapBenhLy.getText());
                quanLyBenhNhan.themBN(bn);
                napDuLieuBenhNhanLenBang(quanLyBenhNhan.layDanhSachBN());
                JOptionPane.showMessageDialog(this, "Da them!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Loi: " + ex.getMessage());
            }
        });

        nutSua.addActionListener(e -> {
            String maBN = oNhapMaBN.getText();
            BenhNhan bn = quanLyBenhNhan.timKiemBN(maBN);
            if (bn != null) {
                bn.thietLapBenhLy(oNhapBenhLy.getText());
                napDuLieuBenhNhanLenBang(quanLyBenhNhan.layDanhSachBN());
                JOptionPane.showMessageDialog(this, "Da cap nhat thong tin cho BN: " + maBN);
            } else {
                JOptionPane.showMessageDialog(this, "Khong tim thay BN co ma: " + maBN);
            }
        });

        nutXoa.addActionListener(e -> {
            int dongDuocChon = bangBenhNhan.getSelectedRow();
            if (dongDuocChon != -1) {
                String maBN = (String) moHinhBangBenhNhan.getValueAt(dongDuocChon, 5);
                if (JOptionPane.showConfirmDialog(this, "Xoa BN: " + maBN + "?") == JOptionPane.YES_OPTION) {
                    quanLyBenhNhan.xoaBN(maBN);
                    napDuLieuBenhNhanLenBang(quanLyBenhNhan.layDanhSachBN());
                }
            } else
                JOptionPane.showMessageDialog(this, "Chon dong can xoa!");
        });

        nutLamMoi.addActionListener(e -> {
            oNhapTimKiemBN.setText("");
            oNhapMaID.setText("");
            oNhapHoTen.setText("");
            oNhapMaBN.setText("");
            oNhapBenhLy.setText("");
            napDuLieuBenhNhanLenBang(quanLyBenhNhan.layDanhSachBN());
        });

        return bang;
    }

    private void napDuLieuBenhNhanLenBang(List<BenhNhan> danhSachDuLieu) {
        moHinhBangBenhNhan.setRowCount(0);
        for (BenhNhan bn : danhSachDuLieu) {
            moHinhBangBenhNhan.addRow(new Object[] {
                    bn.layMaID(), bn.layHoTen(), bn.layNgaySinh(), bn.layGioiTinh(), bn.laySoDienThoai(),
                    bn.layMaBN(), bn.layNgayVaoVien(), bn.layBenhLy()
            });
        }
    }

    private JPanel taoBangQuanLyNhanVien() {
        JPanel bang = new JPanel(new BorderLayout());

        JPanel bangTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField oNhapTimKiemNV = new JTextField(15);
        JButton nutTimNV = new JButton("Tim theo Ma NV");
        bangTimKiem.add(new JLabel("Tim kiem: "));
        bangTimKiem.add(oNhapTimKiemNV);
        bangTimKiem.add(nutTimNV);
        bang.add(bangTimKiem, BorderLayout.NORTH);

        String[] tenCot = { "Loai NV", "Ma NV", "Ho Ten", "Chuc Vu", "He So Luong", "Luong Thuc Linh" };
        moHinhBangNhanVien = new DefaultTableModel(tenCot, 0);
        bangNhanVien = new JTable(moHinhBangNhanVien);

        napDuLieuNhanVienLenBang(quanLyNhanVien.layDanhSachNV());
        bang.add(new JScrollPane(bangNhanVien), BorderLayout.CENTER);

        JPanel bangNhapLieu = new JPanel(new GridLayout(5, 4, 10, 10));
        bangNhapLieu.setBorder(BorderFactory.createTitledBorder("Thong Tin Nhan Vien"));

        oNhapNV_MaID = new JTextField();
        oNhapNV_HoTen = new JTextField();
        oNhapNV_NgaySinh = new JTextField();
        oNhapNV_GioiTinh = new JTextField();
        oNhapNV_SDT = new JTextField();
        oNhapNV_MaNV = new JTextField();
        oNhapNV_ChucVu = new JTextField();
        oNhapNV_HeSoLuong = new JTextField();

        String[] loaiNV = { "Bac Si", "Dieu Duong", "Ky Thuat Vien" };
        hopChonLoaiNV = new JComboBox<>(loaiNV);

        bangNhapLieu.add(new JLabel("Loai Nhan Vien:"));
        bangNhapLieu.add(hopChonLoaiNV);
        bangNhapLieu.add(new JLabel("Ma NV:"));
        bangNhapLieu.add(oNhapNV_MaNV);
        bangNhapLieu.add(new JLabel("Ho Ten:"));
        bangNhapLieu.add(oNhapNV_HoTen);
        bangNhapLieu.add(new JLabel("Chuc Vu:"));
        bangNhapLieu.add(oNhapNV_ChucVu);
        bangNhapLieu.add(new JLabel("He So Luong:"));
        bangNhapLieu.add(oNhapNV_HeSoLuong);
        bangNhapLieu.add(new JLabel("Ma ID (CCCD):"));
        bangNhapLieu.add(oNhapNV_MaID);
        bangNhapLieu.add(new JLabel("Ngay Sinh:"));
        bangNhapLieu.add(oNhapNV_NgaySinh);
        bangNhapLieu.add(new JLabel("Gioi Tinh:"));
        bangNhapLieu.add(oNhapNV_GioiTinh);
        bangNhapLieu.add(new JLabel("So Dien Thoai:"));
        bangNhapLieu.add(oNhapNV_SDT);
        bangNhapLieu.add(new JLabel("(Luu y: Cac chi so phu mac dinh)"));

        JPanel bangNut = new JPanel(new FlowLayout());
        JButton nutThemNV = new JButton("Them NV Moi");
        JButton nutSuaNV = new JButton("Sua NV Dang Chon");
        JButton nutXoaNV = new JButton("Xoa NV Dang Chon");
        JButton nutLamMoiNV = new JButton("Lam Moi");

        bangNut.add(nutThemNV);
        bangNut.add(nutSuaNV);
        bangNut.add(nutXoaNV);
        bangNut.add(nutLamMoiNV);

        JPanel bangDuoi = new JPanel(new BorderLayout());
        bangDuoi.add(bangNhapLieu, BorderLayout.CENTER);
        bangDuoi.add(bangNut, BorderLayout.SOUTH);
        bang.add(bangDuoi, BorderLayout.SOUTH);

        bangNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int dong = bangNhanVien.getSelectedRow();
                if (dong >= 0) {
                    String maNV = moHinhBangNhanVien.getValueAt(dong, 1).toString();
                    NhanVien nv = quanLyNhanVien.timKiemNV(maNV);
                    if (nv != null) {
                        oNhapNV_MaNV.setText(nv.layMaNV());
                        oNhapNV_HoTen.setText(nv.layHoTen());
                        oNhapNV_ChucVu.setText(nv.layChucVu());
                        oNhapNV_HeSoLuong.setText(String.valueOf(nv.layHeSoLuong()));
                        oNhapNV_MaID.setText(nv.layMaID());
                        oNhapNV_NgaySinh.setText(nv.layNgaySinh());
                        oNhapNV_GioiTinh.setText(nv.layGioiTinh());
                        oNhapNV_SDT.setText(nv.laySoDienThoai());

                        if (nv instanceof BacSi)
                            hopChonLoaiNV.setSelectedItem("Bac Si");
                        else if (nv instanceof DieuDuong)
                            hopChonLoaiNV.setSelectedItem("Dieu Duong");
                        else
                            hopChonLoaiNV.setSelectedItem("Ky Thuat Vien");
                    }
                }
            }
        });

        nutTimNV.addActionListener(e -> {
            String tuKhoa = oNhapTimKiemNV.getText().trim().toLowerCase();
            if (tuKhoa.isEmpty())
                return;
            List<NhanVien> ketQua = new ArrayList<>();
            for (NhanVien nv : quanLyNhanVien.layDanhSachNV()) {
                if (nv.layMaNV().toLowerCase().contains(tuKhoa))
                    ketQua.add(nv);
            }
            napDuLieuNhanVienLenBang(ketQua);
        });

        nutThemNV.addActionListener(e -> {
            try {
                if (oNhapNV_MaNV.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Chua nhap Ma NV!");
                    return;
                }
                double hsl = Double.parseDouble(oNhapNV_HeSoLuong.getText());
                String loai = (String) hopChonLoaiNV.getSelectedItem();

                NhanVien nvMoi = null;
                if ("Bac Si".equals(loai)) {
                    nvMoi = new BacSi(oNhapNV_MaID.getText(), oNhapNV_HoTen.getText(), oNhapNV_NgaySinh.getText(),
                            oNhapNV_GioiTinh.getText(), oNhapNV_SDT.getText(), oNhapNV_MaNV.getText(),
                            oNhapNV_ChucVu.getText(), hsl, 500000);
                } else if ("Dieu Duong".equals(loai)) {
                    nvMoi = new DieuDuong(oNhapNV_MaID.getText(), oNhapNV_HoTen.getText(), oNhapNV_NgaySinh.getText(),
                            oNhapNV_GioiTinh.getText(), oNhapNV_SDT.getText(), oNhapNV_MaNV.getText(),
                            oNhapNV_ChucVu.getText(), hsl, 200000, 0);
                } else {
                    nvMoi = new KiThuatVien(oNhapNV_MaID.getText(), oNhapNV_HoTen.getText(), oNhapNV_NgaySinh.getText(),
                            oNhapNV_GioiTinh.getText(), oNhapNV_SDT.getText(), oNhapNV_MaNV.getText(),
                            oNhapNV_ChucVu.getText(), hsl, 300000, 500000, 0);
                }

                quanLyNhanVien.themNV(nvMoi);
                napDuLieuNhanVienLenBang(quanLyNhanVien.layDanhSachNV());
                JOptionPane.showMessageDialog(this, "Them thanh cong!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Loi nhap lieu (HSL phai la so): " + ex.getMessage());
            }
        });

        nutSuaNV.addActionListener(e -> {
            String maNV = oNhapNV_MaNV.getText();
            NhanVien nv = quanLyNhanVien.timKiemNV(maNV);
            if (nv != null) {
                try {
                    nv.thietLapHoTen(oNhapNV_HoTen.getText());
                    nv.thietLapChucVu(oNhapNV_ChucVu.getText());
                    nv.thietLapHeSoLuong(Double.parseDouble(oNhapNV_HeSoLuong.getText()));
                    nv.thietLapSoDienThoai(oNhapNV_SDT.getText());

                    napDuLieuNhanVienLenBang(quanLyNhanVien.layDanhSachNV());
                    JOptionPane.showMessageDialog(this, "Da cap nhat thong tin NV: " + maNV);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Loi dinh dang so: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Khong tim thay NV co ma: " + maNV + " de sua!");
            }
        });

        nutXoaNV.addActionListener(e -> {
            int dong = bangNhanVien.getSelectedRow();
            if (dong >= 0) {
                String maNV = (String) moHinhBangNhanVien.getValueAt(dong, 1);
                if (JOptionPane.showConfirmDialog(this, "Xoa NV: " + maNV + "?") == JOptionPane.YES_OPTION) {
                    quanLyNhanVien.xoaNV(maNV);
                    napDuLieuNhanVienLenBang(quanLyNhanVien.layDanhSachNV());
                }
            } else
                JOptionPane.showMessageDialog(this, "Chon dong can xoa!");
        });

        nutLamMoiNV.addActionListener(e -> {
            oNhapTimKiemNV.setText("");
            oNhapNV_MaNV.setText("");
            oNhapNV_HoTen.setText("");
            oNhapNV_ChucVu.setText("");
            oNhapNV_HeSoLuong.setText("");
            napDuLieuNhanVienLenBang(quanLyNhanVien.layDanhSachNV());
        });

        return bang;
    }

    private void napDuLieuNhanVienLenBang(List<NhanVien> danhSachDuLieu) {
        moHinhBangNhanVien.setRowCount(0);
        for (NhanVien nv : danhSachDuLieu) {
            String loai = "Nhan Vien";
            if (nv instanceof BacSi)
                loai = "Bac Si";
            else if (nv instanceof DieuDuong)
                loai = "Dieu Duong";
            else if (nv instanceof KiThuatVien)
                loai = "Ky Thuat Vien";

            moHinhBangNhanVien.addRow(new Object[] {
                    loai, nv.layMaNV(), nv.layHoTen(), nv.layChucVu(), nv.layHeSoLuong(),
                    String.format("%.0f", nv.tinhLuong())
            });
        }
    }

    private JPanel taoBangQuanLyGiaoDich() {
        JPanel bang = new JPanel(new BorderLayout());

        JPanel bangTren = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField oNhapTimKiemHD = new JTextField(15);
        JButton nutTimHD = new JButton("Tim theo Ma HD/BN");
        JButton nutTinhDoanhThu = new JButton("Tinh Tong Doanh Thu");

        bangTren.add(new JLabel("Tim kiem: "));
        bangTren.add(oNhapTimKiemHD);
        bangTren.add(nutTimHD);
        bangTren.add(nutTinhDoanhThu);
        bang.add(bangTren, BorderLayout.NORTH);

        String[] tenCot = { "Ma Hoa Don", "Ma BN", "Ngay Lap", "Tong Tien" };
        DefaultTableModel moHinhBangHoaDon = new DefaultTableModel(tenCot, 0);
        JTable bangHoaDon = new JTable(moHinhBangHoaDon);

        napDuLieuHoaDonLenBang(moHinhBangHoaDon, quanLyGiaoDich.layDanhSachHoaDon());
        bang.add(new JScrollPane(bangHoaDon), BorderLayout.CENTER);

        JTextArea vungHienThiChiTiet = new JTextArea(5, 20);
        vungHienThiChiTiet.setBorder(BorderFactory.createTitledBorder("Chi Tiet Hoa Don"));
        vungHienThiChiTiet.setEditable(false);
        vungHienThiChiTiet.setFont(new Font("Monospaced", Font.PLAIN, 12));

        bangHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int dong = bangHoaDon.getSelectedRow();
                if (dong >= 0) {
                    String maHD = (String) moHinhBangHoaDon.getValueAt(dong, 0);
                    HoaDon hd = quanLyGiaoDich.timKiemHD(maHD);
                    if (hd != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Ma HD:        ").append(hd.layMaHoaDon()).append("\n");
                        sb.append("Ma BN:        ").append(hd.layMaBN()).append("\n");
                        sb.append("Ngay Lap:     ").append(hd.layNgayLap()).append("\n");
                        sb.append("--- Dich Vu ---\n");
                        for (DichVuYTe dv : hd.layDanhSachDichVuSuDung()) {
                            sb.append("- ").append(dv.layTenDV()).append(": ")
                                    .append(String.format("%,.0f", dv.tinhChiPhi())).append(" VND\n");
                        }
                        sb.append("----------------\n");
                        sb.append("TONG CONG:    ").append(String.format("%,.0f", hd.tinhTongTien())).append(" VND");
                        vungHienThiChiTiet.setText(sb.toString());
                    }
                }
            }
        });

        JPanel bangDuoi = new JPanel(new BorderLayout());
        bangDuoi.add(new JScrollPane(vungHienThiChiTiet), BorderLayout.CENTER);

        JPanel bangNut = new JPanel(new FlowLayout());
        JButton nutXoaHD = new JButton("Xoa Hoa Don Dang Chon");
        JButton nutLamMoiHD = new JButton("Lam Moi Danh Sach");

        bangNut.add(nutXoaHD);
        bangNut.add(nutLamMoiHD);
        bangDuoi.add(bangNut, BorderLayout.SOUTH);
        bang.add(bangDuoi, BorderLayout.SOUTH);

        nutTimHD.addActionListener(e -> {
            String tuKhoa = oNhapTimKiemHD.getText().trim().toLowerCase();
            if (tuKhoa.isEmpty()) {
                napDuLieuHoaDonLenBang(moHinhBangHoaDon, quanLyGiaoDich.layDanhSachHoaDon());
                return;
            }
            List<HoaDon> ketQua = new ArrayList<>();
            for (HoaDon hd : quanLyGiaoDich.layDanhSachHoaDon()) {
                if (hd.layMaHoaDon().toLowerCase().contains(tuKhoa) || hd.layMaBN().toLowerCase().contains(tuKhoa)) {
                    ketQua.add(hd);
                }
            }
            napDuLieuHoaDonLenBang(moHinhBangHoaDon, ketQua);
        });

        nutTinhDoanhThu.addActionListener(e -> {
            double tongDT = quanLyGiaoDich.tinhTongDoanhThu();
            JOptionPane.showMessageDialog(this,
                    "TONG DOANH THU HE THONG: " + String.format("%,.0f", tongDT) + " VND",
                    "Thong Ke Doanh Thu",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        nutXoaHD.addActionListener(e -> {
            int dongDuocChon = bangHoaDon.getSelectedRow();
            if (dongDuocChon != -1) {
                String maHD = (String) moHinhBangHoaDon.getValueAt(dongDuocChon, 0);
                if (JOptionPane.showConfirmDialog(this, "Xoa Hoa Don: " + maHD + "?") == JOptionPane.YES_OPTION) {
                    quanLyGiaoDich.xoaHD(maHD);
                    napDuLieuHoaDonLenBang(moHinhBangHoaDon, quanLyGiaoDich.layDanhSachHoaDon());
                    vungHienThiChiTiet.setText("");
                }
            } else
                JOptionPane.showMessageDialog(this, "Chon dong can xoa!");
        });

        nutLamMoiHD.addActionListener(e -> {
            oNhapTimKiemHD.setText("");
            vungHienThiChiTiet.setText("");
            napDuLieuHoaDonLenBang(moHinhBangHoaDon, quanLyGiaoDich.layDanhSachHoaDon());
        });

        return bang;
    }

    private void napDuLieuHoaDonLenBang(DefaultTableModel moHinh, List<HoaDon> danhSachDuLieu) {
        moHinh.setRowCount(0);
        for (HoaDon hd : danhSachDuLieu) {
            moHinh.addRow(new Object[] {
                    hd.layMaHoaDon(),
                    hd.layMaBN(),
                    hd.layNgayLap(),
                    String.format("%,.0f", hd.tinhTongTien())
            });
        }
    }

    private JPanel taoBangLapHoaDon() {
        JPanel bang = new JPanel(new BorderLayout(10, 10));

        JPanel bangTren = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel bangThongTin = new JPanel(new GridLayout(5, 2, 5, 5));
        bangThongTin.setBorder(BorderFactory.createTitledBorder("Thong Tin Hoa Don"));

        oNhapHDMaHD = new JTextField();
        oNhapHDBN = new JTextField();
        oNhapHDNgayLap = new JTextField();
        JButton nutLapHD = new JButton("LAP HOA DON & THANH TOAN");
        JLabel nhanTongTien = new JLabel("Tong: 0 VND");
        nhanTongTien.setFont(new Font("Arial", Font.BOLD, 14));

        bangThongTin.add(new JLabel("Ma Hoa Don:"));
        bangThongTin.add(oNhapHDMaHD);
        bangThongTin.add(new JLabel("Ma BN:"));
        bangThongTin.add(oNhapHDBN);
        bangThongTin.add(new JLabel("Ngay Lap (DD/MM/YYYY):"));
        bangThongTin.add(oNhapHDNgayLap);
        bangThongTin.add(nhanTongTien);
        bangThongTin.add(nutLapHD);

        String[] cotChiTiet = { "Ma DV", "Ten Dich Vu", "Chi Phi" };
        moHinhBangChiTietHD = new DefaultTableModel(cotChiTiet, 0);
        JTable bangChiTietHD = new JTable(moHinhBangChiTietHD);

        bangTren.add(bangThongTin);
        bangTren.add(new JScrollPane(bangChiTietHD));

        bang.add(bangTren, BorderLayout.NORTH);

        JPanel bangGiua = new JPanel(new BorderLayout());
        bangGiua.setBorder(BorderFactory.createTitledBorder("Chon Dich Vu Y Te"));

        String[] cotDichVu = { "Ma DV", "Ten Dich Vu", "Gia Goc", "Loai" };
        moHinhBangDichVu = new DefaultTableModel(cotDichVu, 0);
        JTable bangDichVu = new JTable(moHinhBangDichVu);
        napDuLieuDichVuLenBang(quanLyDichVu.layDanhSachDV());

        JPanel bangNutDV = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton nutThemDV = new JButton("Them Dich Vu Dang Chon");
        JButton nutXoaDVChiTiet = new JButton("Xoa Dich Vu Da Chon");
        bangNutDV.add(nutThemDV);
        bangNutDV.add(nutXoaDVChiTiet);

        bangGiua.add(new JScrollPane(bangDichVu), BorderLayout.CENTER);
        bangGiua.add(bangNutDV, BorderLayout.SOUTH);

        bang.add(bangGiua, BorderLayout.CENTER);

        Runnable capNhatTongTien = () -> {
            double tong = 0;
            for (int i = 0; i < moHinhBangChiTietHD.getRowCount(); i++) {
                String chiPhiChuoi = (String) moHinhBangChiTietHD.getValueAt(i, 2);
                try {
                    // Chú ý: Phải loại bỏ dấu phẩy ngăn cách hàng nghìn nếu có
                    double chiPhi = Double.parseDouble(chiPhiChuoi.replace(",", ""));
                    tong += chiPhi;
                } catch (NumberFormatException ex) {
                }
            }
            nhanTongTien.setText("Tong: " + String.format("%,.0f", tong) + " VND");
        };

        nutThemDV.addActionListener(e -> {
            int dong = bangDichVu.getSelectedRow();
            if (dong >= 0) {
                String maDV = (String) moHinhBangDichVu.getValueAt(dong, 0);
                DichVuYTe dv = quanLyDichVu.timKiemDV(maDV);
                if (dv != null) {
                    double chiPhi = dv.tinhChiPhi();
                    moHinhBangChiTietHD.addRow(new Object[] { maDV, dv.layTenDV(), String.format("%,.0f", chiPhi) });
                    capNhatTongTien.run();
                }
            } else
                JOptionPane.showMessageDialog(this, "Chon dich vu can them!");
        });

        nutXoaDVChiTiet.addActionListener(e -> {
            int dong = bangChiTietHD.getSelectedRow();
            if (dong >= 0) {
                moHinhBangChiTietHD.removeRow(dong);
                capNhatTongTien.run();
            } else
                JOptionPane.showMessageDialog(this, "Chon dich vu trong danh sach chi tiet can xoa!");
        });

        nutLapHD.addActionListener(e -> {
            try {
                String maHD = oNhapHDMaHD.getText().trim();
                String maBN = oNhapHDBN.getText().trim();
                String ngayLap = oNhapHDNgayLap.getText().trim();

                if (maHD.isEmpty() || maBN.isEmpty() || ngayLap.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui long nhap day du Ma HD, Ma BN va Ngay Lap!");
                    return;
                }
                if (quanLyGiaoDich.timKiemHD(maHD) != null) {
                    JOptionPane.showMessageDialog(this, "Loi: Ma Hoa Don da ton tai!");
                    return;
                }
                if (quanLyBenhNhan.timKiemBN(maBN) == null) {
                    JOptionPane.showMessageDialog(this, "Loi: Ma BN khong ton tai!");
                    return;
                }
                if (moHinhBangChiTietHD.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Loi: Hoa don chua co dich vu nao!");
                    return;
                }

                HoaDon hdMoi = new HoaDon(maHD, maBN, ngayLap);

                for (int i = 0; i < moHinhBangChiTietHD.getRowCount(); i++) {
                    String maDV = (String) moHinhBangChiTietHD.getValueAt(i, 0);
                    DichVuYTe dv = quanLyDichVu.timKiemDV(maDV);
                    if (dv != null) {
                        hdMoi.themDichVu(dv);
                    }
                }

                quanLyGiaoDich.themHoaDon(hdMoi);

                JOptionPane.showMessageDialog(this,
                        "Lap Hoa Don thanh cong! Tong tien: " + String.format("%,.0f", hdMoi.tinhTongTien()) + " VND");

                lamMoiFormLapHoaDon(nhanTongTien);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Loi khi lap hoa don: " + ex.getMessage());
            }
        });

        return bang;
    }

    private void napDuLieuDichVuLenBang(List<DichVuYTe> danhSachDuLieu) {
        moHinhBangDichVu.setRowCount(0);
        for (DichVuYTe dv : danhSachDuLieu) {
            String loaiDV = (dv instanceof Thuoc) ? "Thuoc" : "Xet Nghiem";
            moHinhBangDichVu.addRow(new Object[] {
                    dv.layMaDV(), dv.layTenDV(), String.format("%,.0f", dv.layGiaTien()), loaiDV
            });
        }
    }

    private void lamMoiFormLapHoaDon(JLabel nhanTongTien) {
        oNhapHDMaHD.setText("");
        oNhapHDBN.setText("");
        oNhapHDNgayLap.setText("");
        moHinhBangChiTietHD.setRowCount(0);
        nhanTongTien.setText("Tong: 0 VND");
        khungTap.setSelectedIndex(2);
    }

    private void luuDuLieuVaThoat() {
        int xacNhan = JOptionPane.showConfirmDialog(this,
                "Ban co muon luu du lieu va thoat khong?",
                "Thoat", JOptionPane.YES_NO_OPTION);

        if (xacNhan == JOptionPane.YES_OPTION) {
            // SỬA ĐỔI: GỌI HÀM LƯU TỪ CLASS CoSoYTe (Nơi quản lý dữ liệu gốc)
            quanlyphongmachcosoyte.CoSoYTe.luuTatCaDuLieu();
            System.exit(0);
        }
    }

    // XÓA HÀM main() CŨ Ở ĐÂY! (vì đã chuyển sang CoSoYTe.java)
}