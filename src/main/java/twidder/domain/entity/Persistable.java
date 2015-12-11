package twidder.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Created by maks on 12/11/15.
 */

@MappedSuperclass
abstract class Persistable {

    @Id
    @GeneratedValue
    Long id;

    @Version
    Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Persistable{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }
}

