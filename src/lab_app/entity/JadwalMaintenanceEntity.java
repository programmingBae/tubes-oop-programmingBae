package lab_app.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "jadwal_maintenance", schema = "lab_db")
public class JadwalMaintenanceEntity {
    private int idJadwal;
    private String namaKegiatan;
    private Date tanggal;
    private AnggotaStaffEntity anggotaStaffByAnggotaStaffNrp;
    private AnggotaStaffEntity anggotaStaffByAnggotaStaffNrp1;
    private Time waktuMulai;
    private Time waktuSelesai;
    private Integer totalWaktu;
    private LaboratoriumEntity laboratoriumByLaboratoriumIdLab;
    private AnggotaStaffEntity anggotaStaffByKoor;

    @Id
    @GeneratedValue
    @Column(name = "id_jadwal")
    public int getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(int idJadwal) {
        this.idJadwal = idJadwal;
    }

    @Basic
    @Column(name = "nama_kegiatan")
    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    @Basic
    @Column(name = "tanggal")
    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JadwalMaintenanceEntity that = (JadwalMaintenanceEntity) o;
        return idJadwal == that.idJadwal && Objects.equals(namaKegiatan, that.namaKegiatan) && Objects.equals(tanggal, that.tanggal) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJadwal, namaKegiatan, tanggal);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "anggota_staff_NRP", referencedColumnName = "NRP")})
    public AnggotaStaffEntity getAnggotaStaffByAnggotaStaffNrp() {
        return anggotaStaffByAnggotaStaffNrp;
    }

    public void setAnggotaStaffByAnggotaStaffNrp(AnggotaStaffEntity anggotaStaffByAnggotaStaffNrp) {
        this.anggotaStaffByAnggotaStaffNrp = anggotaStaffByAnggotaStaffNrp;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "anggota_staff_NRP1", referencedColumnName = "NRP")})
    public AnggotaStaffEntity getAnggotaStaffByAnggotaStaffNrp1() {
        return anggotaStaffByAnggotaStaffNrp1;
    }

    public void setAnggotaStaffByAnggotaStaffNrp1(AnggotaStaffEntity anggotaStaffByAnggotaStaffNrp1) {
        this.anggotaStaffByAnggotaStaffNrp1 = anggotaStaffByAnggotaStaffNrp1;
    }


    @Basic
    @Column(name = "waktu_mulai")
    public Time getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(Time waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    @Basic
    @Column(name = "waktu_selesai")
    public Time getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(Time waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    @Basic
    @Column(name = "total_waktu")
    public Integer getTotalWaktu() {
        return totalWaktu;
    }

    public void setTotalWaktu(Integer totalWaktu) {
        this.totalWaktu = totalWaktu;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laboratorium_id_lab", referencedColumnName = "id_lab", nullable = false)
    public LaboratoriumEntity getLaboratoriumByLaboratoriumIdLab() {
        return laboratoriumByLaboratoriumIdLab;
    }

    public void setLaboratoriumByLaboratoriumIdLab(LaboratoriumEntity laboratoriumByLaboratoriumIdLab) {
        this.laboratoriumByLaboratoriumIdLab = laboratoriumByLaboratoriumIdLab;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "koor", referencedColumnName = "NRP")
    public AnggotaStaffEntity getAnggotaStaffByKoor() {
        return anggotaStaffByKoor;
    }

    public void setAnggotaStaffByKoor(AnggotaStaffEntity anggotaStaffByKoor) {
        this.anggotaStaffByKoor = anggotaStaffByKoor;
    }
}
