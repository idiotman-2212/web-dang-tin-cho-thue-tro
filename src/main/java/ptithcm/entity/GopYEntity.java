package ptithcm.entity;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "GOPY")
public class GopYEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaGY")
	private Long magy;
	@Column(name = "ThoiGian")
	private Timestamp thoigian;
	@Column(name = "SoSao")
	private Integer sosao;
	@Column(name = "NoiDung")
	private String noidung;
	
	@ManyToOne
	@JoinColumn(name = "MaND")
	private NguoiDungEntity nguoidung;

	public Long getMagy() {
		return magy;
	}

	public void setMagy(Long magy) {
		this.magy = magy;
	}

	public Timestamp getThoigian() {
		return thoigian;
	}

	public void setThoigian(Timestamp thoigian) {
		this.thoigian = thoigian;
	}

	public Integer getSosao() {
		return sosao;
	}

	public void setSosao(Integer sosao) {
		this.sosao = sosao;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public NguoiDungEntity getNguoidung() {
		return nguoidung;
	}

	public void setNguoidung(NguoiDungEntity nguoidung) {
		this.nguoidung = nguoidung;
	}
	
}
