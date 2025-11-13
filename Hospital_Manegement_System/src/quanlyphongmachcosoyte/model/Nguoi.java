package quanlyphongmachcosoyte.model;

abstract public class Nguoi {
	private String maID;
	private String hoTen;
	private String ngaySinh;
	private String gioiTinh;
	private String soDienThoai;

	// Getter & Setter
	public String layMaID() {
		return maID;
	};

	public void thietLapMaID(String maID) {
		this.maID = maID;
	};

	public String layHoTen() {
		return hoTen;
	}

	public void thietLapHoTen(String hoTen) {
		this.hoTen = hoTen;
	};

	public String layNgaySinh() {
		return ngaySinh;
	};

	public void thietLapNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	};

	public String layGioiTinh() {
		return gioiTinh;
	};

	public void thietLapGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	};

	public String laySoDienThoai() {
		return soDienThoai;
	};

	public void thietLapSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	};

	// Constructor
	public Nguoi(String maID, String hoTen, String ngaySinh, String gioiTinh, String soDienThoai) {
		this.maID = maID;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
	}

	abstract public void xuat();
}