package io.github.bholagabbar;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;

import static io.github.bholagabbar.Main.slackBot;


public class SlackBot {

    public SlackSession session;

    SlackBot() {
        session = SlackSessionFactory.createWebSocketSlackSession(BotConstants.SLACK_TOKEN);
    }

    public void Listen() {
        SlackMessagePostedListener messagePostedListener = new SlackMessagePostedListener() {
            public void onEvent(SlackMessagePosted event, SlackSession session) {
                if (!event.getSender().getUserName().equals(BotConstants.SLACK_BOT_NAME)) {
                    // Check if the bot is not mentioned. This means it's an outgoing message
                    if (!event.getMessageContent().contains("@" + slackBot.session.findUserByUserName(BotConstants.SLACK_BOT_NAME).getId())) {
                        String messageToSendFromSlackToIRC = "<slack@" + event.getSender().getUserName() + ">: " + replaceSlackIdsWithUsernames(event.getMessageContent());
                        RelayMessage.sendMessageFromSlackToIRC(messageToSendFromSlackToIRC);
                    } else { //commands to bot
                        if (event.getMessageContent().contains("irc_users")) {
                            slackBot.session.sendMessage(BotConstants.SLACK_CHANNEL_OBJECT, getIRCUserMessageToSendOnSlack());
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
                            slackBot.session.sendMessage(BotConstants.SLACK_CHANNEL_OBJECT, modeMessage);
                            Main.ircBot.sendMessage(BotConstants.IRC_CHANNEL, modeMessage);
                        } else if (event.getMessageContent().contains("ping")) {
                            slackBot.session.sendMessage(BotConstants.SLACK_CHANNEL_OBJECT, BotConstants.SLACK_PING_MSG);
                        } else {
                            slackBot.session.sendMessage(BotConstants.SLACK_CHANNEL_OBJECT, BotConstants.SLACK_TP_MSG);
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
    
    private String replaceSlackIdsWithUsernames(String message) {
        String[] words = message.split(" ");
        for (String ff : words) {
            System.out.println(ff);
        }
        for (String word: words) {
            if (word.contains("<@")) {
                String slackUserId = word.substring(2, word.length()-1).trim();
                String slackUserName = Main.slackBot.session.findUserById(slackUserId).getUserName();
                message = message.replace(word, "@" + slackUserName);
            }
        }
        return message;
    }
    
}
