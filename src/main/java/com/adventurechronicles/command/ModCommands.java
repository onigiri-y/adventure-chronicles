package com.adventurechronicles.command;

import com.adventurechronicles.command.profession.ProfessionCommands;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;

public final class ModCommands {

    private ModCommands() {
    }

    public static void register() {
        CommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess, environment) -> {
                    dispatcher.register(
                            CommandManager.literal("ac")
                                    .then(ProfessionCommands.create())
                                    //.then(ABC.java.create())  ...
                    );//dispatcher
                });//C.R.CallBack.EVENT
    }//register
}//class M.Commands
