package twidder.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import twidder.domain.entity.User;
import twidder.service.UserService;

import java.util.List;

/**
 * Created by maks on 12/14/15
 */

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @MessageMapping("/user/create")
    @SendTo("/topic/user/create")
    public User create(User user) {
        System.out.println("\n>>>\t/user/create " + user);
        return userService.createUser(user);
    }

    //TODO check possibility to use {@PathVariable}
    @MessageMapping("/user/get")
    @SendTo("/topic/user/get")
    public User get(User user) {
        System.out.println("\n>>>\t/user/get " + user);
        return userService.getUserByName(user.getUsername());
    }

    @MessageMapping("/user/all")
    @SendTo("/topic/user/all")
    public List<User> getAll() {
        System.out.println("\n>>>\t/user/all");
        return userService.getAllUsers();
    }


}
