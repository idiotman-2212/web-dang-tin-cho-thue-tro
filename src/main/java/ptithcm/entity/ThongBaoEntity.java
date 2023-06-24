package ptithcm.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "THONGBAO")

public class ThongBaoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaTB")
	private Long matb;
	@Column(name = "ThoiGian")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date thoigian;
	@Column(name = "NoiDung")
	private String noidung;
	@Column(name = "TieuDe")
	private String tieude;
	@Column(name="DaDoc")
	private Boolean dadoc;
	@ManyToOne
	@JoinColumn(name = "MaND")
	private NguoiDungEntity nguoidung;

	public Long getMatb() {
		return matb;
	}

	public void setMatb(Long matb) {
		this.matb = matb;
	}
	public Boolean getDaDoc(){return dadoc;}

    public void setDaDoc(Boolean dadoc) {this.dadoc = dadoc;}

    public Date getThoigian() {
		return thoigian;
	}

	public void setThoigian(Date thoigian) {
		this.thoigian = thoigian;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public String getTieude() {
		return tieude;
	}

	public void setTieude(String tieude) {
		this.tieude = tieude;
	}

	public NguoiDungEntity getNguoidung() {
		return nguoidung;
	}

	public void setNguoidung(NguoiDungEntity nguoidung) {
		this.nguoidung = nguoidung;
	}

	public String formatTime(Timestamp timeStamp){
		int hours = timeStamp.getHours();
		int minutes  = timeStamp.getNanos();
		return hours + " giờ " + minutes + " phút";
	}
	public String formateDate(Timestamp timeStamp){
		int date = timeStamp.getDate();
		int month = timeStamp.getMonth() + 1;
		int year = timeStamp.getYear() + 1900;
		return date + "/" + month + "/"+ year;
	}


}
