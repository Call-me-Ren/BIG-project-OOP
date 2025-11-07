package quanlyphongmachcosoyte;

import java.util.Date;

abstract public class Nguoi {
protected String maID;
protected String hoTen;
protected Date ngaySinh;
protected String gioiTinh;
protected String soDienThoai;

public Nguoi(String maID,String hoTen,Date ngaySinh,String gioiTinh,String soDienThoai) {
	this.maID=maID;
	this.hoTen=hoTen;
	this.ngaySinh=ngaySinh;
	this.gioiTinh=gioiTinh;
	this.soDienThoai=soDienThoai;
}

abstract public void xuat();
}
