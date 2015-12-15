package twidder.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import twidder.domain.entity.Twid;
import twidder.service.TwidService;
import twidder.service.UserService;

import java.security.Principal;
import java.util.List;

/**
 * Created by maks on 12/14/15
 */

@Controller
public class TwidController {

    private final MessageSendingOperations<String> messageSendingOperations;
    //    @Autowired
    TwidService twidService;
    //    @Autowired
    UserService userService;

    @Autowired
    public TwidController(final MessageSendingOperations<String> messageSendingOperations, TwidService twidService, UserService userService
    ) {
        this.messageSendingOperations = messageSendingOperations;
        this.twidService = twidService;
        this.userService = userService;
    }

    @MessageMapping("/twid/create")
    @SendToUser("/topic/twid/create")
    public Twid create(Twid twid, Principal principal) {
        System.out.println("\n>>>\t/twid/create twid: " + twid + " user: " + principal.getName());
        twid.setOwner(userService.getUserByName(principal.getName()));
        return twidService.createTwid(twid);
    }

    @MessageMapping("/twid/remove")
    @SendToUser("/topic/twid/remove")
    public Twid remove(Twid twid/*, Principal principal*/) {
        System.out.println("\n>>>\t/twid/remove twid: " + twid);
        return twidService.removeTwid(twid);
    }

    @MessageMapping("/twid/all")
    @SendToUser("/topic/twid/all")
    public List<Twid> getAll(Principal principal) {
        //TODO this method changed, user was passed before
        System.out.println("\n>>>\t/twid/all for user: " + principal.getName());

        return twidService.getUserTwids(userService.getUserByName(principal.getName()));
    }


}
