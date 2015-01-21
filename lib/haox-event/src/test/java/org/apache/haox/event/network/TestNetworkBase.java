/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package org.apache.haox.event.network;

import org.apache.haox.event.EventType;
import org.apache.haox.transport.tcp.DecodingCallback;
import org.apache.haox.transport.tcp.StreamingDecoder;

import java.nio.ByteBuffer;

public class TestNetworkBase {
    protected String serverHost = "127.0.0.1";
    protected short tcpPort = 8183;
    protected short udpPort = 8184;
    protected String TEST_MESSAGE = "Hello world!";
    protected String clientRecvedMessage;

    protected enum TestEventType implements EventType {
        FINISHED
    }

    protected String recvBuffer2String(ByteBuffer buffer) {
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return new String(bytes);
    }

    protected StreamingDecoder createStreamingDecoder() {
        return new StreamingDecoder() {
            @Override
            public void decode(ByteBuffer streamingBuffer, DecodingCallback callback) {
                int expectedMessageLength = TEST_MESSAGE.getBytes().length;
                if (streamingBuffer.remaining() >= expectedMessageLength) {
                    callback.onMessageComplete(expectedMessageLength);
                } else {
                    callback.onMoreDataNeeded(expectedMessageLength);
                }
            }
        };
    }
}
