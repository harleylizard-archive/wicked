package com.harleylizard.wicked.common.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public final class CreateStirringPotMessage implements IMessage, IMessageHandler<CreateStirringPotMessage, IMessage> {
    private int x;
    private int y;
    private int z;

    public CreateStirringPotMessage() {
    }

    public CreateStirringPotMessage(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    @Override
    public IMessage onMessage(CreateStirringPotMessage message, MessageContext ctx) {
        int x = message.x;
        int y = message.y;
        int z = message.z;

        World world = Minecraft.getMinecraft().theWorld;
        world.playSound(x + 0.5F, y, z + 0.5F, "wicked:block.create_stirring_pot", 1.0F, 1.0F, false);
        return null;
    }
}
