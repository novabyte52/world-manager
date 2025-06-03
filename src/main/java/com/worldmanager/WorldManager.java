package com.worldmanager;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(WorldManager.MODID)
public class WorldManager {
    public static final String MODID = "worldmanager";

    public WorldManager(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(Commands.literal("wm")
                .requires(cs -> cs.hasPermission(2))
                .then(Commands.literal("list").executes(this::listWorlds))
                .then(Commands.literal("create")
                        .then(Commands.argument("name", StringArgumentType.word())
                                .executes(ctx -> createWorld(ctx, StringArgumentType.getString(ctx, "name")))))
                .then(Commands.literal("load")
                        .then(Commands.argument("name", StringArgumentType.word())
                                .executes(ctx -> loadWorld(ctx, StringArgumentType.getString(ctx, "name")))))
                .then(Commands.literal("unload")
                        .then(Commands.argument("name", StringArgumentType.word())
                                .executes(ctx -> unloadWorld(ctx, StringArgumentType.getString(ctx, "name")))))
                .then(Commands.literal("delete")
                        .then(Commands.argument("name", StringArgumentType.word())
                                .executes(ctx -> deleteWorld(ctx, StringArgumentType.getString(ctx, "name")))))
                .then(Commands.literal("enter")
                        .then(Commands.argument("name", StringArgumentType.word())
                                .executes(ctx -> enterWorld(ctx, StringArgumentType.getString(ctx, "name"))))));
    }

    private int listWorlds(CommandContext<CommandSourceStack> ctx) {
        ctx.getSource().sendSuccess(() -> Component.literal("Listing worlds..."), false);
        return 1;
    }

    private int createWorld(CommandContext<CommandSourceStack> ctx, String name) {
        ctx.getSource().sendSuccess(() -> Component.literal("Creating world: " + name), false);
        return 1;
    }

    private int loadWorld(CommandContext<CommandSourceStack> ctx, String name) {
        ctx.getSource().sendSuccess(() -> Component.literal("Loading world: " + name), false);
        return 1;
    }

    private int unloadWorld(CommandContext<CommandSourceStack> ctx, String name) {
        ctx.getSource().sendSuccess(() -> Component.literal("Unloading world: " + name), false);
        return 1;
    }

    private int deleteWorld(CommandContext<CommandSourceStack> ctx, String name) {
        ctx.getSource().sendSuccess(() -> Component.literal("Deleting world: " + name), false);
        return 1;
    }

    private int enterWorld(CommandContext<CommandSourceStack> ctx, String name) {
        ctx.getSource().sendSuccess(() -> Component.literal("Entering world: " + name), false);
        return 1;
    }
}
