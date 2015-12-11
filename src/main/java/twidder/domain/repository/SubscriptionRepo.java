package twidder.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twidder.domain.entity.Subscription;
import twidder.domain.entity.User;

import java.util.List;

/**
 * Created by maks on 12/11/15.
 */

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {

    List<Subscription> findByUserId(User user);

    List<Subscription> findByBuddyId(User user);

}
