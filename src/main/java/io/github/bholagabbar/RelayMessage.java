package io.github.bholagabbar;

import com.ullink.slack.simpleslackapi.SlackChannel;

import static io.github.bholagabbar.Main.slackChannel;

public class RelayMessage {

    public static void sendGenericMessageFromIRCToSlack(String message) {
        SlackChannel slackChannelToSend = Main.slackBot.session.findChannelByName(slackChannel);
        Main.slackBot.session.sendMessage(slackChannelToSend, message);
    }

    public static void sendGenericMessageFromSlackToIRC (String message) {
        Main.ircBot.sendMessage(Main.ircChannel, message);
    }

}
