package commands;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
  /**
   * Listens for slash commands and responds accordingly
   */
  @Override
  public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
    String command = event.getName();
    if (command.equals("welcome")) {
      // Run the 'ping' command
      String userTag = event.getUser().getAsTag();
      event.reply("Welcome to the server, **" + userTag + "**!").queue();
    }
    else if (command.equals("roles")) {
      // run the 'roles' command
      event.deferReply().queue();
      String response = "";
      for (Role role : event.getGuild().getRoles()) {
        response += role.getAsMention() + "\n";
      }
      event.getHook().sendMessage(response).queue();
    } else if (command.equals("add")) {
      event.deferReply().queue();
      String response = "";
      //Get message options
      OptionMapping nameOption = event.getOption("name");
      OptionMapping ageOption = event.getOption("age");

      String bioname = nameOption.getAsString();
      String bioage = ageOption.getAsString();
      EmbedBuilder embed = new EmbedBuilder().setTitle(bioname).setDescription(bioage);

      event.getHook().sendMessageEmbeds(embed.build()).queue();
    }
  }

  /**
   * Registers slash commands as GUILD commands (max 100).
   * These commands will update instantly and are great for testing.
   */
  @Override
  public void onGuildReady(@NotNull GuildReadyEvent event) {
    List<CommandData> commandData = new ArrayList<>();
    commandData.add(Commands.slash("welcome", "Get welcomed by the bot"));
    commandData.add(Commands.slash("roles", "Display all roles on the server"));

    OptionData name = new OptionData(OptionType.STRING, "name", "name of person", true);
    OptionData age = new OptionData(OptionType.INTEGER, "age", "age of person", true);
    commandData.add(Commands.slash("add", "Add new biographies").addOptions(name, age));

    event.getGuild().updateCommands().addCommands(commandData).queue();
  }

  /**
   * Registers slash commands as GLOBAL commands (unlimited).
   * These commands may take up to an hour to update.
   */
  @Override
  public void onReady(@NotNull ReadyEvent event) {
    List<CommandData> commandData = new ArrayList<>();
    commandData.add(Commands.slash("welcome", "Get welcomed by the bot"));
    commandData.add(Commands.slash("roles", "Display all roles on the server"));
    event.getJDA().updateCommands().addCommands(commandData).queue();
  }
}
