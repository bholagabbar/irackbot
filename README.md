# irackbot

IrackBot acts as a bridge across Slack and IRC channels, allowing message filtering while keeping all communication public in the spirit of FOSS communication.


#### irackbot is now officially recognised by the Slack API Community! See it listed as the 4th entry on [https://api.slack.com/community#java](https://api.slack.com/community#java)

## Another IRC-Slack bot?

A lot of Open Source communities rely on [IRC ](https://en.wikipedia.org/wiki/Internet_Relay_Chat) for their communication. IRC's great
but let's face it, it doesn't really compare that well to modern team messaging clients like [Slack](https://slack.com/).

* A lot of your conversations are drowned and lost inbetween messages from other users discussing other issues
* It's hard to keep track of what you've discussed and keep a log of messages received.
* Proxy issues with IRC resulting in instant ban from IRC servers when you haven't done s**t (pretty common)

You could be working on a project / involved with this
community and you end up facing all these problems. So being the wannabe developer that you are, you end up building
something that solves all of these problems.

## Uhh, so what's different?

* This bot relays messages from Slack to IRC and vice-versa.<br>
*Well, that's not new.*

* You can mention users on the other platform (from Slack to IRC and vice versa) by just pinging them with their original username.<br>
*Ehh nothing new*

* You can get a list of all the users on the other platform with just a single command.<br>
*Ok not bad..*<br>

* You can configure your bot, with a single ping command, to **filter all incoming IRC communication and include only those messages
where any one of your members on the Slack channel have been mentioned**. Meanwhile, all your communication from Slack is still
relayed to IRC and everyone can see what you're discussing and reach out to you anytime.
<br>
*That's just what I need!*<br>


#### Some regular chat

![Some regular chat](http://i.imgur.com/o5e9xXC.png)


#### A few features

![image](http://i.imgur.com/4J3T3Fl.png)

## Running

#### Requirements: You need to have [Java](https://java.com/en/download/) and [Maven](https://maven.apache.org/install.html) installed and configured.

### Setup

1. Create a Slack Bot for your team channel through the official slack website [by going here](https://www.google.co.in/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=slack%20bot%20for%20my%20team) and clicking on *creating a new bot user*.
2. Generate and copy the Authentication Token you get. Now, create a file called *local_token.txt*(already included in .gitignore) in the root folder and paste this token there for future reference.
3. Once you have created the bot, it should be visible on your Slack Team. Invite it to the channel you are going to use it on.
4. Clone this repository, go to `/src/main/resources` and rename the `config-test.properties` file to `config.properties`. Enter all the configuration details following the template given. Make sure all your details are correct else you'll have an exception thrown later on.
5. Go back to the project root directory and open up your terminal pointing to that directory

### Build and Run
* **Standard**
```
mvn clean install
cd target
java -jar irackbot-1.0.jar
```
* [**Heroku**](https://www.heroku.com/)
```
heroku login
mvn clean install && heroku local #Optional, to test locally
heroku create
git add -A
git commit -m"Added Token for Config"
git push heroku master
heroku ps:scale worker=1
```
* [**Docker**](https://www.docker.com/what-docker)
```
docker build -t irackbot #sudo
docker run irackbot #sudo
```


## Usage

### Modes: What makes this bot different is you can tell it to receive either all messages from IRC *(Mode 1)* or only those where your channel members are mentioned *(Mode 2)*

##### For the examples, I'm assuming my bot is named *irackbot* itself on both channels. Needless to say, you have the choice to keep whatever name suits you through the config file.

* *Get users on Slack From IRC*: `@irackbot slack_users`
* *Get users on IRC From Slack*: `@irackbot irc_users`
* *Ping bot on Slack/IRC*: `@irackbot ping`
* *Change mode on Slack*: `@irackbot change_mode`
* *Ping users from IRC to Slack or vice versa*: `@slack/ircusername[Space *important*], message`

## Credits

This bot was built using the [pircbot](http://www.jibble.org/pircbot.php) library by [Jibble](http://www.jibble.org/) and the [simple-slack-api](https://github.com/Ullink/simple-slack-api) by [Ullink](https://github.com/Ullink). Kudos to them for building these useful tools so effectively with an easy to use API.


## Contributions

Pull requests and Issues are welcome. Please do supplement them with enough information so as to get a clear idea about the same. Also, please do Star this repository if you liked irackbot !

## License

[The MIT License](https://rem.mit-license.org/  )
