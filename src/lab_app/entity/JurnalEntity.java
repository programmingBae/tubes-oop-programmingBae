package lab_app.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "jurnal", schema = "lab_db")
public class JurnalEntity {
    private int idJurnal;
    private String ketTugas;
    private String status;
    private AnggotaStaffEntity anggotaStaffByPemberiTugas;
    private AnggotaStaffEntity anggotaStaffByPelaksanaTugas;
    private Date tanggalDibuat;

    @Id
    @GeneratedValue
    @Column(name = "id_jurnal")
    public int getIdJurnal() {
        return idJurnal;
    }

    public void setIdJurnal(int idJurnal) {
        this.idJurnal = idJurnal;
    }

    @Basic
    @Column(name = "ket_tugas")
    public String getKetTugas() {
        return ketTugas;
    }

    public void setKetTugas(String ketTugas) {
        this.ketTugas = ketTugas;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JurnalEntity that = (JurnalEntity) o;
        return idJurnal == that.idJurnal && Objects.equals(ketTugas, that.ketTugas) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJurnal, ketTugas, status);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pemberi_tugas", referencedColumnName = "NRP", nullable = false)
    public AnggotaStaffEntity getAnggotaStaffByPemberiTugas() {
        return anggotaStaffByPemberiTugas;
    }

    public void setAnggotaStaffByPemberiTugas(AnggotaStaffEntity anggotaStaffByPemberiTugas) {
        this.anggotaStaffByPemberiTugas = anggotaStaffByPemberiTugas;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelaksana_tugas", referencedColumnName = "NRP")
    public AnggotaStaffEntity getAnggotaStaffByPelaksanaTugas() {
        return anggotaStaffByPelaksanaTugas;
    }

    public void setAnggotaStaffByPelaksanaTugas(AnggotaStaffEntity anggotaStaffByPelaksanaTugas) {
        this.anggotaStaffByPelaksanaTugas = anggotaStaffByPelaksanaTugas;
    }

    @Basic
    @Column(name = "tanggal_dibuat")
    public Date getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(Date tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }
}
