package twidder.domain.entity;

import javax.persistence.*;

/**
 * Created by maks on 12/11/15
 */


@Entity
public class Subscription extends Persistable {

    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "buddy_id", nullable = false)
    private Long buddyId;
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name = "buddy_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User buddy;

    public Subscription() {
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "userId=" + userId +
                ", buddyId=" + buddyId +
                ", user=" + user +
                ", buddy=" + buddy +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (buddy != null ? !buddy.equals(that.buddy) : that.buddy != null) return false;
        if (buddyId != null ? !buddyId.equals(that.buddyId) : that.buddyId != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (buddyId != null ? buddyId.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (buddy != null ? buddy.hashCode() : 0);
        return result;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBuddyId() {
        return buddyId;
    }

    public void setBuddyId(Long buddyId) {
        this.buddyId = buddyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getBuddy() {
        return buddy;
    }

    public void setBuddy(User buddy) {
        this.buddy = buddy;
    }
}
