package quanlyphongmachcosoyte;

public class BenhNhan extends Nguoi {
    private String maBN;
    private String ngayVaoVien;
    private String benhLy;

    public BenhNhan(String maID, String hoTen, String ngaySinh, String gioiTinh, String soDienThoai, String maBN,
            String ngayVaoVien, String benhLy) {
        super(maID, hoTen, ngaySinh, gioiTinh, soDienThoai);
        this.maBN = maBN;
        this.ngayVaoVien = ngayVaoVien;
        this.benhLy = benhLy;
    }

    @Override
    public void xuat() {
        System.out.println("MaID: " + this.maID);
        System.out.println("Ho ten: " + this.hoTen);
        System.out.println("Ngay Sinh: " + this.ngaySinh);
        System.out.println("Gioi Tinh: " + this.gioiTinh);
        System.out.println("So Dien Thoai: " + this.soDienThoai);
        System.out.println("Ma BN: " + this.maBN);
        System.out.println("Ngay Vao Vien: " + this.ngayVaoVien);
        System.out.println("Benh Ly: " + this.benhLy);
    }

    public String getMaBN() {
        return maBN;
    }

    public void setBenhLy(String benhLy) {
        this.benhLy = benhLy;
    }
    
    // --- CÁC HÀM GETTER MỚI CHO GUI ---
    public String getNgayVaoVien() {
        return ngayVaoVien;
    }

    public String getBenhLy() {
        return benhLy;
    }
    
    public String toFileString() {
        return maID + ";" + hoTen + ";" + ngaySinh + ";" + gioiTinh + ";" + soDienThoai + ";"
                + maBN + ";" + ngayVaoVien + ";" + benhLy;
    }
}