package lab_app.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;



@Entity
@Table(name = "anggota_staff", schema = "lab_db")
public class AnggotaStaffEntity {
    private String nrp;
    private String firstName;
    private String lastName;
    private Date tahunMasuk;
    private String jabatan;



    @Id

    @Column(name = "NRP")
    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "tahun_masuk")
    public Date getTahunMasuk() {
        return tahunMasuk;
    }

    public void setTahunMasuk(Date tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    @Basic
    @Column(name = "Jabatan")
    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnggotaStaffEntity that = (AnggotaStaffEntity) o;
        return Objects.equals(nrp, that.nrp) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(tahunMasuk, that.tahunMasuk) && Objects.equals(jabatan, that.jabatan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrp, firstName, lastName, tahunMasuk, jabatan);
    }

    @Override
    public String toString() {
        return  firstName + ' ' + lastName;
    }


}
