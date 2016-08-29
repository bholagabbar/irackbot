package io.github.bholagabbar;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;


public class SlackBot {

    public SlackSession session;

    SlackBot() {
        session = SlackSessionFactory.createWebSocketSlackSession(BotConstants.SLACK_TOKEN);
    }

    public void Listen() {
        SlackMessagePostedListener messagePostedListener = new SlackMessagePostedListener() {
            public void onEvent(SlackMessagePosted event, SlackSession session) {
                if (!event.getSender().getUserName().equals(BotConstants.SLACK_BOT_NAME)) {
                    System.out.println("FACK " + Main.slackBot.session.findUserById("U25MZS61W").getRealName());
                    if (!event.getMessageContent().contains("@" + BotConstants.SLACK_BOT_NAME)) {
                        String messageToSendFromSlackToIRC = "<slack@" + event.getSender().getUserName() + ">: " + event.getMessageContent();
                        RelayMessage.sendMessageFromSlackToIRC(messageToSendFromSlackToIRC);
                    } else {
                        if (event.getMessageContent().contains("irc_users")) {
                            System.out.println("YUO HEHREHRER");
                            StringBuilder ircUserList = new StringBuilder();
                            ircUserList.append("Entities on the linked channel are: ");
                            for (org.jibble.pircbot.User user: Main.ircBot.getUsers(BotConstants.IRC_CHANNEL)) {
                                ircUserList.append(user.toString() + ", ");
                            }
                            Main.ircBot.sendMessage(BotConstants.SLACK_CHANNEL, ircUserList.toString());
                        } else {
                            String lolTp = "I don't read a valid command there. I'm alive and kicking though, if that's what you wanted to know";
                            Main.ircBot.sendMessage(BotConstants.SLACK_CHANNEL, lolTp);
                        }
                    }
                }
            }
        };
        session.addMessagePostedListener(messagePostedListener);
    }
}
