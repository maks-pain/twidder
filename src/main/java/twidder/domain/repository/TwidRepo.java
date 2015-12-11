package twidder.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twidder.domain.entity.Twid;
import twidder.domain.entity.User;

import java.util.List;

/**
 * Created by maks on 12/11/15.
 */

@Repository
public interface TwidRepo extends JpaRepository<Twid, Long> {

    List<Twid> findAllByOwner(User owner);

}
