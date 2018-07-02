/*
 * DragonProxy API
 * Copyright © 2016 Dragonet Foundation (https://github.com/DragonetMC/DragonProxy)
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
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dragonet.api.caches;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.ItemStack;
import com.github.steveice10.mc.protocol.data.game.window.WindowType;
import java.util.Map;

/**
 *
 * @author Epic
 */
public interface ICachedWindow {
    /**
     * @return the windowId
     */
    public int getWindowId();

    /**
     * @return the isOpen
     */
    public boolean isIsOpen();

    /**
     * @param isOpen the isOpen to set
     */
    public void setIsOpen(boolean isOpen);

    /**
     * @return the pcType
     */
    public WindowType getPcType();

    /**
     * @return the size
     */
    public int getSize();

    /**
     * @return the title
     */
    public String getTitle();

    /**
     * @param title the title to set
     */
    public void setTitle(String title);

    /**
     * @return the properties
     */
    public Map<Integer, Integer> getProperties();

    /**
     * @return the slots
     */
    public ItemStack[] getSlots();

    /**
     * @param slots the slots to set
     */
    public void setSlots(ItemStack[] slots);
}
