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

import org.apache.kerby.kerberos.kerb.client.KrbClient;
import org.junit.After;
import org.junit.Before;

public abstract class KdcTestBase {

    protected String kdcRealm;
    protected String clientPrincipal;
    protected String serverPrincipal;

    protected String hostname = "localhost";
    protected short tcpPort = 8088;
    protected short udpPort = 8089;

    protected TestKdcServer kdcServer;
    protected KrbClient krbClnt;

    @Before
    public void setUp() throws Exception {
        setUpKdcServer();
        setUpClient();
    }

    protected void setUpKdcServer() throws Exception {
        kdcServer = new TestKdcServer();
        kdcServer.setKdcHost(hostname);
        kdcServer.setKdcTcpPort(tcpPort);
        kdcServer.setKdcUdpPort(udpPort);
        kdcServer.init();

        kdcRealm = kdcServer.getKdcRealm();
        clientPrincipal = "drankye@" + kdcRealm;

        serverPrincipal = "test-service/localhost@" + kdcRealm;
        kdcServer.createPrincipals(serverPrincipal);
    }

    protected void setUpClient() throws Exception {
        krbClnt = new KrbClient(hostname, tcpPort);
        krbClnt.setTimeout(5);
        krbClnt.setKdcRealm(kdcServer.getKdcRealm());
    }



    @After
    public void tearDown() throws Exception {
        kdcServer.stop();
    }
}