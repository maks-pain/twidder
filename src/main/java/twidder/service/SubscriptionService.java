package twidder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twidder.domain.entity.Subscription;
import twidder.domain.entity.User;
import twidder.domain.repository.SubscriptionRepo;

import java.util.List;

/**
 * Created by maks on 12/14/15
 */

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    public Subscription subscribe(User user, User buddy) {
        //TODO create findByUserIdAndBuddyId method in repo...
        List<Subscription> existingSubs = subscriptionRepo.findByUserId(user.getId());
        if (existingSubs != null) {
            for (Subscription sub : existingSubs) {
                if (sub.getBuddy().equals(buddy)) {
                    System.out.println("\n>>>\tThere is already such subscription: " + sub);
                    return sub;
                }
            }
        }

        System.out.println("\n>>>\tCreating new subscription for user " + user + " and buddy " + buddy);

        Subscription newSub = new Subscription();
        newSub.setUser(user);
        newSub.setBuddy(buddy);
        return subscriptionRepo.save(newSub);
    }

    public Subscription unSubscribe(User user, User buddy) {
        List<Subscription> existingSubs = subscriptionRepo.findByUserId(user.getId());
        if (existingSubs != null) {
            for (Subscription sub : existingSubs) {
                if (sub.getBuddy().equals(buddy)) {
                    System.out.println("\n>>>\tThere is already such subscription: " + sub);
                    subscriptionRepo.delete(sub);
                    System.out.println("\n>>>\tRemoving subscription: " + sub);
                    return sub;
                }
            }
        }
        System.out.println("\n>>>\tThere is no such subscription!");
        return null;
    }

    public List<Subscription> getUserSubscriptions(User user) {
        List<Subscription> existingSubs = subscriptionRepo.findByUserId(user.getId());
        if (existingSubs != null){
            return existingSubs;
        }
        System.out.println("\n>>>\tThe user " + user + " does not have any subscriptions!");
        return null;
    }


}
