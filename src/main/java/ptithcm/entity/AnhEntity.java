package ptithcm.entity;

import javax.persistence.*;

@Entity
@Table(name = "ANH")
public class AnhEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaAnh")
	private Long maanh;
	@Column(name = "LinkAnh")
	private String linkanh;
	
	@ManyToOne
	@JoinColumn(name = "MaBaiViet",referencedColumnName = "MaBaiViet")
	private BaiVietEntity baiviet;
	public Long getMaanh() {
		return maanh;
	}
	public void setMaanh(Long maanh) {
		this.maanh = maanh;
	}
	public String getLinkanh() {
		return linkanh;
	}
	public void setLinkanh(String linkanh) {
		this.linkanh = linkanh;
	}
	public BaiVietEntity getBaiviet() {
		return baiviet;
	}
	public void setBaiviet(BaiVietEntity baiviet) {
		this.baiviet = baiviet;
	}
	
}
