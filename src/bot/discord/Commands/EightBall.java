package bot.discord.Commands;

import bot.discord.Interfaces.Command;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class EightBall implements Command {

    private static final String HELP = "```USAGE: ~8ball <YES or NO question>```";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getTextChannel().getId().equals("389552298293985304") || !(event.getGuild().getId().equals("307656057830768640"))) {
            TextChannel channel = event.getTextChannel();
            String[] arguments = event.getMessage().getContent().split(" ");

            String[] goodWords = {"can", "am", "is", "are", "do", "will"};
            String firstWord = arguments[1];

            boolean condition = false;

            for (String s : goodWords) {
                if (s.equalsIgnoreCase(firstWord)) {
                    condition = true;
                    break;
                }
            }

            if (condition) {
                int advice = genRandomInt();

                switch (advice) {
                    case 0: {
                        channel.sendMessage("Yes").queue();
                        break;
                    }
                    case 1: {
                        channel.sendMessage("No").queue();
                        break;
                    }
                    case 2: {
                        channel.sendMessage("I'm not sure").queue();
                        break;
                    }
                    case 3: {
                        channel.sendMessage("If they try hard enough").queue();
                        break;
                    }
                    default: {
                        channel.sendMessage("Something went wrong...").queue();
                        break;
                    }
                }
            } else {
                channel.sendMessage("That is not a YES or NO question. Try again :)").queue();
            }
        }
    }

    public static String help() {
        return HELP;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }

    private int genRandomInt() {
        return (int) (Math.random() * 4);
    }
}
