package twidder.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by maks on 12/11/15
 */


@Entity
public class Twid extends Persistable {

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    @Column
    private String text;

    public Twid() {
    }

    @Override
    public String toString() {
        return "Twid{" +
                "owner=" + owner.getUsername() +
                ", text='" + text + '\'' +
                '}';
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Twid twid = (Twid) o;

        return !(owner != null ? !owner.equals(twid.owner) : twid.owner != null)
                && !(text != null ? !text.equals(twid.text) : twid.text != null);

    }

    @Override
    public int hashCode() {
        int result = owner != null ? owner.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
