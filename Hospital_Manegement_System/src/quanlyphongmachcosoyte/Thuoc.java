package quanlyphongmachcosoyte;

public class Thuoc extends DichVuYTe {
    private String donViTinh;
    private double hesoThuoc;

    public Thuoc(String maDV, String tenDV, double giaTien, String donViTinh, double hesoThuoc, char xeploai) {
        super(maDV, tenDV, giaTien, xeploai);
        this.donViTinh = donViTinh;
        this.hesoThuoc = hesoThuoc;
    }

    @Override
    public void hienThi() { // Triển khai đúng tên mới
        System.out.println("--- Thong tin Thuoc ---");
        System.out.println("Ma Dich vu: " + this.maDV);
        System.out.println("Ten Thuoc: " + this.tenDV);
        System.out.println("Gia Tien (goc): " + this.giaTien + " VND");
        System.out.println("Don vi Tinh: " + this.donViTinh);
        System.out.println("Xep loai (tinh toan): " + xepLoaiTinhToan());
        System.out.println("He so thuoc: " + hesoThuoc);
        System.out.println("-------------------------");
    }

    @Override
    public double tinhChiPhi() {
        double tienthuocCoBan = hesoThuoc * giaTien;
        double tienTheoXepLoai;

        switch (xepLoaiTinhToan()) {
            case 'A':
                tienTheoXepLoai = tienthuocCoBan / 0.75;
                break;
            case 'B':
                tienTheoXepLoai = tienthuocCoBan / 0.5;
                break;
            case 'C':
            default:
                tienTheoXepLoai = tienthuocCoBan;
                break;
        }

        double thueVat = tienTheoXepLoai * 0.15;
        double hoaHong = giaTien * 0.15;

        return tienTheoXepLoai + thueVat - hoaHong;
    }
    
    @Override
    public String chuyenThanhChuoiLuuTapTin() { // Triển khai đúng tên mới
        return "Thuoc;" + maDV + ";" + tenDV + ";" + giaTien + ";" + donViTinh + ";" + hesoThuoc + ";" + xeploai;
    }

    private char xepLoaiTinhToan() {
        if (this.hesoThuoc == 0) return 'D';

        double diemtichluy = (giaTien / hesoThuoc) * 100;
        if (diemtichluy >= 100) return 'A';
        else if (diemtichluy >= 50) return 'B';
        else if (diemtichluy >= 25) return 'C';
        else return 'D';
    }
}