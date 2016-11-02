package io.github.bholagabbar;

import static io.github.bholagabbar.BotConstants.*;

public class Main {

    public static IRCBot ircBot;
    public static SlackBot slackBot;

    private static void setupIRCBot() throws Exception {
        ircBot = new io.github.bholagabbar.IRCBot(IRC_BOT_NAME);
        ircBot.setVerbose(true);
        ircBot.connect(BotConstants.IRC_SERVER);
        ircBot.joinChannel(BotConstants.IRC_CHANNEL);
        ircBot.sendMessage(BotConstants.IRC_CHANNEL, BotConstants.IRC_JOIN_MSG);
    }

    private static void setupSlackBot() throws Exception {
        slackBot = new SlackBot();
        slackBot.session.connect();
        slackBot.session.joinChannel(SLACK_CHANNEL);
        //Set Dependant Bot Constants
        BotConstants.setSlackChannelObject(slackBot.session.findChannelByName(SLACK_CHANNEL));
        BotConstants.setSlackTeamName(slackBot.session.getTeam().getName());
        BotConstants.setJoinIRCMessage();
        //Start
        slackBot.Listen();
        slackBot.session.sendMessage(BotConstants.SLACK_CHANNEL_OBJECT, BotConstants.SLACK_JOIN_MSG);
    }

    public static void main(String[] args) throws Exception {
        new BotConstants();
        setupIRCBot();
        setupSlackBot();
    }

}