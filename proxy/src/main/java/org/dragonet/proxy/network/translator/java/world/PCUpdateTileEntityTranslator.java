/*
 * DragonProxy
 * Copyright (C) 2016-2019 Dragonet Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You can view the LICENSE file for more details.
 *
 * https://github.com/DragonetMC/DragonProxy
 */
package org.dragonet.proxy.network.translator.java.world;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUpdateTileEntityPacket;
import com.nukkitx.math.vector.Vector3i;
import com.nukkitx.protocol.bedrock.packet.BlockEntityDataPacket;
import lombok.extern.log4j.Log4j2;
import org.dragonet.proxy.network.session.ProxySession;
import org.dragonet.proxy.network.translator.PacketTranslator;
import org.dragonet.proxy.network.translator.annotations.PCPacketTranslator;
import org.dragonet.proxy.network.translator.types.BlockTranslator;


@Log4j2
@PCPacketTranslator(packetClass = ServerUpdateTileEntityPacket.class)
public class PCUpdateTileEntityTranslator extends PacketTranslator<ServerUpdateTileEntityPacket> {

    @Override
    public void translate(ProxySession session, ServerUpdateTileEntityPacket packet) {
        BlockEntityDataPacket blockEntityDataPacket = new BlockEntityDataPacket();
        blockEntityDataPacket.setBlockPosition(Vector3i.from(packet.getPosition().getX(), packet.getPosition().getY(), packet.getPosition().getZ()));
        blockEntityDataPacket.setData(BlockTranslator.translateTileToBedrock(packet.getNbt()));

        session.sendPacket(blockEntityDataPacket);
    }
}
