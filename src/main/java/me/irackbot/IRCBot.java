package me.irackbot;

import org.jibble.pircbot.PircBot;

class IRCBot extends PircBot {
    IRCBot(String name) {
        this.setName(name);
    }
}