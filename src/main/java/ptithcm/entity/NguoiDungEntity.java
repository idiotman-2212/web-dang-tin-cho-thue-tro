package ptithcm.entity;

import java.util.Collection;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "NGUOIDUNG")
public class NguoiDungEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaND")
	private Long maND;
	@Column(name = "SDT")
	private String sdt;
	@Column(name = "Email")
	private String email;
	@Column(name = "DiaChi")
	private String diachi;
	@Column(name = "LinkAnhDaiDien")
	private String linkanhdaidien;

	@Column(name="TenND")
	private String tenND;
	public String getTenND() {
		return this.tenND;
	}

	public void setTenND(String tenND) {
		this.tenND = tenND;
	}
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "TenDN", referencedColumnName = "TenDN")
	private TaiKhoanEntity taikhoan;



	@OneToMany(mappedBy = "nguoidung")
	private Collection<PasswordResetTokenEntity> passwordResetToken;

	public Collection<PasswordResetTokenEntity> getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(Collection<PasswordResetTokenEntity> passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}
	@OneToMany(mappedBy = "nguoidung")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<BaiVietEntity> baiviet;
	
	@OneToMany(mappedBy = "nguoidung")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<ThongBaoEntity> thongbao;
	
	@OneToMany(mappedBy = "nguoidung")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<GopYEntity> gopy;

	public Long getMaND() {
		return maND;
	}

	public void setMaND(Long maND) {
		this.maND = maND;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getLinkanhdaidien() {
		return linkanhdaidien;
	}

	public void setLinkanhdaidien(String linkanhdaidien) {
		this.linkanhdaidien = linkanhdaidien;
	}

	public TaiKhoanEntity getTaikhoan() {
		return taikhoan;
	}

	public void setTaikhoan(TaiKhoanEntity taikhoan) {
		this.taikhoan = taikhoan;
	}

	public Collection<BaiVietEntity> getBaiviet() {
		return baiviet;
	}

	public void setBaiviet(Collection<BaiVietEntity> baiviet) {
		this.baiviet = baiviet;
	}

	public Collection<ThongBaoEntity> getThongbao() {
		return thongbao;
	}

	public void setThongbao(Collection<ThongBaoEntity> thongbao) {
		this.thongbao = thongbao;
	}

	public Collection<GopYEntity> getGopy() {
		return gopy;
	}

	public void setGopy(Collection<GopYEntity> gopy) {
		this.gopy = gopy;
	}

	@Override
	public String toString() {
		return "NguoiDungEntity{" +
				"maND=" + maND +
				", sdt='" + sdt + '\'' +
				", email='" + email + '\'' +
				", diachi='" + diachi + '\'' +
				", linkanhdaidien='" + linkanhdaidien + '\'' +
				", tenND='" + tenND + '\'' +
				", taikhoan=" + taikhoan +
				", baiviet=" + baiviet +
				", thongbao=" + thongbao +
				", gopy=" + gopy +
				'}';
	}
}
