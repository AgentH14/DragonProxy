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
package org.dragonet.proxy.network.session.cache;

import com.github.steveice10.mc.protocol.data.game.setting.Difficulty;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import lombok.*;
import org.dragonet.proxy.data.stats.StatInfo;

import java.util.HashMap;
import java.util.Map;

@Data
public class WorldCache implements Cache {
    @Setter(value = AccessLevel.NONE)
    private Object2IntMap<StatInfo> statistics = new Object2IntArrayMap<>();
    private double rainLevel = 0.0;

    private Difficulty difficulty = Difficulty.EASY;


    @Override
    public void purge() {

    }
}
