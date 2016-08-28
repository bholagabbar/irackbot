package me.irackbot;

public class Main {

    public static IRCBot ircBot;
    public static SlackBot slackBot;
    public static String ircChannel = "#pircbot";

    static void sendMessageFromSlackToIRC(String message) {
        ircBot.sendMessage(ircChannel, message);
    }

    private static void setupIRCBot() throws Exception {
        ircBot = new IRCBot("iRack-bot");
        ircBot.setVerbose(true);
        ircBot.connect("irc.freenode.net");
        ircBot.joinChannel(ircChannel);
    }

    private static void setupSlackBot() throws Exception {
        slackBot = new SlackBot();
        slackBot.session.connect();
        slackBot.session.joinChannel("bot-test");
        slackBot.Listen();
    }

    public static void main(String[] args) throws Exception {
        setupIRCBot();
        setupSlackBot();
    }

}