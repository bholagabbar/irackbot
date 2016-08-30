package io.github.bholagabbar;

import com.ullink.slack.simpleslackapi.SlackChannel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BotConstants {

    public static String IRC_SERVER;
    public static String IRC_BOT_NAME;
    public static String IRC_CHANNEL;
    public static String IRC_TP_MSG;

    public static String SLACK_TOKEN;
    public static String SLACK_CHANNEL;
    public static String SLACK_BOT_NAME;
    public static String SLACK_MODE_ADMIN;
    public static String SLACK_MODE_1_TO_2_MESSAGE;
    public static String SLACK_MODE_2_TO_1_MESSAGE;
    public static String SLACK_TP_MSG;

    public static int MODE;

    public static SlackChannel SLACK_CHANNEL_OBJECT;

    private static InputStream inputStream;

    public BotConstants() throws IOException {
        try {
            java.util.Properties prop = new java.util.Properties();
            String propFileName = "config.properties";
            inputStream = BotConstants.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            //Set details from config file
            IRC_SERVER = prop.getProperty("ircServer");
            IRC_BOT_NAME = prop.getProperty("ircBotName");
            IRC_CHANNEL = prop.getProperty("ircChannel");
            IRC_TP_MSG = "I don't read a valid command there. I'm alive and kicking though, if that's what you wanted to know";

            SLACK_TOKEN = prop.getProperty("slackToken");
            SLACK_CHANNEL = prop.getProperty("slackChannel");
            SLACK_BOT_NAME = prop.getProperty("slackBotName");
            SLACK_MODE_ADMIN = prop.getProperty("slackModeAdmin");

            SLACK_MODE_1_TO_2_MESSAGE = "Slack Mode has been changed from 1 to 2 by the admin. The Slack users will only receive messages on a mention of their username and otherwise. ALl communication from Slack to IRC is still active";
            SLACK_MODE_2_TO_1_MESSAGE = "Slack Mode has been changed from 2 to 1 by the admin. The Slack users will receive all messages from this channel. ALl communication from Slack to IRC is still active";
            SLACK_TP_MSG = "I don't read a valid command there. I'm alive and kicking though, if that's what you wanted to know. Type ' @" + BotConstants.SLACK_BOT_NAME+" irc_users ' to get a list of users on the linked IRC channel, " + BotConstants.IRC_CHANNEL;

            MODE = Integer.parseInt(prop.getProperty("mode"));

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
    }
}