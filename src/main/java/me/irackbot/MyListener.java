package me.irackbot;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;


public class MyListener extends ListenerAdapter {

    static Logger logger = Logger.getLogger(MyListener.class);

    @Override
    public void onGenericMessage(GenericMessageEvent event) {
        //When someone says ?helloworld respond with "Hello World"
        if (event.getMessage().startsWith("?gabbarAaGaya")) {
            event.respond("Kitne aadmi the?");
        }
    }

    public static void main(String[] args) throws Exception {
        //Logger
        BasicConfigurator.configure();

        //Configure IRC bot
        Configuration.Builder builder = new Configuration.Builder();
        builder.setName("bholagabbarTestBot");
        builder.setServerHostname("irc.freenode.net");
        builder.addAutoJoinChannel("#pircbotx");
        builder.addListener(new MyListener());
        Configuration configuration = builder.buildConfiguration();

        //Create bot, apply configuration and connect to server
        PircBotX bot = new PircBotX(configuration);
        bot.startBot();
    }
}