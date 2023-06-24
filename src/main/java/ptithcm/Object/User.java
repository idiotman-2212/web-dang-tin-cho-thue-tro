package ptithcm.Object;

public class User {
    private String sdt;
    private String email;
    private String diachi;
    private String linkanhdaidien;
    private String tenND;
    public User() {

    }

    public String getSdt() {
        return sdt;
    }

    public String getEmail() {
        return email;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getLinkanhdaidien() {
        return linkanhdaidien;
    }

    public String getTenND() {
        return tenND;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setLinkanhdaidien(String linkanhdaidien) {
        this.linkanhdaidien = linkanhdaidien;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    @Override
    public String toString() {
        return "User{" +
                ", sdt='" + sdt + '\'' +
                ", email='" + email + '\'' +
                ", diachi='" + diachi + '\'' +
                ", linkanhdaidien='" + linkanhdaidien + '\'' +
                ", tenND='" + tenND + '\'' +
                '}';
    }
}
