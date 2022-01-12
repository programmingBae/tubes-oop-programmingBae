package lab_app.entity;

import javax.persistence.*;
import java.util.Objects;



@Entity
@Table(name = "laboratorium", schema = "lab_db")
public class LaboratoriumEntity {
    private int idLab;
    private String namaLab;



    @Id
    @GeneratedValue
    @Column(name = "id_lab")
    public int getIdLab() {
        return idLab;
    }

    public void setIdLab(int idLab) {
        this.idLab = idLab;
    }

    @Basic
    @Column(name = "nama_lab")
    public String getNamaLab() {
        return namaLab;
    }

    public void setNamaLab(String namaLab) {
        this.namaLab = namaLab;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaboratoriumEntity that = (LaboratoriumEntity) o;
        return idLab == that.idLab && Objects.equals(namaLab, that.namaLab);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLab, namaLab);
    }

    @Override
    public String toString() {
        return namaLab;
    }


}
