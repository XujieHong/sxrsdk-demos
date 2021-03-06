/*
 * Copyright 2015 Samsung Electronics Co., LTD
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.samsungxr.arpet.connection.socket.bluetooth;

import android.support.annotation.NonNull;

import com.samsungxr.arpet.connection.Device;
import com.samsungxr.arpet.connection.OnConnectionListener;
import com.samsungxr.arpet.connection.OnMessageListener;
import com.samsungxr.arpet.connection.socket.IncomingSocketConnectionThread;
import com.samsungxr.arpet.connection.socket.OutgoingSocketConnectionThread;
import com.samsungxr.arpet.connection.socket.SocketConnectionThreadFactory;
import com.samsungxr.arpet.connection.socket.BaseSocketConnectionManager;

public abstract class BTConnectionManager extends BaseSocketConnectionManager {

    private BluetoothSocketConnectionThreadFactory mSocketConnectionThreadFactory =
            new BluetoothSocketConnectionThreadFactory();

    @Override
    protected SocketConnectionThreadFactory getSocketConnectionThreadFactory() {
        return mSocketConnectionThreadFactory;
    }

    private class BluetoothSocketConnectionThreadFactory implements SocketConnectionThreadFactory {

        @Override
        public IncomingSocketConnectionThread createIncomingSocketConnectionThread(
                @NonNull OnMessageListener messageListener,
                @NonNull OnConnectionListener connectionListener) {

            return new BTIncomingSocketConnectionThread(
                    messageListener,
                    connectionListener
            );
        }

        @Override
        public OutgoingSocketConnectionThread createOutgoingSocketConnectionThread(
                @NonNull Device device,
                @NonNull OnMessageListener messageListener,
                @NonNull OnConnectionListener connectionListener) {

            return new BTOutgoingSocketConnectionThread(
                    (BTDevice) device,
                    messageListener,
                    connectionListener);
        }
    }
}
