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
package org.apache.haox.asn1.type;

import org.apache.haox.asn1.EncodingOption;
import org.apache.haox.asn1.LimitedByteBuffer;
import org.apache.haox.asn1.TagClass;
import org.apache.haox.asn1.UniversalTag;

import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class Asn1Simple<T> extends AbstractAsn1Type<T> {
    private byte[] bytes;

    public Asn1Simple(UniversalTag tagNo) {
        this(tagNo, null);
    }

    public Asn1Simple(UniversalTag tagNo, T value) {
        super(TagClass.UNIVERSAL, tagNo.getValue(), value);
        setEncodingOption(EncodingOption.PRIMITIVE);
    }

    protected byte[] getBytes() {
        return bytes;
    }

    protected void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public void encode(ByteBuffer buffer) {
        encodeTag(buffer, tagFlags(), tagNo());
        int bodyLen = encodingBodyLength();
        encodeLength(buffer, bodyLen);
        if (bodyLen > 0) {
            buffer.put(encodeBody());
        }
    }

    protected byte[] encodeBody() {
        if (bytes == null) {
            toBytes();
        }
        return bytes;
    }

    @Override
    protected void encodeBody(ByteBuffer buffer) {
        buffer.put(encodeBody());
    }

    @Override
    protected int encodingBodyLength() {
        if (getValue() == null) {
            return 0;
        }
        if (bytes == null) {
            toBytes();
        }
        return bytes.length;
    }

    @Override
    protected void decodeBody(LimitedByteBuffer content) throws IOException {
        byte[] leftBytes = content.readAllLeftBytes();
        if (leftBytes.length > 0) {
            setBytes(leftBytes);
            toValue();
        }
    }

    @Override
    public boolean isConstructed() {
        return false;
    }

    protected void toValue() throws IOException {}

    protected void toBytes() {}

    public static boolean isSimple(int tagNo) {
        return isSimple(UniversalTag.fromValue(tagNo));
    }

    public static boolean isSimple(UniversalTag tagNo) {
        switch (tagNo) {
            case BIT_STRING:
            case BMP_STRING:
            case BOOLEAN:
            case ENUMERATED:
            case GENERALIZED_TIME:
            case GENERAL_STRING:
            case IA5_STRING:
            case INTEGER:
            case NULL:
            case NUMERIC_STRING:
            case OBJECT_IDENTIFIER:
            case OCTET_STRING:
            case PRINTABLE_STRING:
            case T61_STRING:
            case UNIVERSAL_STRING:
            case UTC_TIME:
            case UTF8_STRING:
            case VISIBLE_STRING:
                return true;
            default:
                return false;
        }
    }

    public static Asn1Type createSimple(int tagNo) {
        if (! isSimple(tagNo)) {
            throw new IllegalArgumentException("Not simple type, tag: " + tagNo);
        }
        return createSimple(UniversalTag.fromValue(tagNo));
    }

    public static Asn1Type createSimple(UniversalTag tagNo) {
        if (! isSimple(tagNo)) {
            throw new IllegalArgumentException("Not simple type, tag: " + tagNo);
        }

        switch (tagNo) {
            case BIT_STRING:
                return new Asn1BitString();
            case BMP_STRING:
                return new Asn1BmpString();
            case BOOLEAN:
                return new Asn1Boolean();
            case ENUMERATED:
                return null;
            case GENERALIZED_TIME:
                return new Asn1GeneralizedTime();
            case GENERAL_STRING:
                return new Asn1GeneralString();
            case IA5_STRING:
                return new Asn1IA5String();
            case INTEGER:
                return new Asn1Integer();
            case NULL:
                return new Asn1Null();
            case NUMERIC_STRING:
                return new Asn1NumericsString();
            case OBJECT_IDENTIFIER:
                return new Asn1ObjectIdentifier();
            case OCTET_STRING:
                return new Asn1OctetString();
            case PRINTABLE_STRING:
                return new Asn1PrintableString();
            case T61_STRING:
                return new Asn1T61String();
            case UNIVERSAL_STRING:
                return new Asn1UniversalString();
            case UTC_TIME:
                return new Asn1UtcTime();
            case UTF8_STRING:
                return new Asn1Utf8String();
            case VISIBLE_STRING:
                return new Asn1VisibleString();
            default:
                throw new IllegalArgumentException("Unexpected tag " + tagNo.getValue());
        }
    }
}
