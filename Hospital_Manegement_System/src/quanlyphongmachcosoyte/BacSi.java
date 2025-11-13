package quanlyphongmachcosoyte;

public class BacSi extends NhanVien {
	 private double phuCapChucVu;

	    public BacSi(String maID, String hoTen, String ngaySinh, String gioiTinh, String soDienThoai, String maNV,
	            String chucVu, double heSoLuong, double phuCapChucVu) {
	        super(maID, hoTen, ngaySinh, gioiTinh, soDienThoai, maNV, chucVu, heSoLuong);
	        this.phuCapChucVu = phuCapChucVu;
	    }

	    @Override
	    public double tinhLuong() {
	        double luong;
	        double luongCoCau = this.heSoLuong * NhanVien.LUONG_CO_BAN;
	        double phuCapUuDaiNghe = luongCoCau * 0.40;
	        double tongLuong = luongCoCau + phuCapUuDaiNghe + phuCapChucVu;
	        return tongLuong;
	    }

	    @Override
	    public void xuat() {
	        super.xuat();
	        System.out.println("Phu Cap Chuc Vu: " + this.phuCapChucVu);
	        System.out.println("Tong Luong: " + tinhLuong());
	    }

	    @Override
	    public String chuyenThanhChuoiLuuTapTin() {
	        return "BacSi;" + maID + ";" + hoTen + ";" + ngaySinh + ";" + gioiTinh + ";" + soDienThoai + ";"
	                + layMaNV() + ";" + chucVu + ";" + heSoLuong + ";" + phuCapChucVu;
	    }
	}