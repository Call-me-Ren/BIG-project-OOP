package quanlyphongmachcosoyte;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GiaoDienYTe extends JFrame {

    // Khởi tạo các đối tượng quản lý logic
    private QL_BenhNhan qlBenhNhan = new QL_BenhNhan();
    private QL_NhanVien qlNhanVien = new QL_NhanVien();
    private QL_DichVu qlDichVu = new QL_DichVu();

    // Các thành phần giao diện chung
    private JTabbedPane tabbedPane;
    
    // Thành phần cho Tab Bệnh Nhân
    private DefaultTableModel tableModelBenhNhan;
    private JTable tableBenhNhan;
    
    // Thành phần cho Tab Nhân Viên
    private DefaultTableModel tableModelNhanVien;
    private JTable tableNhanVien;
    // Khai báo các ô nhập liệu Nhân Viên để dùng chung trong các nút
    private JTextField txtNV_MaID, txtNV_HoTen, txtNV_NgaySinh, txtNV_GioiTinh, txtNV_SDT;
    private JTextField txtNV_MaNV, txtNV_ChucVu, txtNV_HeSoLuong;
    private JComboBox<String> cbLoaiNV;

    public GiaoDienYTe() {
        // Load dữ liệu từ file khi mở ứng dụng
        qlBenhNhan.docTuFile();
        qlNhanVien.docTuFile();
        qlDichVu.docTuFile();

        // Cấu hình cửa sổ chính
        setTitle("Hệ Thống Quản Lý Cơ Sở Y Tế");
        setSize(1200, 700);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 

        // Sự kiện khi tắt phần mềm -> Lưu file
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                luuDuLieuVaThoat();
            }
        });

        // Tạo TabbedPane
        tabbedPane = new JTabbedPane();

        // --- TAB 1: QUẢN LÝ BỆNH NHÂN ---
        JPanel panelBenhNhan = taoPanelBenhNhan();
        tabbedPane.addTab("Quản Lý Bệnh Nhân", null, panelBenhNhan, "Quản lý thông tin bệnh nhân");

        // --- TAB 2: QUẢN LÝ NHÂN VIÊN (Đã nâng cấp) ---
        JPanel panelNhanVien = taoPanelNhanVien();
        tabbedPane.addTab("Quản Lý Nhân Viên", null, panelNhanVien, "Quản lý nhân sự");

        // Thêm Tab vào cửa sổ
        add(tabbedPane);
    }

    // ========================================================================
    // MODULE 1: BỆNH NHÂN
    // ========================================================================
    private JPanel taoPanelBenhNhan() {
        JPanel panel = new JPanel(new BorderLayout());

        // --- PHẦN TÌM KIẾM ---
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField txtTimKiemBN = new JTextField(15);
        JButton btnTimBN = new JButton("Tìm theo Mã BN");
        searchPanel.add(new JLabel("Tìm kiếm: "));
        searchPanel.add(txtTimKiemBN);
        searchPanel.add(btnTimBN);
        panel.add(searchPanel, BorderLayout.NORTH);

        // --- BẢNG HIỂN THỊ ---
        String[] columnNames = {"Mã ID", "Họ Tên", "Ngày Sinh", "Giới Tính", "SĐT", "Mã BN", "Ngày Vào", "Bệnh Lý"};
        tableModelBenhNhan = new DefaultTableModel(columnNames, 0);
        tableBenhNhan = new JTable(tableModelBenhNhan);
        
        loadDataBenhNhanLenBang(qlBenhNhan.getDanhSachBN()); 
        panel.add(new JScrollPane(tableBenhNhan), BorderLayout.CENTER);

        // --- FORM NHẬP LIỆU ---
        JPanel inputPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Bệnh Nhân"));

        JTextField txtMaID = new JTextField();
        JTextField txtHoTen = new JTextField();
        JTextField txtNgaySinh = new JTextField();
        JTextField txtGioiTinh = new JTextField();
        JTextField txtSDT = new JTextField();
        JTextField txtMaBN = new JTextField();
        JTextField txtNgayVao = new JTextField();
        JTextField txtBenhLy = new JTextField();

        inputPanel.add(new JLabel("Mã ID (CCCD):")); inputPanel.add(txtMaID);
        inputPanel.add(new JLabel("Họ Tên:")); inputPanel.add(txtHoTen);
        inputPanel.add(new JLabel("Ngày Sinh:")); inputPanel.add(txtNgaySinh);
        inputPanel.add(new JLabel("Giới Tính:")); inputPanel.add(txtGioiTinh);
        inputPanel.add(new JLabel("SĐT:")); inputPanel.add(txtSDT);
        inputPanel.add(new JLabel("Mã BN:")); inputPanel.add(txtMaBN);
        inputPanel.add(new JLabel("Ngày Vào:")); inputPanel.add(txtNgayVao);
        inputPanel.add(new JLabel("Bệnh Lý:")); inputPanel.add(txtBenhLy);

        // --- SỰ KIỆN CLICK BẢNG BỆNH NHÂN (Đổ dữ liệu ngược lại ô nhập) ---
        tableBenhNhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableBenhNhan.getSelectedRow();
                if (row >= 0) {
                    txtMaID.setText(tableModelBenhNhan.getValueAt(row, 0).toString());
                    txtHoTen.setText(tableModelBenhNhan.getValueAt(row, 1).toString());
                    txtNgaySinh.setText(tableModelBenhNhan.getValueAt(row, 2).toString());
                    txtGioiTinh.setText(tableModelBenhNhan.getValueAt(row, 3).toString());
                    txtSDT.setText(tableModelBenhNhan.getValueAt(row, 4).toString());
                    txtMaBN.setText(tableModelBenhNhan.getValueAt(row, 5).toString());
                    txtNgayVao.setText(tableModelBenhNhan.getValueAt(row, 6).toString());
                    txtBenhLy.setText(tableModelBenhNhan.getValueAt(row, 7).toString());
                }
            }
        });

        // --- CÁC NÚT CHỨC NĂNG ---
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnThem = new JButton("Thêm Mới");
        JButton btnSua = new JButton("Sửa Thông Tin"); // Thêm nút Sửa cho BN
        JButton btnXoa = new JButton("Xóa Chọn");
        JButton btnLamMoi = new JButton("Làm Mới / Hiện Tất Cả");

        btnPanel.add(btnThem);
        btnPanel.add(btnSua);
        btnPanel.add(btnXoa);
        btnPanel.add(btnLamMoi);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        bottomPanel.add(btnPanel, BorderLayout.SOUTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // --- XỬ LÝ SỰ KIỆN ---
        
        // Tìm kiếm
        btnTimBN.addActionListener(e -> {
            String tuKhoa = txtTimKiemBN.getText().trim().toLowerCase();
            if (tuKhoa.isEmpty()) return;
            List<BenhNhan> ketQua = new ArrayList<>();
            for (BenhNhan bn : qlBenhNhan.getDanhSachBN()) {
                if (bn.getMaBN().toLowerCase().contains(tuKhoa)) ketQua.add(bn);
            }
            loadDataBenhNhanLenBang(ketQua);
        });

        // Thêm
        btnThem.addActionListener(e -> {
            try {
                if(txtMaBN.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Chưa nhập Mã BN!"); return;
                }
                BenhNhan bn = new BenhNhan(
                        txtMaID.getText(), txtHoTen.getText(), txtNgaySinh.getText(),
                        txtGioiTinh.getText(), txtSDT.getText(), txtMaBN.getText(),
                        txtNgayVao.getText(), txtBenhLy.getText()
                );
                qlBenhNhan.themBN(bn);
                loadDataBenhNhanLenBang(qlBenhNhan.getDanhSachBN());
                JOptionPane.showMessageDialog(this, "Đã thêm!");
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage()); }
        });

        // Sửa
        btnSua.addActionListener(e -> {
             String maBN = txtMaBN.getText();
             BenhNhan bn = qlBenhNhan.timKiemBN(maBN);
             if (bn != null) {
                 bn.setBenhLy(txtBenhLy.getText());
                 // Cập nhật các trường khác nếu cần (cần setter bên BenhNhan)
                 loadDataBenhNhanLenBang(qlBenhNhan.getDanhSachBN());
                 JOptionPane.showMessageDialog(this, "Đã cập nhật thông tin cho BN: " + maBN);
             } else {
                 JOptionPane.showMessageDialog(this, "Không tìm thấy BN có mã: " + maBN);
             }
        });

        // Xóa
        btnXoa.addActionListener(e -> {
            int selectedRow = tableBenhNhan.getSelectedRow();
            if (selectedRow != -1) {
                String maBN = (String) tableModelBenhNhan.getValueAt(selectedRow, 5);
                if (JOptionPane.showConfirmDialog(this, "Xóa BN: " + maBN + "?") == JOptionPane.YES_OPTION) {
                    qlBenhNhan.xoaBN(maBN);
                    loadDataBenhNhanLenBang(qlBenhNhan.getDanhSachBN());
                }
            } else JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa!");
        });
        
        // Làm Mới
        btnLamMoi.addActionListener(e -> {
            txtTimKiemBN.setText("");
            txtMaID.setText(""); txtHoTen.setText(""); txtMaBN.setText(""); txtBenhLy.setText("");
            loadDataBenhNhanLenBang(qlBenhNhan.getDanhSachBN());
        });

        return panel;
    }

    private void loadDataBenhNhanLenBang(List<BenhNhan> listData) {
        tableModelBenhNhan.setRowCount(0); 
        for (BenhNhan bn : listData) {
            tableModelBenhNhan.addRow(new Object[]{
                bn.maID, bn.hoTen, bn.ngaySinh, bn.gioiTinh, bn.soDienThoai, 
                bn.getMaBN(), bn.getNgayVaoVien(), bn.getBenhLy()
            });
        }
    }

    // ========================================================================
    // MODULE 2: NHÂN VIÊN (ĐÃ NÂNG CẤP ĐẦY ĐỦ TÍNH NĂNG)
    // ========================================================================
    private JPanel taoPanelNhanVien() {
        JPanel panel = new JPanel(new BorderLayout());

        // 1. TÌM KIẾM
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField txtTimKiemNV = new JTextField(15);
        JButton btnTimNV = new JButton("Tìm theo Mã NV");
        searchPanel.add(new JLabel("Tìm kiếm: "));
        searchPanel.add(txtTimKiemNV);
        searchPanel.add(btnTimNV);
        panel.add(searchPanel, BorderLayout.NORTH);

        // 2. BẢNG HIỂN THỊ
        String[] columnNames = {"Loại NV", "Mã NV", "Họ Tên", "Chức Vụ", "Hệ Số Lương", "Lương Thực Lĩnh"};
        tableModelNhanVien = new DefaultTableModel(columnNames, 0);
        tableNhanVien = new JTable(tableModelNhanVien);
        
        loadDataNhanVienLenBang(qlNhanVien.getDanhSachNV());
        panel.add(new JScrollPane(tableNhanVien), BorderLayout.CENTER);
        
        // 3. FORM NHẬP LIỆU (GIỐNG BÊN BỆNH NHÂN)
        JPanel inputPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Nhân Viên"));

        // Khởi tạo các TextField
        txtNV_MaID = new JTextField();
        txtNV_HoTen = new JTextField();
        txtNV_NgaySinh = new JTextField();
        txtNV_GioiTinh = new JTextField();
        txtNV_SDT = new JTextField();
        txtNV_MaNV = new JTextField();
        txtNV_ChucVu = new JTextField();
        txtNV_HeSoLuong = new JTextField();
        
        // ComboBox để chọn loại nhân viên khi thêm
        String[] loaiNV = {"Bác Sĩ", "Điều Dưỡng", "Kỹ Thuật Viên"};
        cbLoaiNV = new JComboBox<>(loaiNV);

        inputPanel.add(new JLabel("Loại Nhân Viên:")); inputPanel.add(cbLoaiNV);
        inputPanel.add(new JLabel("Mã NV:")); inputPanel.add(txtNV_MaNV);
        inputPanel.add(new JLabel("Họ Tên:")); inputPanel.add(txtNV_HoTen);
        inputPanel.add(new JLabel("Chức Vụ:")); inputPanel.add(txtNV_ChucVu);
        inputPanel.add(new JLabel("Hệ Số Lương:")); inputPanel.add(txtNV_HeSoLuong);
        inputPanel.add(new JLabel("Mã ID (CCCD):")); inputPanel.add(txtNV_MaID);
        inputPanel.add(new JLabel("Ngày Sinh:")); inputPanel.add(txtNV_NgaySinh);
        inputPanel.add(new JLabel("Giới Tính:")); inputPanel.add(txtNV_GioiTinh);
        inputPanel.add(new JLabel("Số Điện Thoại:")); inputPanel.add(txtNV_SDT);
        inputPanel.add(new JLabel("(Lưu ý: Các chỉ số phụ mặc định)")); // Placeholder

        // 4. CÁC NÚT CHỨC NĂNG
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnThemNV = new JButton("Thêm NV Mới");
        JButton btnSuaNV = new JButton("Sửa NV Đang Chọn"); // Nút Sửa
        JButton btnXoaNV = new JButton("Xóa NV Đang Chọn");
        JButton btnLamMoiNV = new JButton("Làm Mới");

        btnPanel.add(btnThemNV);
        btnPanel.add(btnSuaNV);
        btnPanel.add(btnXoaNV);
        btnPanel.add(btnLamMoiNV);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        bottomPanel.add(btnPanel, BorderLayout.SOUTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // --- SỰ KIỆN CLICK BẢNG NHÂN VIÊN (Đổ dữ liệu lên ô nhập) ---
        tableNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableNhanVien.getSelectedRow();
                if (row >= 0) {
                    // Lấy Mã NV từ cột 1
                    String maNV = tableModelNhanVien.getValueAt(row, 1).toString();
                    NhanVien nv = qlNhanVien.timKiemNV(maNV);
                    if (nv != null) {
                        txtNV_MaNV.setText(nv.getMaNV());
                        txtNV_HoTen.setText(nv.hoTen);
                        txtNV_ChucVu.setText(nv.chucVu);
                        txtNV_HeSoLuong.setText(String.valueOf(nv.heSoLuong));
                        txtNV_MaID.setText(nv.maID);
                        txtNV_NgaySinh.setText(nv.ngaySinh);
                        txtNV_GioiTinh.setText(nv.gioiTinh);
                        txtNV_SDT.setText(nv.soDienThoai);
                        
                        // Set ComboBox đúng loại
                        if (nv instanceof BacSi) cbLoaiNV.setSelectedItem("Bác Sĩ");
                        else if (nv instanceof DieuDuong) cbLoaiNV.setSelectedItem("Điều Dưỡng");
                        else cbLoaiNV.setSelectedItem("Kỹ Thuật Viên");
                    }
                }
            }
        });

        // --- SỰ KIỆN NÚT BẤM ---

        // Tìm kiếm
        btnTimNV.addActionListener(e -> {
            String tuKhoa = txtTimKiemNV.getText().trim().toLowerCase();
            if (tuKhoa.isEmpty()) return;
            List<NhanVien> ketQua = new ArrayList<>();
            for (NhanVien nv : qlNhanVien.getDanhSachNV()) {
                if (nv.getMaNV().toLowerCase().contains(tuKhoa)) ketQua.add(nv);
            }
            loadDataNhanVienLenBang(ketQua);
        });

        // Thêm Nhân Viên
        btnThemNV.addActionListener(e -> {
            try {
                if (txtNV_MaNV.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Chưa nhập Mã NV!"); return;
                }
                double hsl = Double.parseDouble(txtNV_HeSoLuong.getText());
                String loai = (String) cbLoaiNV.getSelectedItem();
                
                NhanVien nvMoi = null;
                // Tạo đối tượng dựa trên ComboBox
                if ("Bác Sĩ".equals(loai)) {
                    nvMoi = new BacSi(txtNV_MaID.getText(), txtNV_HoTen.getText(), txtNV_NgaySinh.getText(),
                            txtNV_GioiTinh.getText(), txtNV_SDT.getText(), txtNV_MaNV.getText(),
                            txtNV_ChucVu.getText(), hsl, 500000); // Phụ cấp mặc định demo
                } else if ("Điều Dưỡng".equals(loai)) {
                    nvMoi = new DieuDuong(txtNV_MaID.getText(), txtNV_HoTen.getText(), txtNV_NgaySinh.getText(),
                            txtNV_GioiTinh.getText(), txtNV_SDT.getText(), txtNV_MaNV.getText(),
                            txtNV_ChucVu.getText(), hsl, 200000, 0); 
                } else {
                    nvMoi = new KiThuatVien(txtNV_MaID.getText(), txtNV_HoTen.getText(), txtNV_NgaySinh.getText(),
                            txtNV_GioiTinh.getText(), txtNV_SDT.getText(), txtNV_MaNV.getText(),
                            txtNV_ChucVu.getText(), hsl, 300000, 500000, 0);
                }

                qlNhanVien.themNV(nvMoi);
                loadDataNhanVienLenBang(qlNhanVien.getDanhSachNV());
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi nhập liệu (HSL phải là số): " + ex.getMessage());
            }
        });

        // Sửa Nhân Viên
        btnSuaNV.addActionListener(e -> {
            String maNV = txtNV_MaNV.getText();
            NhanVien nv = qlNhanVien.timKiemNV(maNV);
            if (nv != null) {
                try {
                    // Cập nhật thông tin từ ô nhập vào đối tượng
                    nv.hoTen = txtNV_HoTen.getText();
                    nv.chucVu = txtNV_ChucVu.getText();
                    nv.heSoLuong = Double.parseDouble(txtNV_HeSoLuong.getText());
                    nv.soDienThoai = txtNV_SDT.getText();
                    // (Các trường ID, Ngày sinh... cũng có thể set tương tự nếu muốn)
                    
                    loadDataNhanVienLenBang(qlNhanVien.getDanhSachNV());
                    JOptionPane.showMessageDialog(this, "Đã cập nhật thông tin NV: " + maNV);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi định dạng số: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy NV có mã: " + maNV + " để sửa!");
            }
        });

        // Xóa Nhân Viên
        btnXoaNV.addActionListener(e -> {
            int row = tableNhanVien.getSelectedRow();
            if (row >= 0) {
                String maNV = (String) tableModelNhanVien.getValueAt(row, 1);
                if (JOptionPane.showConfirmDialog(this, "Xóa NV: " + maNV + "?") == JOptionPane.YES_OPTION) {
                    qlNhanVien.xoaNV(maNV);
                    loadDataNhanVienLenBang(qlNhanVien.getDanhSachNV());
                }
            } else JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa!");
        });

        // Làm Mới
        btnLamMoiNV.addActionListener(e -> {
            txtTimKiemNV.setText("");
            txtNV_MaNV.setText(""); txtNV_HoTen.setText(""); txtNV_ChucVu.setText(""); txtNV_HeSoLuong.setText("");
            loadDataNhanVienLenBang(qlNhanVien.getDanhSachNV());
        });

        return panel;
    }

    private void loadDataNhanVienLenBang(List<NhanVien> listData) {
        tableModelNhanVien.setRowCount(0);
        for (NhanVien nv : listData) {
            String loai = "Nhân Viên";
            if (nv instanceof BacSi) loai = "Bác Sĩ";
            else if (nv instanceof DieuDuong) loai = "Điều Dưỡng";
            else if (nv instanceof KiThuatVien) loai = "Kỹ Thuật Viên";

            tableModelNhanVien.addRow(new Object[]{
                loai, nv.getMaNV(), nv.hoTen, nv.chucVu, nv.heSoLuong, String.format("%.0f", nv.TinhLuong())
            });
        }
    }

    // ========================================================================
    // HÀM CHUNG
    // ========================================================================
    private void luuDuLieuVaThoat() {
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có muốn lưu dữ liệu và thoát không?", 
                "Thoát", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            qlBenhNhan.luuVaoFile();
            qlNhanVien.luuVaoFile();
            qlDichVu.luuVaoFile();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { e.printStackTrace(); }

        SwingUtilities.invokeLater(() -> {
            GiaoDienYTe app = new GiaoDienYTe();
            app.setVisible(true);
        });
    }
}