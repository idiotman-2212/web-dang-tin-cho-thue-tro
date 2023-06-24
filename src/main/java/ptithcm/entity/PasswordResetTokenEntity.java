package ptithcm.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="PassResetToken")
public class PasswordResetTokenEntity {
//    Thời gian sống của token theo giờ
    private static final int EXPIRATION = 8;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;
    @Column(name="token")
    private String token;

    public Long getMaND() {
        return maND;
    }

    public void setMaND(Long maND) {
        this.maND = maND;
    }

    @Column(name= "maND")
    private Long maND;
    public PasswordResetTokenEntity() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpireDate() {
        return expireDate;
    }

    public void setExpireDate() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
        cal.add(Calendar.HOUR_OF_DAY, EXPIRATION);
        timestamp.setTime(cal.getTime().getTime());
        this.expireDate = timestamp;

    }
    @Column(name="expiredDate")
    private Timestamp expireDate;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NguoiDungEntity getNguoidung() {
        return nguoidung;
    }

    public void setNguoidung(NguoiDungEntity nguoidung) {
        this.nguoidung = nguoidung;
    }

    @ManyToOne (optional=false)
    @JoinColumn(name = "maND", referencedColumnName = "maND",insertable=false, updatable=false)
    private NguoiDungEntity nguoidung;
    public PasswordResetTokenEntity(String token, Long MaND) {
        this.token = token;
        this.maND =MaND;
        this.setExpireDate();
    }

}
