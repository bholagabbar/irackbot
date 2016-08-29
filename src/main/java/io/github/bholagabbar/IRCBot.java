package io.github.bholagabbar;

import com.ullink.slack.simpleslackapi.SlackUser;
import org.jibble.pircbot.PircBot;

import static io.github.bholagabbar.Main.slackBot;

public class IRCBot extends PircBot {
    IRCBot(String name) {
        this.setName(name);
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        message = "<irc@" + sender + ">: " + message;
        if (!sender.equals(BotConstants.IRC_BOT_NAME)) {
            //No mention. Normal Message
            if (!message.contains("@" + BotConstants.IRC_BOT_NAME)) {
                System.out.println("1 HERE "+ message);
                if (BotConstants.MODE == 1 || (BotConstants.MODE == 2 && messageContainsSlackUserToSend(message))) {
                    RelayMessage.sendMessageFromIRCToSlack(message);
                }
            } else {
                //Mention found. Reply with users
                System.out.println("2 HERE "+ message);
                if (message.contains("slack_users")) {
                    StringBuilder slackUserListMessage = new StringBuilder();
                    slackUserListMessage.append("Entities on the linked channel are: ");
                    for (SlackUser slackUser : Main.slackBot.session.getUsers()) {
                        slackUserListMessage.append(slackUser.getUserName()+", ");
                    }
                    Main.ircBot.sendMessage(BotConstants.IRC_CHANNEL, slackUserListMessage.toString());
                } else {
                    String lolTp = "I don't read a valid command there. I'm alive and kicking though, if that's what you wanted to know";
                    Main.ircBot.sendMessage(BotConstants.IRC_CHANNEL, lolTp);
                }
            }
        }
    }

    private boolean messageContainsSlackUserToSend(String message) {
        for (SlackUser slackUser: slackBot.session.getUsers()) {
            if(message.contains("@" + slackUser.getUserName())) {
                return true;
            }
        }
        return false;
    }
}