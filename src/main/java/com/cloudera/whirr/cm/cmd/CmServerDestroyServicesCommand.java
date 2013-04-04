/**
 * Licensed to Cloudera, Inc. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  Cloudera, Inc. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloudera.whirr.cm.cmd;

import java.io.IOException;

import org.apache.whirr.ClusterControllerFactory;
import org.apache.whirr.state.ClusterStateStoreFactory;

import com.cloudera.whirr.cm.server.CmServerCluster;
import com.cloudera.whirr.cm.server.CmServerCommand;
import com.cloudera.whirr.cm.server.CmServerException;

public class CmServerDestroyServicesCommand extends BaseCommandCmServer {

  public static final String NAME = "destroy-services";
  public static final String DESCRIPTION = "Terminate and cleanup resources for a service.";
  
  public CmServerDestroyServicesCommand() throws IOException {
    this(new ClusterControllerFactory());
  }

  public CmServerDestroyServicesCommand(ClusterControllerFactory factory) {
    this(factory, new ClusterStateStoreFactory());
  }

  public CmServerDestroyServicesCommand(ClusterControllerFactory factory, ClusterStateStoreFactory stateStoreFactory) {
    super(NAME, DESCRIPTION, factory, stateStoreFactory);
  }

  @Override
  public int run(CmServerCluster cluster, CmServerCommand serverCommand) throws CmServerException {
    return serverCommand.command("unconfigure").executeBoolean() ? 0 : -1;
  }

}