package com.github.mixu78.mixulib.network;

import com.github.mixu78.mixulib.MixuLib;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.server.FMLServerHandler;

public abstract class SyncPacket <REQ extends IMessage, REPLY extends IMessage> implements Runnable {

    public final REQ message;
    public REPLY reply;

    public MessageContext ctx;

    public SyncPacket(REQ message, MessageContext ctx) {
        this.message = message;
        this.ctx = ctx;
    }

    @Override
    public abstract void run();

    public void addServer() {
        if (ctx.side != Side.SERVER) {
            MixuLib.logger.error("Packet added to wrong side(client to server), message class is "+ message.getClass());
        }
        ctx.getServerHandler().player.getServer().addScheduledTask(this);
    }

    public void addClient() {
        if (ctx.side != Side.CLIENT) {
            MixuLib.logger.error("Packet added to wrong side(server to client), message class is "+ message.getClass());
        }
        Minecraft.getMinecraft().addScheduledTask(this);
    }
}
