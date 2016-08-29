package io.github.bholagabbar;

import org.jibble.pircbot.PircBot;

class IRCBot extends PircBot {
    IRCBot(String name) {
        this.setName(name);
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        //Do parsing of username etc, modes etc
        if (!sender.equals(Main.ircBotName)) {
            RelayMessage.sendGenericMessageFromIRCToSlack("<irc@" + sender +">: " + message);
        }
    }

    
}