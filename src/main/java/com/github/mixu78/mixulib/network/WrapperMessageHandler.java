package com.github.mixu78.mixulib.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public abstract class WrapperMessageHandler <REQ extends IMessage, REPLY extends IMessage> implements IMessageHandler<REQ, REPLY> {

    @Override
    public REPLY onMessage(REQ message, MessageContext ctx) {

        SyncPacket<REQ, REPLY> syncObject = new SyncPacket<REQ, REPLY>(message, ctx) {
            @Override
            public void run() {
                reply = handleMessage(message, ctx);
            }
        };

        if (ctx.side == Side.CLIENT) {
            syncObject.addClient();
        } else {
            syncObject.addServer();
        }

        //TODO Find a way to handle replies (when needed) because this does not actually work
        return syncObject.reply;
    }

    public abstract REPLY handleMessage(REQ message, MessageContext ctx);
}
