package ptithcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAIKHOAN")
public class TaiKhoanEntity {
	@Id
	@Column(name = "TenDN")
	private String tenDN;
	@Column(name = "MatKhau")
	private String matkhau;
	@Column(name = "TinhTrang")
	private Boolean tinhtrang;
	@ManyToOne 
	@JoinColumn(name = "MaQuyen")
	private TenQuyenEntity quyen;

	public TenQuyenEntity getQuyen() {
		return this.quyen;
	}

	public void setQuyen(TenQuyenEntity quyen) {
		this.quyen = quyen;
	}

    @OneToOne(mappedBy = "taikhoan")
	private NguoiDungEntity nguoidung;
	
	public String getTenDN() {
		return tenDN;
	}
	public void setTenDN(String tenDN) {
		this.tenDN = tenDN;
	}
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	public Boolean getTinhtrang() {
		return tinhtrang;
	}
	public void setTinhtrang(Boolean tinhtrang) {
		this.tinhtrang = tinhtrang;
	}
	public NguoiDungEntity getNguoidung() {
		return nguoidung;
	}
	public void setNguoidung(NguoiDungEntity nguoidung) {
		this.nguoidung = nguoidung;
	}
	public TaiKhoanEntity(String tenDN,String mk,Boolean tinhtrang){
		this.matkhau=mk;
		this.tenDN=tenDN;
		this.tinhtrang=tinhtrang;
	}
	public TaiKhoanEntity() {
	}
	
}
