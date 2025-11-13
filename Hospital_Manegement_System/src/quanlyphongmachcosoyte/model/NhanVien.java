package quanlyphongmachcosoyte.model;

abstract public class NhanVien extends Nguoi {
	private String maNV;
	private String chucVu;
	private double heSoLuong;
	public static final double LUONG_CO_BAN = 4300000;

	// Getter & Setter
	public String layMaNV() {
		return maNV;
	};

	public void thietLapMaNV(String maNV) {
		this.maNV = maNV;
	};

	public String layChucVu() {
		return chucVu;
	};

	public void thietLapChucVu(String chucVu) {
		this.chucVu = chucVu;
	};

	public double layHeSoLuong() {
		return heSoLuong;
	};

	public void thietLapHeSoLuong(double heSoLuong) {
		this.heSoLuong = heSoLuong;
	};

	// Constructor
	public NhanVien(String maID, String hoTen, String ngaySinh, String gioiTinh, String soDienThoai, String maNV,
			String chucVu, double heSoLuong) {
		super(maID, hoTen, ngaySinh, gioiTinh, soDienThoai);
		this.maNV = maNV;
		this.chucVu = chucVu;
		this.heSoLuong = heSoLuong;
	}

	abstract public double tinhLuong(); // Đã đổi tên TinhLuong -> tinhLuong

	abstract public String chuyenThanhChuoiLuuTapTin(); // Đã đổi tên toFileString -> chuyenThanhChuoiLuuTapTin

	@Override
	public void xuat() {
		System.out.println("MaID: " + layMaID());
		System.out.println("Ho ten: " + layHoTen());
		System.out.println("Ngay Sinh: " + layNgaySinh());
		System.out.println("Gioi Tinh: " + layGioiTinh());
		System.out.println("So Dien Thoai: " + laySoDienThoai());
		System.out.println("Ma NV: " + layMaNV());
		System.out.println("Chuc Vu: " + layChucVu());
		System.out.println("He So Luong: " + layHeSoLuong());
	}
}