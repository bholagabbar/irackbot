package io.github.bholagabbar;

public class RelayMessage {

    public static void sendMessageFromIRCToSlack(String message) {
        Main.slackBot.session.sendMessage(BotConstants.SLACK_CHANNEL_OBJECT, message);
    }

    public static void sendMessageFromSlackToIRC(String message) {
        Main.ircBot.sendMessage(BotConstants.IRC_CHANNEL, message);
    }

}
