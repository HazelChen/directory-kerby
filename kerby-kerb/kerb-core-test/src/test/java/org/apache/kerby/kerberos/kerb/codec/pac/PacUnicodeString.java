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
package org.apache.kerby.kerberos.kerb.codec.pac;

import java.io.IOException;

public class PacUnicodeString {

    private short length;
    private short maxLength;
    private int pointer;

    public PacUnicodeString(short length, short maxLength, int pointer) {
        super();
        this.length = length;
        this.maxLength = maxLength;
        this.pointer = pointer;
    }

    public short getLength() {
        return length;
    }

    public short getMaxLength() {
        return maxLength;
    }

    public int getPointer() {
        return pointer;
    }

    public String check(String string) throws IOException {
        if(pointer == 0 && string != null)
            throw new IOException("pac.string.notempty");

        int expected = length / 2;
        if(string.length() != expected) {
            Object[] args = new Object[]{expected, string.length()};
            throw new IOException("pac.string.invalid.size");
        }

        return string;
    }
}
