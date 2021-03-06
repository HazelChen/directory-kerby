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
package org.apache.kerby.asn1.type;

import org.apache.kerby.asn1.EncodingOption;
import org.apache.kerby.asn1.TaggingOption;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * The ASN1 type interface for all ASN1 types.
 */
public interface Asn1Type {
    /**
     *
     * @return
     */
    public int tagFlags();

    /**
     * Get tag number for the type
     * @return tag number
     */
    public int tagNo();

    /**
     * Set encoding option.
     * See {@link org.apache.kerby.asn1.EncodingOption}.
     * @param encodingOption
     */
    public void setEncodingOption(EncodingOption encodingOption);

    /**
     * Get length of encoding bytes by just calculating without real encoding.
     * Generally it's called to prepare for the encoding buffer.
     * @return length of encoding bytes
     */
    public int encodingLength();

    /**
     * Encode the type, by recursively.
     * @return encoded bytes
     */
    public byte[] encode();

    /**
     * Encode the type, by recursively, using the provided buffer.
     * @param buffer
     */
    public void encode(ByteBuffer buffer);

    /**
     * Decode the content bytes into this type.
     * @param content
     * @throws IOException
     */
    public void decode(byte[] content) throws IOException;

    /**
     * Decode the content bytes into this type.
     * @param content
     * @throws IOException
     */
    public void decode(ByteBuffer content) throws IOException;

    /**
     * Tag and encode this type using the provided tagging option.
     * @param taggingOption
     * @return encoded bytes
     */
    public byte[] taggedEncode(TaggingOption taggingOption);

    /**
     * Tag and encode this type using the provided tagging option.
     * @param taggingOption
     * @return encoded bytes
     */
    public void taggedEncode(ByteBuffer buffer, TaggingOption taggingOption);

    /**
     * Decode the content bytes into this type as it's tagged with the provided
     * tagging option.
     *
     * See {@link org.apache.kerby.asn1.TaggingOption}
     *
     * @param content
     * @param taggingOption
     * @throws IOException
     */
    public void taggedDecode(ByteBuffer content, TaggingOption taggingOption) throws IOException;

    /**
     * Decode the content bytes into this type as it's tagged with the provided
     * tagging option.
     *
     * See {@link org.apache.kerby.asn1.TaggingOption}
     *
     * @param content
     * @param taggingOption
     * @throws IOException
     */
    public void taggedDecode(byte[] content, TaggingOption taggingOption) throws IOException;
}
