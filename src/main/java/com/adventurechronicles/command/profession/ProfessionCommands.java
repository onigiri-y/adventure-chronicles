package com.adventurechronicles.command.profession;

import com.adventurechronicles.profession.Profession;
import com.adventurechronicles.profession.ProfessionRegistry;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public final class ProfessionCommands {

    private ProfessionCommands() {
    }

    public static LiteralArgumentBuilder<ServerCommandSource> create() {

        return CommandManager.literal("profession")
                .then(CommandManager.literal("list")
                        .executes(context -> {
                            var source = context.getSource();
                            ProfessionRegistry.getAll().forEach(profession -> {
                                source.sendFeedback(
                                        () -> Text.translatable(profession.getName()), false
                                );
                            });
                            return 1;
                        })//info.executes
                )//list
                .then(CommandManager.literal("info")



                        .then(CommandManager.literal("lumberjack")
                            .executes(context -> {
                                ServerCommandSource source = context.getSource();
                                source.sendFeedback(
                                        () -> Text.translatable("chat.adventure_chronicles.profession_lumberjack"), false
                                );
                                source.sendFeedback(
                                        () -> Text.translatable("chat.adventure_chronicles.profession_lumberjack_ability"), false
                                );
                                return 1;
                            })//lumberjack.executes
                        )//lumberjack



                        /*.then(CommandManager.literal("lumberjack")
                            .executes(context -> {
                                ServerCommandSource source = context.getSource();
                                source.sendFeedback(
                                        () -> Text.translatable("chat.adventure_chronicles.profession_lumberjack"), false
                                );
                                source.sendFeedback(
                                        () -> Text.translatable("chat.adventure_chronicles.profession_lumberjack_ability"), false
                                );
                                return 1;
                            })//lumberjack.executes
                        )//lumberjack*/



                );//info
    }
}