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
package org.dragonet.proxy.network.translator.types;

import com.github.steveice10.mc.protocol.data.message.ChatColor;
import com.github.steveice10.mc.protocol.data.message.ChatFormat;
import com.github.steveice10.mc.protocol.data.message.Message;
import com.github.steveice10.mc.protocol.data.message.TranslationMessage;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

// This class is from the old version of DragonProxy.
// It may need cleaning up.
public class MessageTranslator {

    // TODO: allow translating to Java text
    public static String translate(Message message) {
        JsonParser parser = new JsonParser();
        if (isMessage(message.getText())) {
            JsonObject object = parser.parse(message.getText()).getAsJsonObject();
            message = Message.fromJson(editJson(object));
        }
        StringBuilder build = new StringBuilder(message.getText());
        for (Message msg : message.getExtra()) {
            build.append(toMinecraftFormat(msg.getStyle().getFormats()));
            build.append(toMinecraftColor(msg.getStyle().getColor()));
            if (!(msg.getText() == null))
                build.append(translate(msg));
        }
        return build.toString();
    }

    public static String translate(String message) {
        return translate(Message.fromString(message));
    }

    public static String translationTranslateText(TranslationMessage message) {
        StringBuilder build = new StringBuilder("");
        build.append(toMinecraftFormat(message.getStyle().getFormats()));
        build.append(toMinecraftColor(message.getStyle().getColor()));
        build.append("%");
        build.append(message.getTranslationKey());
        return build.toString();
    }

    public static List<String> translationTranslateParams(Message[] messages) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i<messages.length;i++) {
            if (messages[i] instanceof TranslationMessage) {
                TranslationMessage tmsg = (TranslationMessage) messages[i];
                StringBuilder build = new StringBuilder("");
                build.append("%");
                build.append(tmsg.getTranslationKey());
                strings.add(build.toString());
                if(tmsg.getTranslationKey().equals("commands.gamemode.success.other"))
                    strings.add("");
                for (int j = 0; j < translationTranslateParams(tmsg.getTranslationParams()).size(); j++)
                    strings.add(translationTranslateParams(tmsg.getTranslationParams()).get(j));
            } else {
                StringBuilder build = new StringBuilder("");
                build.append(toMinecraftFormat(messages[i].getStyle().getFormats()));
                build.append(toMinecraftColor(messages[i].getStyle().getColor()));
                build.append(translate(messages[i]));
                strings.add(build.toString());
            }
        }
        return strings;
    }

    public static boolean isMessage(String text) {
        JsonParser parser = new JsonParser();
        try {
            JsonObject object = parser.parse(text).getAsJsonObject();
            Message.fromJson(editJson(object));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static JsonObject editJson(JsonObject object) {
        if (object.has("hoverEvent")) {
            JsonObject sub = (JsonObject) object.get("hoverEvent");
            JsonElement element = sub.get("value");

            if (element instanceof JsonArray) {
                JsonObject newobj = new JsonObject();
                newobj.add("extra", element);
                newobj.addProperty("text", "");
                sub.remove("value");
                sub.add("value", newobj);
            }
        }

        if (object.has("extra")) {
            JsonArray array = object.getAsJsonArray("extra");
            for (int i = 0; i < array.size(); i++)
                if (!(array.get(i) instanceof JsonPrimitive))
                    editJson((JsonObject) array.get(i));
        }

        return object;
    }

    public static String toMinecraftColor(ChatColor color) {
        String base = "\u00a7";
        switch (color) {
            case AQUA:
                base += "b";
                break;
            case BLACK:
                base += "0";
                break;
            case BLUE:
                base += "9";
                break;
            case DARK_AQUA:
                base += "3";
                break;
            case DARK_BLUE:
                base += "1";
                break;
            case DARK_GRAY:
                base += "8";
                break;
            case DARK_GREEN:
                base += "2";
                break;
            case DARK_PURPLE:
                base += "5";
                break;
            case DARK_RED:
                base += "4";
                break;
            case GOLD:
                base += "6";
                break;
            case GRAY:
                base += "7";
                break;
            case GREEN:
                base += "a";
                break;
            case LIGHT_PURPLE:
                base += "d";
                break;
            case RED:
                base += "c";
                break;
            case RESET:
                base += "r";
                break;
            case WHITE:
                base += "f";
                break;
            case YELLOW:
                base += "e";
                break;
            default:
                break;
        }
        return base;
    }

    private static String toMinecraftFormat(List<ChatFormat> formats) {
        StringBuilder superBase = new StringBuilder();
        for (ChatFormat cf : formats) {
            String base = "\u00a7";
            switch (cf) {
                case BOLD:
                    base += "l";
                    break;
                case ITALIC:
                    base += "o";
                    break;
                case OBFUSCATED:
                    base += "k";
                    break;
                case STRIKETHROUGH:
                    base += "m";
                    break;
                case UNDERLINED:
                    base += "n";
                    break;
                default:
                    break;
            }
            superBase.append(base);
        }
        return superBase.toString();
    }
}
