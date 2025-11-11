package quanlyphongmachcosoyte;

public class Thuoc extends DichVuYTe {
    private String donViTinh;

    // Constructor
    public Thuoc(String maDV, String tenDV, double giaTien, String donViTinh) {
        super(maDV, tenDV, giaTien);
        this.donViTinh = donViTinh;
    }

    @Override
    public void HienThi() {
        System.out.println("--- Thông tin Thuốc ---");
        System.out.println("Mã Dịch vụ: " + this.maDV);
        System.out.println("Tên Thuốc: " + this.tenDV);
        System.out.println("Giá Tiền: " + this.giaTien + " VND");
        System.out.println("Đơn vị Tính: " + this.donViTinh);
        System.out.println("-------------------------");
    }

    // Getter và Setter
    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
}