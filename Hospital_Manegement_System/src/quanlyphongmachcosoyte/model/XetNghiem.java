package quanlyphongmachcosoyte.model;

public class XetNghiem extends DichVuYTe {
    private String yeuCauThietBi;
    private String loaiXetNghiem;

    // Getter & Setter
    public String layYeuCauThietBi() {
        return yeuCauThietBi;
    };

    public void thietLapYeuCauThietBi(String yeuCauThietBi) {
        this.yeuCauThietBi = yeuCauThietBi;
    };

    public String layLoaiXetNghiem() {
        return loaiXetNghiem;
    };

    public void thietLapLoaiXetNghiem(String loaiXetNghiem) {
        this.loaiXetNghiem = loaiXetNghiem;
    };

    // Constructor
    public XetNghiem(String maDV, String tenDV, char xeploai, double giaTien, String yeuCauThietBi,
            String loaiXetNghiem) {
        super(maDV, tenDV, giaTien, xeploai);
        this.yeuCauThietBi = yeuCauThietBi;
        this.loaiXetNghiem = loaiXetNghiem;
    }

    @Override
    public void hienThi() {
        System.out.println("--- Thong tin Xet Nghiem ---");
        System.out.println("Ma dich vu: " + layMaDV());
        System.out.println("Ten dich vu: " + layTenDV());
        System.out.println("Gia tien: " + layGiaTien() + " VNĐ");
        System.out.println("Loai xet nghiem: " + loaiXetNghiem);
        System.out.println("Yeu cau thiet bi: " + yeuCauThietBi);
        System.out.println("-------------------------");
    }

    @Override
    public double tinhChiPhi() {
        return layGiaTien();
    }

    @Override
    public String chuyenThanhChuoiLuuTapTin() {
        return "XetNghiem;" + layMaDV() + ";" + layTenDV() + ";" + layXepLoai() + ";" + layGiaTien() + ";"
                + yeuCauThietBi
                + ";"
                + loaiXetNghiem;
    }

    public char xepLoaiTinhToan() {
        double diemthanhvien = layGiaTien() * 150;
        if (diemthanhvien >= 1300)
            return 'A';
        else if (diemthanhvien >= 1250)
            return 'B';
        else if (diemthanhvien >= 1000)
            return 'C';
        else
            return 'D';
    }

    public String layThietBi() {
        char xl = xepLoaiTinhToan();
        switch (xl) {
            case 'A':
                return "Thiet bi hien dai cao cap";
            case 'B':
                return "Thiet bi tieu chuan tot";
            case 'C':
                return "Thiet bi co ban";
            default:
                return "Thiet bi don gian";
        }
    }

    public String danhGiaXetNghiem() {
        switch (loaiXetNghiem.toLowerCase()) {
            case "máu":
                return "Yeu cau vo trung tuyet doi va thiet bi cao cap.";
            case "nước tiểu":
                return "Thiet bi co ban, de thuc hien.";
            case "sinh hóa":
                return "Can may phan tich tu dong.";
            case "covid-19":
                return "Can thiet bi PCR hien dai.";
            default:
                return "Loai xet nghiem khac.";
        }
    }
}