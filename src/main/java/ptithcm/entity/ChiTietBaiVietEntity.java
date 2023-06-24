package ptithcm.entity;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "CHITIETBAIVIET")
public class ChiTietBaiVietEntity {
	@Id
	@Column(name = "MaBaiViet")
	private Long mabaiviet;

	public Long getMabaiviet() {
		return this.mabaiviet;
	}

	public void setMabaiviet(Long mabaiviet) {
		this.mabaiviet = mabaiviet;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MaBaiViet", referencedColumnName = "MaBaiViet")
	private BaiVietEntity baiviet;
	
	@Column(name = "TinhTP")
	private String tinhtp;
	@Column(name = "QuanHuyen")
	private String quanhuyen;
	@Column(name = "PhuongXa")
	private String phuongxa;
	@Column(name = "MoTa")
	private String mota;

	@Column(name="LinkVideo")
	private  String linkVideo;
	@Column(name = "ThoiGianBatDau")
	private Timestamp thoigianbatdau;
	@Column(name = "ThoiGianKetThuc")
	private Timestamp thoigianketthuc;
	public BaiVietEntity getBaiviet() {
		return baiviet;
	}
	public void setBaiviet(BaiVietEntity baiviet) {
		this.baiviet = baiviet;
	}
	public String getTinhtp() {
		return tinhtp;
	}
	public void setTinhtp(String tinhtp) {
		this.tinhtp = tinhtp;
	}
	public String getQuanhuyen() {
		return quanhuyen;
	}
	public void setQuanhuyen(String quanhuyen) {
		this.quanhuyen = quanhuyen;
	}
	public String getPhuongxa() {
		return phuongxa;
	}
	public void setPhuongxa(String phuongxa) {
		this.phuongxa = phuongxa;
	}
	public String getLinkVideo(){return linkVideo;}
	public void setLinkVideo(String linkVideo){this.linkVideo = linkVideo;}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public Timestamp getThoigianbatdau() {
		return thoigianbatdau;
	}
	public void setThoigianbatdau(Timestamp thoigianbatdau) {
		this.thoigianbatdau = thoigianbatdau;
	}
	public Timestamp getThoigianketthuc() {
		return thoigianketthuc;
	}
	public void setThoigianketthuc(Timestamp thoigianketthuc) {
		this.thoigianketthuc = thoigianketthuc;
	}
	public String formatDateTime(Timestamp timeStamp){
		int date = timeStamp.getDate();
		int month = timeStamp.getMonth() + 1;
		int year = timeStamp.getYear() + 1900;
		int hours = timeStamp.getHours();
		int minutes = timeStamp.getMinutes();
		String formatedTime = hours + ":" + minutes +" "+ date + "/" + month + "/"+ year;
		return formatedTime;
	}
	public String getEndDateFormated(){
		return formatDateTime(this.thoigianketthuc);
	}
	public String getStartDateFormated(){
		return formatDateTime(this.thoigianbatdau);
	}


}
