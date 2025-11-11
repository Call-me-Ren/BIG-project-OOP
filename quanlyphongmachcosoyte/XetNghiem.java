package quanlyphongmachcosoyte;

public class XetNghiem extends DichVuYTe {
    private String yeuCauThietBi;
    private String loaiXetNghiem;

    // Constructor
    public XetNghiem(String maDV, String tenDV, double giaTien, String yeuCauThietBi, String loaiXetNghiem) {
        super(maDV, tenDV, giaTien);
        this.yeuCauThietBi = yeuCauThietBi;
        this.loaiXetNghiem = loaiXetNghiem;
    }

    @Override
    public void HienThi() {
        System.out.println("--- Thông tin Dịch vụ Xét Nghiệm ---");
        System.out.println("Mã Dịch vụ: " + this.maDV);
        System.out.println("Tên Xét nghiệm: " + this.tenDV);
        System.out.println("Giá Tiền: " + this.giaTien + " VND");
        System.out.println("Loại Xét nghiệm: " + this.loaiXetNghiem);
        System.out.println("Yêu cầu Thiết bị: " + this.yeuCauThietBi);
        System.out.println("------------------------------------");
    }

    // Getter và Setter
    public String getYeuCauThietBi() {
        return yeuCauThietBi;
    }

    public void setYeuCauThietBi(String yeuCauThietBi) {
        this.yeuCauThietBi = yeuCauThietBi;
    }

    public String getLoaiXetNghiem() {
        return loaiXetNghiem;
    }

    public void setLoaiXetNghiem(String loaiXetNghiem) {
        this.loaiXetNghiem = loaiXetNghiem;
    }
}