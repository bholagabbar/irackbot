package me.irackbot;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;


public class SlackBot {

    SlackSession session;

    SlackBot() {
        session =  SlackSessionFactory.createWebSocketSlackSession("xoxb-73747890064-Hl1brbljwjfxQZYkDHUROFEQ");
    }

    public void Listen() {
        SlackMessagePostedListener messagePostedListener = new SlackMessagePostedListener() {
            public void onEvent(SlackMessagePosted event, SlackSession session) {
                if(!event.getSender().getUserName().equals("irackbot-test")) {
                    String messageToSendFromSlackToIRC = "<"+event.getSender().getUserName()+">: " + event.getMessageContent();
                    System.out.println(messageToSendFromSlackToIRC);
                    Main.sendMessageFromSlackToIRC(messageToSendFromSlackToIRC);
                }
            }
        };
        session.addMessagePostedListener(messagePostedListener);
    }

}
