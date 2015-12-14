package twidder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twidder.domain.entity.Twid;
import twidder.domain.entity.User;
import twidder.domain.repository.UserRepo;

/**
 * Created by maks on 12/14/15
 */

@Component
public class RandomDataGenerator implements ApplicationListener<BrokerAvailabilityEvent> {

    private final MessageSendingOperations<String> mso;

    @Autowired
    UserRepo userRepo;

    @Autowired
    public RandomDataGenerator(final MessageSendingOperations<String> mso) {
        this.mso = mso;
    }


    @Override
    public void onApplicationEvent(BrokerAvailabilityEvent brokerAvailabilityEvent) {
    }

    @Scheduled(fixedDelay = 30000)
    public void sendDataUpdate() {
        System.out.println("\n>>>\tRendom data update");
        Twid twid = new Twid();
        User owner = new User();
        owner.setUsername("-= UFO =-");
        twid.setOwner(owner);
        twid.setText("(___________ aliens are here ___________)");
        this.mso.convertAndSend("/topic/twid/all", twid);
    }


}
