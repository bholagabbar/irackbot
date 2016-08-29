package io.github.bholagabbar;

import com.ullink.slack.simpleslackapi.SlackChannel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BotConstants {

    public static String IRC_SERVER;
    public static String IRC_BOT_NAME;
    public static String IRC_CHANNEL;

    public static String SLACK_TOKEN;
    public static String SLACK_CHANNEL;
    public static String SLACK_BOT_NAME;

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

            SLACK_TOKEN = prop.getProperty("slackToken");
            SLACK_CHANNEL = prop.getProperty("slackChannel");
            SLACK_BOT_NAME = prop.getProperty("slackBotName");

            MODE = Integer.parseInt(prop.getProperty("mode"));

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
    }
}