package twidder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;
import twidder.domain.entity.Subscription;
import twidder.domain.entity.Twid;
import twidder.domain.entity.User;
import twidder.domain.repository.SubscriptionRepo;
import twidder.domain.repository.TwidRepo;

import java.util.List;

/**
 * Created by maks on 12/14/15
 */

@Service
public class TwidService {

    private final MessageSendingOperations<String> messagingTemplate;
    @Autowired
    private TwidRepo twidRepo;


//    @Autowired
//    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private SubscriptionRepo subsRepo;

    @Autowired
    public TwidService(final MessageSendingOperations<String> mso) {
        this.messagingTemplate = mso;
    }

    public Twid createTwid(Twid t) {
        Twid newTwid = twidRepo.save(t);
        //TODO check returned object
        List<Subscription> subscriptions = subsRepo.findByBuddyId(t.getOwner().getId());
        for (Subscription subscription : subscriptions) {
            String username = subscription.getUser().getUsername();
            messagingTemplate.convertAndSend("/user/" + username + "/topic/twid/all", newTwid);
        }
        return newTwid;
    }

    public Twid removeTwid(Twid t) {
        twidRepo.delete(t);
        //TODO do I need this return value?
        return t;
    }

    public Twid getTwid(Long id) {
        return twidRepo.findOne(id);
    }

    public List<Twid> getUserTwids(User u) {
        return twidRepo.findAllByOwner(u);
    }


}
