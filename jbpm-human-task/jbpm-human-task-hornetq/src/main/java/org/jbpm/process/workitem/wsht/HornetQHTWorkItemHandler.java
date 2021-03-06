/*
 * Copyright 2012 JBoss by Red Hat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.process.workitem.wsht;

import org.jbpm.task.TaskService;
import org.jbpm.task.service.SyncTaskServiceWrapper;
import org.jbpm.task.service.hornetq.AsyncHornetQTaskClient;
import org.jbpm.task.utils.OnErrorAction;
import org.kie.runtime.KnowledgeRuntime;
/**
 *
 * This class provides the default configurations for a HornetQ WorkItem Handler
 */
public class HornetQHTWorkItemHandler extends GenericHTWorkItemHandler{

    private String connectorName = "SyncHornetQHTWorkItemHandler";
    public HornetQHTWorkItemHandler(KnowledgeRuntime session) {
        super(session);
        init();
    }
    
    public HornetQHTWorkItemHandler(KnowledgeRuntime session, boolean owningSessionOnly) {
        super(session, owningSessionOnly);
        init();
    }
    
    public HornetQHTWorkItemHandler(KnowledgeRuntime session, OnErrorAction action) {
        super(session, action);
        init();
    }
    
    public HornetQHTWorkItemHandler(TaskService client, KnowledgeRuntime session) {
        super(client, session);
        init();
    }
    
    public HornetQHTWorkItemHandler(TaskService client, KnowledgeRuntime session, boolean owningSessionOnly) {
        super(client, session, owningSessionOnly);
        init();
    }
    
    public HornetQHTWorkItemHandler(TaskService client, KnowledgeRuntime session, OnErrorAction action) {
        super(client, session, action);
        init();
    }
    
    public HornetQHTWorkItemHandler(String connectorName, TaskService client, KnowledgeRuntime session, OnErrorAction action) {
        super(client, session, action);
        setClient(client);
        this.connectorName = connectorName;
        init();
    }
    public HornetQHTWorkItemHandler(String connectorName, TaskService client, KnowledgeRuntime session, OnErrorAction action, ClassLoader classLoader) {
        super(client, session, action, classLoader);
        setClient(client);
        this.connectorName = connectorName;
        init();
    }

    private void init(){
        if(getClient() == null){
            setClient(new SyncTaskServiceWrapper(new AsyncHornetQTaskClient(this.connectorName)));
        }
        if(getPort() <= 0){
            setPort(5153);
        }
        if(getIpAddress() == null || getIpAddress().equals("")){
            setIpAddress("127.0.0.1");
        }
    }

    public String getConnectorName() {
        return connectorName;
    }

    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }
    
    
   
    
}
