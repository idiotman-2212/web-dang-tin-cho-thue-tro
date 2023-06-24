package ptithcm.entity;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "QUANGCAO")
public class QuangCaoEntity {
	@Id
	@GeneratedValue
	@Column(name = "MaQC")
	private Long maqc;
	@Column(name = "NgayBatDau")
	private Timestamp ngaybatdau;
	@Column(name = "NgayKetThuc")
	private Timestamp ngayketthuc;
	@Column(name = "GiaQC")
	private Float giaqc;
	@Column(name = "TinhTrang")
	private Boolean tinhtrang;
	
	@ManyToOne
	@JoinColumn(name = "MaBaiViet")
	private BaiVietEntity baiviet;

	public Long getMaqc() {
		return maqc;
	}

	public void setMaqc(Long maqc) {
		this.maqc = maqc;
	}

	public Timestamp getNgaybatdau() {
		return ngaybatdau;
	}

	public void setNgaybatdau(Timestamp ngaybatdau) {
		this.ngaybatdau = ngaybatdau;
	}

	public Timestamp getNgayketthuc() {
		return ngayketthuc;
	}

	public void setNgayketthuc(Timestamp ngayketthuc) {
		this.ngayketthuc = ngayketthuc;
	}

	public Float getGiaqc() {
		return giaqc;
	}

	public void setGiaqc(Float giaqc) {
		this.giaqc = giaqc;
	}

	public Boolean getTinhtrang() {
		return tinhtrang;
	}

	public void setTinhtrang(Boolean tinhtrang) {
		this.tinhtrang = tinhtrang;
	}

	public BaiVietEntity getBaiviet() {
		return baiviet;
	}

	public void setBaiviet(BaiVietEntity baiviet) {
		this.baiviet = baiviet;
	}
}
