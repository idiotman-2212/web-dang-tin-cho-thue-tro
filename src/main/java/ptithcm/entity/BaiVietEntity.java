package ptithcm.entity;

import java.lang.annotation.Native;
import java.util.Collection;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity

@Table(name = "BAIVIET")
public class BaiVietEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaBaiViet")
	private Long mabaiviet;
	@Column(name = "TieuDe")
	private String tieude;
	@Column(name = "Gia")
	private Float gia;
	@Column(name = "DiaChi")
	private String diachi;
	@Column(name = "TinhTrang")
	private Boolean tinhtrang;
	@Column(name = "An")
	private Boolean an;
	@Column(name = "DienTich")
	private Integer dientich;

	public int getDientich() {
		return this.dientich;
	}

	public void setDientich(int dientich) {
		this.dientich = dientich;
	}

    @ManyToOne
	@JoinColumn(name = "MaND")
	private NguoiDungEntity nguoidung;
	
	@OneToOne(mappedBy = "baiviet",cascade = CascadeType.ALL,orphanRemoval = true)
	private ChiTietBaiVietEntity chitietbaiviet;

	public ChiTietBaiVietEntity getChitietbaiviet() {
		return this.chitietbaiviet;
	}

	public void setChitietbaiviet(ChiTietBaiVietEntity chitietbaiviet) {
		this.chitietbaiviet = chitietbaiviet;
	}
	
	@OneToMany(mappedBy = "baiviet",cascade=CascadeType.ALL,orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<AnhEntity> Anh;

	public Collection<AnhEntity> getAnh() {
		return this.Anh;
	}

	public void setAnh(Collection<AnhEntity> Anh) {
		this.Anh = Anh;
	}
	
	@OneToMany(mappedBy = "baiviet",cascade=CascadeType.ALL,orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<QuangCaoEntity> quangcao;

	public Collection<QuangCaoEntity> getQuangcao() {
		return this.quangcao;
	}

	public BaiVietEntity(String tieude, Float gia, String khuvuc, String diachi,
			 String linkanh, int dientich) {
		super();
		
		this.tieude = tieude;
		this.gia = gia;
		this.diachi = diachi;
		this.dientich = dientich;
	}
	public BaiVietEntity(){
		super();
	}

	public void setQuangcao(Collection<QuangCaoEntity> quangcao) {
		this.quangcao = quangcao;
	}
	
	public Long getMabaiviet() {
		return mabaiviet;
	}
	public void setMabaiviet(Long mabaiviet) {
		this.mabaiviet = mabaiviet;
	}
	public String getTieude() {
		return tieude;
	}
	public void setTieude(String tieude) {
		this.tieude = tieude;
	}
	public Float getGia() {
		return gia;
	}
	public void setGia(Float gia) {
		System.out.println(gia);
		this.gia = gia;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public Boolean getTinhtrang() {
		return tinhtrang;
	}
	public void setTinhtrang(Boolean tinhtrang) {
		this.tinhtrang = tinhtrang;
	}
	public Boolean getAn() {
		return an;
	}
	public void setAn(Boolean an) {
		this.an = an;
	}
	public NguoiDungEntity getNguoidung() {
		return nguoidung;
	}
	public void setNguoidung(NguoiDungEntity nguoidung) {
		this.nguoidung = nguoidung;
	}
	public boolean isDisplay(){
		return !this.an && this.tinhtrang;
	}

	@Override
	public String toString() {
		return  "BaiVietEntity{" +
				"mabaiviet=" + mabaiviet +
				", tieude='" + tieude + '\'' +
				", gia=" + gia +
				", diachi='" + diachi + '\'' +
				", tinhtrang=" + tinhtrang +
				", an=" + an +
				", dientich=" + dientich +
				'}';
	}
}
