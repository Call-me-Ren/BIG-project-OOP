package quanlyphongmachcosoyte;

public class XetNghiem extends DichVuYTe {
    private String yeuCauThietBi;
    private String loaiXetNghiem;

    public XetNghiem(String maDV, String tenDV, char xeploai, double giaTien, String yeuCauThietBi, String loaiXetNghiem) {
        super(maDV, tenDV, giaTien, xeploai);
        this.yeuCauThietBi = yeuCauThietBi;
        this.loaiXetNghiem = loaiXetNghiem;
    }

    @Override
    public void hienThi() {
        System.out.println("--- Thong tin Xet Nghiem ---");
        System.out.println("Ma dich vu: " + maDV);
        System.out.println("Ten dich vu: " + tenDV);
        System.out.println("Gia tien: " + giaTien + " VNĐ");
        System.out.println("Loai xet nghiem: " + loaiXetNghiem);
        System.out.println("Yeu cau thiet bi: " + yeuCauThietBi);
        System.out.println("-------------------------");
    }

    @Override
    public double tinhChiPhi() {
        return this.giaTien;
    }
    
    @Override
    public String chuyenThanhChuoiLuuTapTin() {
        return "XetNghiem;" + maDV + ";" + tenDV + ";" + xeploai + ";" + giaTien + ";" + yeuCauThietBi + ";" + loaiXetNghiem;
    }
    
    public char xepLoaiTinhToan() {
        double diemthanhvien = giaTien * 150;
        if (diemthanhvien >= 1300) return 'A';
        else if (diemthanhvien >= 1250) return 'B';
        else if (diemthanhvien >= 1000) return 'C';
        else return 'D';
    }

    public String layThietBi() {
        char xl = xepLoaiTinhToan();
        switch (xl) {
            case 'A': return "Thiet bi hien dai cao cap";
            case 'B': return "Thiet bi tieu chuan tot";
            case 'C': return "Thiet bi co ban";
            default: return "Thiet bi don gian";
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