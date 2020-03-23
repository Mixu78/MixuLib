package com.github.mixu78.mixulib.command;

import net.minecraft.command.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public abstract class CoreCommandBase implements ICommand {

    public static class Level {
        public static final Level ALL = new Level(0, ((server, sender, command) -> true));
        public static final Level OP_OR_SP = new Level(2, ((server, sender, command) -> server.isSinglePlayer() || sender.canUseCommand(2, command.getName())));
        public static final Level OP = new Level(2, ((server, sender, command) -> sender.canUseCommand(2, command.getName())));
        public static final Level STRONG_OP_OR_SP = new Level(4, (server, sender, command) -> server.isSinglePlayer() || sender.canUseCommand(4, command.getName()));
        public static final Level STRONG_OP = new Level(4, (server, sender, command) -> sender.canUseCommand(4, command.getName()));
        public static final Level SERVER = new Level(4, (server, sender, command) -> sender instanceof MinecraftServer);

        public interface PermissionChecker
        {
            boolean checkPermission(MinecraftServer server, ICommandSender sender, ICommand command);
        }

        public final int requiredPermissionLevel;
        public final PermissionChecker permissionChecker;

        public Level(int l, PermissionChecker p)
        {
            requiredPermissionLevel = l;
            permissionChecker = p;
        }
    }

    private final String name;
    public final Level level;

    public CoreCommandBase(String name, Level permLevel) {
        this.name = name;
        this.level = permLevel;
    }

    @Override
    public String getName() { return name; }

    @Override
    public int compareTo(ICommand o) {
        return getName().compareTo(o.getName());
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands." + getName() + ".usage";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        checkArgs(wantedArgs(), args, sender);
        handleCommand(this, sender, args, server);
    }

    public abstract void handleCommand(ICommand command, ICommandSender sender, String[] args, MinecraftServer server);

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return level.permissionChecker.checkPermission(server, sender, this);
    }

    public void checkArgs(int wantedArgs, String[] args, ICommandSender sender) throws CommandException {
        if (args.length < wantedArgs) {
            throw new WrongUsageException(getUsage(sender));
        }
    }

    @Override
    @Nonnull
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    public abstract int wantedArgs();
}
