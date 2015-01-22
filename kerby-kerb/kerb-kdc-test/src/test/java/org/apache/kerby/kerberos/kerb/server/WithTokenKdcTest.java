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

import org.apache.kerby.kerberos.kerb.KrbException;
import org.apache.kerby.kerberos.kerb.spec.ticket.ServiceTicket;
import org.apache.kerby.kerberos.kerb.spec.ticket.TgtTicket;
import org.haox.token.KerbToken;
import org.junit.Assert;

public class WithTokenKdcTest extends KdcTestBase {

    private KerbToken token;

    @Override
    protected void setUpKdcServer() throws Exception {
        super.setUpKdcServer();
        kdcServer.createPrincipals(clientPrincipal);
    }

    //@Test
    public void testKdc() throws Exception {
        kdcServer.start();
        Assert.assertTrue(kdcServer.isStarted());
        krbClnt.init();

        TgtTicket tgt = null;
        try {
            tgt = krbClnt.requestTgtTicket(clientPrincipal, token, null);
        } catch (KrbException te) {
            Assert.assertTrue(te.getMessage().contains("timeout"));
            return;
        }
        Assert.assertNull(tgt);

        ServiceTicket tkt = krbClnt.requestServiceTicket(tgt, serverPrincipal, null);
        Assert.assertNull(tkt);
    }
}