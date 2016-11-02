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
                    if (!event.getMessageContent().contains("@" + Main.slackBot.session.findUserByUserName(BotConstants.SLACK_BOT_NAME).getId())) {
                        String messageToSendFromSlackToIRC = "<slack@" + event.getSender().getUserName() + ">: " + event.getMessageContent();
                        RelayMessage.sendMessageFromSlackToIRC(messageToSendFromSlackToIRC);
                    } else {
                        if (event.getMessageContent().contains("irc_users")) {
                            Main.slackBot.session.sendMessage(BotConstants.SLACK_CHANNEL_OBJECT, getIRCUserMessageToSendOnSlack());
                        } else if (event.getMessageContent().contains("change_mode") && event.getSender().getUserName().equals(BotConstants.SLACK_MODE_ADMIN)) {
                            int older_mode = BotConstants.MODE;
                            if (BotConstants.MODE == 1) {
                                BotConstants.MODE = 2;
                            } else {
                                BotConstants.MODE = 1;
                            }
                            String modeMessage;
                            if (older_mode == 1) {
                                modeMessage = BotConstants.SLACK_MODE_1_TO_2_MESSAGE;
                            } else {
                                modeMessage = BotConstants.SLACK_MODE_2_TO_1_MESSAGE;
                            }
                            Main.slackBot.session.sendMessage(BotConstants.SLACK_CHANNEL_OBJECT, modeMessage);
                            Main.ircBot.sendMessage(BotConstants.IRC_CHANNEL, modeMessage);
                        } else {
                            Main.slackBot.session.sendMessage(BotConstants.SLACK_CHANNEL_OBJECT, BotConstants.SLACK_TP_MSG);
                        }
                    }
                }
            }
        };
        session.addMessagePostedListener(messagePostedListener);
    }

    public String getIRCUserMessageToSendOnSlack() {
        StringBuilder ircUserList = new StringBuilder();
        ircUserList.append("Entities on the IRC channel '" + BotConstants.IRC_CHANNEL + "' are: ");
        for (org.jibble.pircbot.User user : Main.ircBot.getUsers(BotConstants.IRC_CHANNEL)) {
            ircUserList.append(user.toString() + ", ");
        }
        return ircUserList.toString();
    }
}
