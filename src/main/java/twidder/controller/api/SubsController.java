package twidder.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import twidder.domain.entity.Subscription;
import twidder.domain.entity.User;
import twidder.service.SubscriptionService;
import twidder.service.UserService;

import java.security.Principal;
import java.util.List;

/**
 * Created by maks on 12/14/15
 */

@Controller
public class SubsController {

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    UserService userService;

    @MessageMapping("/subs/create")
    @SendToUser("/topic/subs/create")
    public Subscription create(Subscription message, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        System.out.println("\n>>>\t/subs/create sub: " + message);
        return subscriptionService.subscribe(user, message.getBuddy());

    }

    @MessageMapping("/subs/get")
    @SendToUser("/topic/subs/get")
    public List<Subscription> getSubsForUser(User user) {
        System.out.println("\n>>>\t/subs/get for user: " + user);
        return subscriptionService.getUserSubscriptions(user);
    }

    @MessageMapping("/subs/remove")
    @SendToUser("/topic/subs/remove")
    public Subscription remove(Subscription message) {
        System.out.println("\n>>>\t/subs/remove sub: " + message);
        //TODO swith unsubscribe to use subscription object
        return subscriptionService.unSubscribe(message.getUser(), message.getBuddy());
    }


}
