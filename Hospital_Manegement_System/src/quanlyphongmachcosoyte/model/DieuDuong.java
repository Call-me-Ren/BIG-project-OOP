package quanlyphongmachcosoyte.model;

public class DieuDuong extends NhanVien {
	private double phuCapNgheNghiep;
	private int soGioLamThem;

	// Getter & Setter
	public double layPhuCapNgheNghiep() {
		return phuCapNgheNghiep;
	};

	public void thietLapPhuCapNgheNghiep() {
		this.phuCapNgheNghiep = phuCapNgheNghiep;
	};

	public int laySoGioLamThem() {
		return soGioLamThem;
	};

	public void thietLapSoGioLamThem() {
		this.soGioLamThem = soGioLamThem;
	};

	// Constructor
	public DieuDuong(String maID, String hoTen, String ngaySinh, String gioiTinh, String soDienThoai, String maNV,
			String chucVu, double heSoLuong, double phuCapNgheNghiep, int soGioLamThem) {
		super(maID, hoTen, ngaySinh, gioiTinh, soDienThoai, maNV, chucVu, heSoLuong);
		this.phuCapNgheNghiep = phuCapNgheNghiep;
		this.soGioLamThem = soGioLamThem;
	}

	@Override
	public double tinhLuong() {
		double luongCoCau = layHeSoLuong() * NhanVien.LUONG_CO_BAN;
		double phuCapUuDaiNghe = luongCoCau * 0.40;
		final double GIO_LAM_CHUAN = 176;
		double donGiaLuongGio = luongCoCau / GIO_LAM_CHUAN;
		double luongLamThem = this.soGioLamThem * donGiaLuongGio * 1.5;
		double tongLuong = luongCoCau + phuCapUuDaiNghe + luongLamThem + this.phuCapNgheNghiep;
		return tongLuong;
	}

	@Override
	public void xuat() {
		super.xuat();
		System.out.println("Phu Cap Nghe Nghiep: " + this.phuCapNgheNghiep);
		System.out.println("So Gio Lam Them: " + this.soGioLamThem);
		System.out.println("Tong luong: " + tinhLuong());
	}

	@Override
	public String chuyenThanhChuoiLuuTapTin() {
		return "DieuDuong;" + layMaID() + ";" + layHoTen() + ";" + layNgaySinh() + ";" + layGioiTinh() + ";"
				+ laySoDienThoai() + ";"
				+ layMaNV() + ";" + layChucVu() + ";" + layHeSoLuong() + ";" + layPhuCapNgheNghiep() + ";"
				+ laySoGioLamThem();
	}
}