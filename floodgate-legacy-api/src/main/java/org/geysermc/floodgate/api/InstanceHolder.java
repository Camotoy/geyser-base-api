/*
 * Copyright (c) 2019-2022 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Floodgate
 */

package org.geysermc.floodgate.api;

import lombok.Getter;
import org.geysermc.floodgate.api.inject.PlatformInjector;
import org.geysermc.floodgate.api.link.PlayerLink;
import org.geysermc.floodgate.api.packet.PacketHandlers;

import java.util.UUID;

public final class InstanceHolder {
    @Getter private static FloodgateApi api;
    @Getter private static PlayerLink playerLink;

    @Getter private static PlatformInjector injector;
    @Getter private static PacketHandlers packetHandlers;

    public static boolean set(
            FloodgateApi floodgateApi,
            PlayerLink link,
            PlatformInjector platformInjector,
            PacketHandlers packetHandlers,
            UUID key) {
        api = floodgateApi;
        playerLink = link;
        injector = platformInjector;
        InstanceHolder.packetHandlers = packetHandlers;
        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T extends FloodgateApi> T castApi(Class<T> cast) {
        return (T) api;
    }
}
