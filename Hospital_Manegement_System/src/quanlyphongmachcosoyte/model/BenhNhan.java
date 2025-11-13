package quanlyphongmachcosoyte.model;

public class BenhNhan extends Nguoi {
    private String maBN;
    private String ngayVaoVien;
    private String benhLy;

    // Getter & Setter
    public String layMaBN() {
        return maBN;
    }

    public void thieLapMaBN(String maBN) {
        this.maBN = maBN;
    };

    public String layBenhLy() {
        return benhLy;
    }

    public void thietLapBenhLy(String benhLy) {
        this.benhLy = benhLy;
    }

    public String layNgayVaoVien() {
        return ngayVaoVien;
    }

    public void thietLapNgayVaoVien(String ngayVaoVien) {
        this.ngayVaoVien = ngayVaoVien;
    };

    // Constructor
    public BenhNhan(String maID, String hoTen, String ngaySinh, String gioiTinh, String soDienThoai, String maBN,
            String ngayVaoVien, String benhLy) {
        super(maID, hoTen, ngaySinh, gioiTinh, soDienThoai);
        this.maBN = maBN;
        this.ngayVaoVien = ngayVaoVien;
        this.benhLy = benhLy;
    }

    @Override
    public void xuat() {
        System.out.println("MaID: " + layMaID());
        System.out.println("Ho ten: " + layHoTen());
        System.out.println("Ngay Sinh: " + layNgaySinh());
        System.out.println("Gioi Tinh: " + layGioiTinh());
        System.out.println("So Dien Thoai: " + laySoDienThoai());
        System.out.println("Ma BN: " + layMaBN());
        System.out.println("Ngay Vao Vien: " + layNgayVaoVien());
        System.out.println("Benh Ly: " + layBenhLy());
    }

    public String chuyenThanhChuoiLuuTapTin() {
        return layMaID() + ";" + layHoTen() + ";" + layNgaySinh() + ";" + layGioiTinh() + ";" + laySoDienThoai() + ";"
                + layMaBN() + ";" + layNgayVaoVien() + ";" + layBenhLy();
    }
}