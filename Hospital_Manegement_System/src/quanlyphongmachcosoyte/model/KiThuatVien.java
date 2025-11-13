package quanlyphongmachcosoyte.model;

public class KiThuatVien extends NhanVien {
	private double phuCapNguyHiem;
	private double thuongHieuSuat;
	private int soGioTrucDem;

	// Getter & Setter
	public double layPhuCapNguyHiem() {
		return phuCapNguyHiem;
	};

	public void thietLapPhuCapNguyHiem(double phuCapNguyHiem) {
		this.phuCapNguyHiem = phuCapNguyHiem;
	};

	public double layThuongHieuSuat() {
		return thuongHieuSuat;
	};

	public void thietLapThuongHieuSuat(double thuongHieuSuat) {
		this.thuongHieuSuat = thuongHieuSuat;
	};

	public int laySoGioTrucDem() {
		return soGioTrucDem;
	};

	public void thietLapSoGioTrucDem(int soGioTrucDem) {
		this.soGioTrucDem = soGioTrucDem;
	};

	// Constructor
	public KiThuatVien(String maID, String hoTen, String ngaySinh, String gioiTinh, String soDienThoai, String maNV,
			String chucVu, double heSoLuong, double phuCapNguyHiem, double thuongHieuSuat, int soGioTrucDem) {
		super(maID, hoTen, ngaySinh, gioiTinh, soDienThoai, maNV, chucVu, heSoLuong);
		this.phuCapNguyHiem = phuCapNguyHiem;
		this.thuongHieuSuat = thuongHieuSuat;
		this.soGioTrucDem = soGioTrucDem;
	}

	@Override
	public double tinhLuong() {

		final double LUONG_CO_BAN = NhanVien.LUONG_CO_BAN;
		double luongCoCau = layHeSoLuong() * LUONG_CO_BAN;
		double phuCapDocHai = luongCoCau * 0.30;
		final double GIO_CHAM_CHUAN = 176;
		double donGiaLuongGio = luongCoCau / GIO_CHAM_CHUAN;
		double luongTrucDem = this.soGioTrucDem * donGiaLuongGio * 1.3;
		double thuongHieuSuat = this.thuongHieuSuat;
		double tongLuong = luongCoCau + phuCapDocHai + luongTrucDem + thuongHieuSuat + this.phuCapNguyHiem;

		return tongLuong;
	}

	@Override
	public void xuat() {
		super.xuat();
		System.out.println("Phu Cap Nguy Hiem: " + this.phuCapNguyHiem);
		System.out.println("Thuong Hieu Suat: " + this.thuongHieuSuat);
		System.out.println("So Gio Truc Dem: " + this.soGioTrucDem);
		System.out.println("Tong Luong: " + tinhLuong());
	}

	@Override
	public String chuyenThanhChuoiLuuTapTin() {
		return "KiThuatVien;" + layMaID() + ";" + layHoTen() + ";" + layNgaySinh() + ";" + layGioiTinh() + ";"
				+ laySoDienThoai() + ";"
				+ layMaNV() + ";" + layChucVu() + ";" + layHeSoLuong() + ";" + layPhuCapNguyHiem() + ";"
				+ thuongHieuSuat + ";"
				+ soGioTrucDem;
	}
}