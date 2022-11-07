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

import java.util.stream.Collectors;
import org.geysermc.api.GeyserApiBase;
import org.geysermc.api.connection.Connection;
import org.geysermc.cumulus.form.Form;
import org.geysermc.cumulus.form.util.FormBuilder;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.geysermc.floodgate.api.unsafe.Unsafe;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public final class FloodgateApiWrapper implements FloodgateApi {
    private final GeyserApiBase delegate;

    public FloodgateApiWrapper(GeyserApiBase delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getPlayerPrefix() {
        return delegate.usernamePrefix();
    }

    @Override
    public Collection<FloodgatePlayer> getPlayers() {
        return delegate.onlineConnections()
                .stream()
                .<FloodgatePlayer>map(FloodgatePlayerWrapper::new)
                .collect(Collectors.toList()); // TODO guarantee immutable?
    }

    @Override
    public int getPlayerCount() {
        return delegate.onlineConnectionsCount();
    }

    @Override
    public boolean isFloodgatePlayer(UUID uuid) {
        return delegate.isBedrockPlayer(uuid);
    }

    @Override
    public FloodgatePlayer getPlayer(UUID uuid) {
        Connection connection = delegate.connectionByUuid(uuid);
        if (connection != null) {
            return new FloodgatePlayerWrapper(connection);
        }
        return null;
    }

    @Override
    public UUID createJavaPlayerId(long xuid) {
        return new UUID(0, xuid);
    }

    @Override
    public boolean isFloodgateId(UUID uuid) {
        return uuid.getMostSignificantBits() == 0L; // TODO right?
    }

    @Override
    public boolean sendForm(UUID uuid, Form form) {
        return delegate.sendForm(uuid, form);
    }

    @Override
    public boolean sendForm(UUID uuid, FormBuilder<?, ?, ?> formBuilder) {
        return delegate.sendForm(uuid, formBuilder);
    }

    @Override
    public boolean sendForm(UUID uuid, org.geysermc.cumulus.Form<?> form) {
        return delegate.sendForm(uuid, form.newForm());
    }

    @Override
    public boolean sendForm(UUID uuid, org.geysermc.cumulus.util.FormBuilder<?, ?> formBuilder) {
        return sendForm(uuid, formBuilder.build());
    }

    @Override
    public boolean transferPlayer(UUID uuid, String address, int port) {
        return delegate.transfer(uuid, address, port);
    }

    @Override
    public CompletableFuture<Long> getXuidFor(String gamertag) {
        throw new UnsupportedOperationException(); // TODO
    }

    @Override
    public CompletableFuture<String> getGamertagFor(long xuid) {
        throw new UnsupportedOperationException(); // TODO
    }

    @Override
    public Unsafe unsafe() {
        throw new UnsupportedOperationException(); // TODO
    }
}
