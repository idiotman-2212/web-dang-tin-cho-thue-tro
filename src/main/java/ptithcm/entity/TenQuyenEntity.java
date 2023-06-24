package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TENQUYEN")
public class TenQuyenEntity {
	@Id
	@Column(name = "MaQuyen")
	private Integer maquyen;
	@Column(name = "TenQuyen")
	private String tenquyen;
	@OneToMany(mappedBy = "quyen", fetch = FetchType.EAGER)
	private Collection<TaiKhoanEntity> taikhoan;
	public Integer getMaquyen() {
		return maquyen;
	}
	public void setMaquyen(Integer maquyen) {
		this.maquyen = maquyen;
	}
	public String getTenquyen() {
		return tenquyen;
	}
	public void setTenquyen(String tenquyen) {
		this.tenquyen = tenquyen;
	}
	public Collection<TaiKhoanEntity> getTaikhoan() {
		return taikhoan;
	}
	public void setTaikhoan(Collection<TaiKhoanEntity> taikhoan) {
		this.taikhoan = taikhoan;
	}
	
}
