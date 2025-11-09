package quanlyphongmachcosoyte;

abstract public class DichVuYTe {
	protected String maDV;
	protected String tenDV;
	protected double giaTien;

	public DichVuYTe(String maDV, String tenDV, double giatien) {
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.giaTien = giatien;
	}

	abstract public void HienThi();
}
