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
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.floodgate.api.impl;

import org.geysermc.api.connection.Connection;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.geysermc.floodgate.util.DeviceOs;
import org.geysermc.floodgate.util.InputMode;
import org.geysermc.floodgate.util.LinkedPlayer;
import org.geysermc.floodgate.util.UiProfile;

import java.util.UUID;

public final class FloodgatePlayerWrapper implements FloodgatePlayer {
    private final Connection delegate;

    public FloodgatePlayerWrapper(Connection delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getJavaUsername() {
        return delegate.javaUsername();
    }

    @Override
    public UUID getJavaUniqueId() {
        return delegate.javaUuid();
    }

    @Override
    public UUID getCorrectUniqueId() { // TODO correct?
        return delegate.javaUuid();
    }

    @Override
    public String getCorrectUsername() { // TODO correct?
        return delegate.javaUsername();
    }

    @Override
    public String getVersion() {
        return delegate.version();
    }

    @Override
    public String getUsername() {
        return delegate.bedrockUsername();
    }

    @Override
    public String getXuid() {
        return delegate.xuid();
    }

    @Override
    public DeviceOs getDeviceOs() {
        return DeviceOs.fromId(delegate.platform().ordinal());
    }

    @Override
    public String getLanguageCode() {
        return delegate.languageCode();
    }

    @Override
    public UiProfile getUiProfile() {
        return UiProfile.fromId(delegate.uiProfile().ordinal());
    }

    @Override
    public InputMode getInputMode() {
        return InputMode.fromId(delegate.inputMode().ordinal());
    }

    @Override
    public boolean isFromProxy() {
        return false; //TODO
    }

    @Override
    public LinkedPlayer getLinkedPlayer() {
        if (delegate.isLinked()) {
            return LinkedPlayer.of(delegate.javaUsername(), delegate.javaUuid(), new UUID(0, Long.parseLong(delegate.xuid())));
        }
        return null;
    }

    @Override
    public boolean isLinked() {
        return false;
    }
}
