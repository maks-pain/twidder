package twidder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import twidder.domain.entity.User;
import twidder.domain.repository.UserRepo;

import java.util.List;

/**
 * Created by maks on 12/14/15.
 */

@Service
public class UserService implements ApplicationListener<BrokerAvailabilityEvent> {


    @Autowired
    private UserRepo userRepo;

    private final MessageSendingOperations<String> messagingTemplate;

    @Autowired
    public UserService(final MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public User getUserByName(String name){
        return userRepo.findByUsername(name);
    }

    public User createUser(User user){
        User u = new User(user.getUsername(),user.getEmail(), new BCryptPasswordEncoder().encode(user.getPasswordHash()),user.getRole());

        User  savedUser = userRepo.save(u);
        sendDataUpdates();
        return savedUser;
    }

    public void sendDataUpdates(){
        System.out.println("\n>>>\tSend data updates for UserService");
        this.messagingTemplate.convertAndSend("/topic/user/all",userRepo.findAll());
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @Override
    public void onApplicationEvent(BrokerAvailabilityEvent brokerAvailabilityEvent) {

    }


}
