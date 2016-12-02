/**
 * This class was created by <KingDGrizzle>. It's distributed as
 * part of the LogBot Port.
 * 
 * File Created @ [1 dec. 2016, 22:24:34 (GMT)]
 */
package kingdgrizzle.logbot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface ICommand {

	boolean called(String[] args, MessageReceivedEvent event);

    void action(String[] args, MessageReceivedEvent event);

    String help();

    void executed(boolean success, MessageReceivedEvent event);

    String getTag();
}
