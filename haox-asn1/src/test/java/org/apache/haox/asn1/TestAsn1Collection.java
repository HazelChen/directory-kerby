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
package org.apache.haox.asn1;

import org.apache.haox.asn1.type.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestAsn1Collection {
    static String TEST_STR = "Jones";
    static Boolean TEST_BOOL = true;
    static byte[] EXPECTED_BYTES = new byte[] {(byte) 0x30, (byte) 0x0A,
            (byte) 0x16, (byte) 0x05, (byte) 0x4A, (byte) 0x6F, (byte) 0x6E, (byte) 0x65, (byte) 0x73,
            (byte) 0x01, (byte) 0x01, (byte) 0xFF
    };

    @Test
    public void testSequenceEncoding() {
        Asn1Sequence seq = new Asn1Sequence();
        seq.addItem(new Asn1IA5String(TEST_STR));
        seq.addItem(new Asn1Boolean(TEST_BOOL));

        Assert.assertArrayEquals(EXPECTED_BYTES, seq.encode());
    }

    @Test
    public void testSequenceDecoding() throws IOException {
        Asn1Sequence seq = new Asn1Sequence();
        seq.decode(EXPECTED_BYTES);
        AbstractAsn1Type field = (AbstractAsn1Type) seq.getValue().get(0).getValue();
        Assert.assertEquals(TEST_STR, field.getValue());

        field = (AbstractAsn1Type) seq.getValue().get(1).getValue();
        Assert.assertEquals(TEST_BOOL, field.getValue());
    }
}
