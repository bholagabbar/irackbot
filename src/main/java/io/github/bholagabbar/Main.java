package io.github.bholagabbar;

public class Main {

    public static IRCBot ircBot;
    public static SlackBot slackBot;

    private static void setupIRCBot() throws Exception {
        ircBot = new io.github.bholagabbar.IRCBot(BotConstants.IRC_BOT_NAME);
        ircBot.setVerbose(true);
        ircBot.connect(BotConstants.IRC_SERVER);
        ircBot.joinChannel(BotConstants.IRC_CHANNEL);
        ircBot.sendMessage(BotConstants.IRC_CHANNEL, BotConstants.IRC_JOIN_MSG);
    }

    private static void setupSlackBot() throws Exception {
        slackBot = new SlackBot();
        slackBot.session.connect();
        slackBot.session.joinChannel(BotConstants.SLACK_CHANNEL);
        BotConstants.SLACK_CHANNEL_OBJECT = slackBot.session.findChannelByName(BotConstants.SLACK_CHANNEL);
        slackBot.Listen();
        slackBot.session.sendMessage(BotConstants.SLACK_CHANNEL_OBJECT, BotConstants.SLACK_JOIN_MSG);
    }

    public static void main(String[] args) throws Exception {
        new BotConstants();
        setupIRCBot();
        setupSlackBot();
    }

}