/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.whirr.service.druid;

import org.apache.commons.configuration.Configuration;
import org.apache.whirr.ClusterSpec;
import org.apache.whirr.service.ClusterActionEvent;

import java.io.IOException;

import static org.jclouds.scriptbuilder.domain.Statements.call;

public class DruidMySQLClusterActionHandler extends DruidClusterActionHandler {
    public static final String ROLE = "druid-mysql";
    public static final Integer PORT = 3306;

    @Override
    public String getRole() {
        return ROLE;
    }

    public Integer getPort() {
        return PORT;
    }

    @Override
    protected void beforeBootstrap(ClusterActionEvent event) throws IOException {
        ClusterSpec clusterSpec = event.getClusterSpec();
        Configuration conf = getConfiguration(clusterSpec);

        addStatement(event, call("configure_hostnames"));
        addStatement(event, call("install_mysql")); // installed/run via apt-get
    }
    protected void beforeConfigure(ClusterActionEvent event) throws IOException {
        // No-op
    }
}
