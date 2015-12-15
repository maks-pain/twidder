package twidder.domain.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by maks-pain on 12/11/15
 */

@MappedSuperclass
abstract class Persistable {

    @Id
    @GeneratedValue
    Long id;

    @Version
    Integer version;

    @CreatedDate
    @Column(name = "created_date")
    Date createdDate;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Persistable{" +
                "id=" + id +
                ", version=" + version +
                ", createdDate=" + createdDate +
                '}';
    }
}

