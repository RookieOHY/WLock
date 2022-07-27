/*
 * Copyright (C) 2005-present, 58.com.  All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wuba.wlock.server.migrate.handler;

import com.wuba.wlock.server.communicate.registry.RegistryClient;
import com.wuba.wlock.server.migrate.protocol.MigrateCommandDO;
import com.wuba.wlock.server.migrate.protocol.MigrateResult;
import com.wuba.wlock.server.migrate.protocol.MigrateSmCtx;

public abstract class BaseCommandHandler implements CommandHandler{
    
    protected void setMigrateResult(MigrateSmCtx migrateSmCtx, MigrateResult migrateResult) {
        if (migrateSmCtx != null) {
            migrateSmCtx.setMigrateResult(migrateResult);
        }
    }

    protected void uploadMigrateState(MigrateCommandDO migrateCommandDO) {
        RegistryClient.getInstance().uploadMigrateState(migrateCommandDO.getVersion(), migrateCommandDO.getSourceGroupId(), migrateCommandDO.getMigrateType());
    }
}
