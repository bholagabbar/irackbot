package io.github.bholagabbar;

public class RelayMessage {

    //Slack Methods

    public static void sendMessageFromIRCToSlack(String message) {
        Main.slackBot.session.sendMessage(BotConstants.SLACK_CHANNEL_OBJECT, message);
    }

    public static void sendSlackUserListToIRC() {

    }

    //IRC Methods

    public static void sendMessageFromSlackToIRC(String message) {
        Main.ircBot.sendMessage(BotConstants.IRC_CHANNEL, message);
    }

    public static void sendIRCUserListToSlack() {

    }

}
