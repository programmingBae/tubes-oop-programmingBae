package lab_app.entity;

import javax.persistence.*;
import java.util.Objects;



@Entity
@Table(name = "inventaris", schema = "lab_db")
public class InventarisEntity {
    private int idInventaris;
    private String namaBarang;
    private LaboratoriumEntity laboratoriumByLaboratoriumIdLab;
    private int jumlah;

    @Id
    @GeneratedValue
    @Column(name = "id_inventaris")
    public int getIdInventaris() {
        return idInventaris;
    }

    public void setIdInventaris(int idInventaris) {
        this.idInventaris = idInventaris;
    }

    @Basic
    @Column(name = "nama_barang")
    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventarisEntity that = (InventarisEntity) o;
        return idInventaris == that.idInventaris && laboratoriumByLaboratoriumIdLab.getIdLab() == laboratoriumByLaboratoriumIdLab.getIdLab() && Objects.equals(namaBarang, that.namaBarang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInventaris, namaBarang, laboratoriumByLaboratoriumIdLab.getIdLab());
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laboratorium_id_lab", referencedColumnName = "id_lab", nullable = false)
    public LaboratoriumEntity getLaboratoriumByLaboratoriumIdLab() {
        return laboratoriumByLaboratoriumIdLab;
    }

    public void setLaboratoriumByLaboratoriumIdLab(LaboratoriumEntity laboratoriumByLaboratoriumIdLab) {
        this.laboratoriumByLaboratoriumIdLab = laboratoriumByLaboratoriumIdLab;
    }


    @Basic
    @Column(name = "jumlah")
    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }


}
