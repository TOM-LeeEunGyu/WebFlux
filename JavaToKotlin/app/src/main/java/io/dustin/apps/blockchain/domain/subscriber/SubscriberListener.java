//package com.mysite.sbb.subscriber;
//
//import io.rigo.sdk.subscriber.Subscriber;
//import io.rigo.sdk.subscriber.callback.SubscriberCallback;
//import io.rigo.sdk.subscriber.code.DefaultEventType;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SubscriberListener {
//
//    @Value("${rigo.node.socket}")
//    private String websocketUrl;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void newBlockSubscribe() throws InterruptedException {
//        Subscriber subscriber = new Subscriber(websocketUrl);
//        SubscriberCallback callback = (message) -> {
//            System.out.println(message);
//        };
//        subscriber.start(DefaultEventType.NEW_BLOCK, callback);
//    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void txSubscribe() throws InterruptedException {
//        Subscriber subscriber = new Subscriber(websocketUrl);
//        SubscriberCallback callback = (message) -> {
//            System.out.println(message);
//        };
//        subscriber.start(DefaultEventType.TX, callback);
//    }
//
//}
