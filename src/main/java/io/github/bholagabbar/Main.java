package io.github.bholagabbar;

public class Main {

    public static io.github.bholagabbar.IRCBot ircBot;
    public static SlackBot slackBot;

    public static String ircChannel = "#pircbot";
    public static String slackChannel = "bot-test";

    public static String ircBotName = "irack-bot";
    public static String slackBotName = "irack-bot";

    static void parseDetails() {

    }

    private static void setupIRCBot() throws Exception {
        ircBot = new io.github.bholagabbar.IRCBot(ircBotName);
        ircBot.setVerbose(true);
        ircBot.connect("irc.freenode.net");
        ircBot.joinChannel(ircChannel);
    }

    private static void setupSlackBot() throws Exception {
        slackBot = new SlackBot();
        slackBot.session.connect();
        slackBot.session.joinChannel(slackChannel);
        slackBot.Listen();
    }

    public static void main(String[] args) throws Exception {
        parseDetails();
        setupIRCBot();
        setupSlackBot();
    }

}