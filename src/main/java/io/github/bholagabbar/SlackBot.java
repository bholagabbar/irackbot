package io.github.bholagabbar;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;


public class SlackBot {

    public SlackSession session;

    SlackBot() {
        session =  SlackSessionFactory.createWebSocketSlackSession("xoxb-73747890064-lQZ5tpHunQpEs5X344niySNY");
    }

    public void Listen() {
        SlackMessagePostedListener messagePostedListener = new SlackMessagePostedListener() {
            public void onEvent(SlackMessagePosted event, SlackSession session) {
                if(!event.getSender().getUserName().equals(Main.slackBotName)) {
                    String messageToSendFromSlackToIRC = "<slack@"+event.getSender().getUserName()+">: " + event.getMessageContent();
                    RelayMessage.sendGenericMessageFromSlackToIRC(messageToSendFromSlackToIRC);
                }
            }
        };
        session.addMessagePostedListener(messagePostedListener);
    }


}
