package quanlyphongmachcosoyte;

abstract public class phongkhoa {
private String makhoa;
private String tenkhoa;
private String truongkhoa;
private int soluongnhanvien;
private int sophong;
private String dienthoai;
private String vitri;
public phongkhoa(String makhoa,String tenkhoa,String truongkhoa,int soluongnhanvien,String dienthoai,String vitri) {
	this.makhoa=makhoa;
	this.tenkhoa=tenkhoa;
	this.truongkhoa=truongkhoa;
	this.soluongnhanvien=soluongnhanvien;
	this.sophong=sophong;
	this.vitri=vitri;
	this.dienthoai=dienthoai;
}
public phongkhoa() {
	this.makhoa="mk 001";
	this.tenkhoa="khoa noi";
	this.truongkhoa="anh";
	this.soluongnhanvien=12;
	this.sophong=2;
	this.vitri="phong 12";
	this.dienthoai="0904583735";
	
	
}
public abstract void themNhanVien();
public abstract void xoaNhanVien();
public abstract int tinhTongBenhNhan();
	
	
	
	







public void xuat() {
	System.out.println("ma khoa:"+makhoa);
	System.out.println("ten khoa:"+tenkhoa);
	System.out.println("truong khoa:"+truongkhoa);
	System.out.println("ma khoa:"+soluongnhanvien);
	System.out.println("so phong:"+sophong);
	System.out.println("vi tri:"+vitri);
	System.out.println("dien thoai:"+dienthoai);
}
}
