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
package org.apache.kerby.kerberos.kerb.server;

import org.apache.kerby.config.Conf;
import org.apache.kerby.kerberos.kerb.common.KrbConfHelper;
import org.apache.kerby.kerberos.kerb.spec.common.EncryptionType;

import java.util.List;

/**
 * Kerb KDC side configuration API.
 */
public class KdcConfig extends Conf {

    public boolean enableDebug() {
        return getBoolean(KdcConfigKey.KRB_DEBUG);
    }

    public String getKdcServiceName() {
        return getString(KdcConfigKey.KDC_SERVICE_NAME);
    }

    public String getKdcHost() {
        return KrbConfHelper.getStringUnderSection(this, KdcConfigKey.KDC_HOST);
    }

    public int getKdcPort() {
        Integer kdcPort =  KrbConfHelper.getIntUnderSection(this,
                KdcConfigKey.KDC_PORT);
        return kdcPort.intValue();
    }

    public int getKdcTcpPort() {
        Integer kdcTcpPort =  KrbConfHelper.getIntUnderSection(this,
                KdcConfigKey.KDC_TCP_PORT);
        if (kdcTcpPort > 0) {
            return kdcTcpPort.intValue();
        }
        return getKdcPort();
    }

    /**
     * Is to allow UDP for KDC
     * @return true to allow UDP, false otherwise
     */
    public boolean allowKdcUdp() {
        return getBoolean(KdcConfigKey.KDC_ALLOW_UDP);
    }

    public int getKdcUdpPort() {
        Integer kdcUdpPort = KrbConfHelper.getIntUnderSection(this,
                KdcConfigKey.KDC_UDP_PORT);
        if (kdcUdpPort > 0) {
            return kdcUdpPort.intValue();
        }
        return getKdcPort();
    }

    public String getKdcRealm() {
        return KrbConfHelper.getStringUnderSection(this, KdcConfigKey.KDC_REALM);
    }

    public String getKdcDomain() {
        return getString(KdcConfigKey.KDC_DOMAIN);
    }

    public boolean isPreauthRequired() {
        return getBoolean(KdcConfigKey.PREAUTH_REQUIRED);
    }

    public String getTgsPrincipal() {
        return getString(KdcConfigKey.TGS_PRINCIPAL);
    }

    public long getAllowableClockSkew() {
        return getLong(KdcConfigKey.ALLOWABLE_CLOCKSKEW);
    }

    public boolean isEmptyAddressesAllowed() {
        return getBoolean(KdcConfigKey.EMPTY_ADDRESSES_ALLOWED);
    }

    public boolean isForwardableAllowed() {
        return getBoolean(KdcConfigKey.FORWARDABLE_ALLOWED);
    }

    public boolean isPostdatedAllowed() {
        return getBoolean(KdcConfigKey.POSTDATED_ALLOWED);
    }

    public boolean isProxiableAllowed() {
        return getBoolean(KdcConfigKey.PROXIABLE_ALLOWED);
    }

    public boolean isRenewableAllowed() {
        return getBoolean(KdcConfigKey.RENEWABLE_ALLOWED);
    }

    public long getMaximumRenewableLifetime() {
        return getLong(KdcConfigKey.MAXIMUM_RENEWABLE_LIFETIME);
    }

    public long getMaximumTicketLifetime() {
        return getLong(KdcConfigKey.MAXIMUM_TICKET_LIFETIME);
    }

    public long getMinimumTicketLifetime() {
        return getLong(KdcConfigKey.MINIMUM_TICKET_LIFETIME);
    }

    public List<EncryptionType> getEncryptionTypes() {
        List<String> eTypes = getList(KdcConfigKey.ENCRYPTION_TYPES);
        return KrbConfHelper.getEncryptionTypes(eTypes);
    }

    public boolean isPaEncTimestampRequired() {
        return getBoolean(KdcConfigKey.PA_ENC_TIMESTAMP_REQUIRED);
    }

    public boolean isBodyChecksumVerified() {
        return getBoolean(KdcConfigKey.VERIFY_BODY_CHECKSUM);
    }

    public String getDefaultLoggingLocation() {
        return KrbConfHelper.getStringUnderSection(this, KdcConfigKey.DEFAULT);
    }

    public String getKdcLoggingLocation() {
        return KrbConfHelper.getStringUnderSection(this, KdcConfigKey.KDC);
    }

    public String getAdminLoggingLocation() {
        return KrbConfHelper.getStringUnderSection(this,
                KdcConfigKey.ADMIN_SERVER);
    }

    public boolean isRestrictAnonymousToTgt() {
        return KrbConfHelper.getBooleanUnderSection(this,
                KdcConfigKey.RESTRICT_ANONYMOUS_TO_TGT);
    }

    public int getKdcMaxDgramReplySize() {
        return KrbConfHelper.getIntUnderSection(this,
                KdcConfigKey.KDC_MAX_DGRAM_REPLY_SIZE);
    }
}
