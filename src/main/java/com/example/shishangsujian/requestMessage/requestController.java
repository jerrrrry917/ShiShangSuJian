package com.example.shishangsujian.requestMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/request")
public class requestController {
    @Autowired
    private requestService requestService;

    @GetMapping("/success")
    public void requestSuccess(@RequestParam long id){
        requestService.requestSuccess(id);
    }

    @GetMapping("/check")
    public int checkRequest(@RequestParam long id){
        return requestService.checkRequest(id);
    }

    @GetMapping("/send")
    public String sendSubscriptionMessage(@RequestParam long id,
                                          @RequestParam String templateId,
                                          @RequestParam String storeName,
                                          @RequestParam String appointmentItem,
                                          @RequestParam String cancelTime) {
        try {
            requestService.sendSubscriptionMessage(id, templateId, storeName, appointmentItem, cancelTime);
            requestService.sentReset(id);
            return "Message sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending message: " + e.getMessage();
        }
    }
}
