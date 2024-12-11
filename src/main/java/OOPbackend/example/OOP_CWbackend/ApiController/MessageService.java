package OOPbackend.example.OOP_CWbackend.ApiController;


import OOPbackend.example.OOP_CWbackend.Classes.Configuration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    private SimpMessagingTemplate messagingTemplate;

//    public void SendMessage(String Id, Configuration configuration) {
//        messagingTemplate.convertAndSendToUser(Id,"/configNotification",configuration);
//
//    }

    public void SendMessage(String id, int totalTickets, int maxTickets, int releaseRate, int customerRate) {
        messagingTemplate.convertAndSendToUser(id,"configuration/TotalTickets",totalTickets);
        messagingTemplate.convertAndSendToUser(id,"configuration/MaxTickets",maxTickets);
        messagingTemplate.convertAndSendToUser(id,"configuration/ReleaseRate",releaseRate);
        messagingTemplate.convertAndSendToUser(id,"configuration/CustomerRate",customerRate);

    }
}
