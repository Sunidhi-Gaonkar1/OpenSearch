/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.index.store.smbsimplefs;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettings;
import org.elasticsearch.index.settings.IndexSettingsService;
import org.elasticsearch.index.shard.ShardPath;
import org.elasticsearch.index.store.DirectoryService;
import org.elasticsearch.index.store.IndexStore;
import org.elasticsearch.indices.store.IndicesStore;

public class SmbSimpleFsIndexStore extends IndexStore {

    @Inject
    public SmbSimpleFsIndexStore(Index index, @IndexSettings Settings indexSettings,
                                 IndexSettingsService indexSettingsService, IndicesStore indicesStore) {
        super(index, indexSettings, indexSettingsService, indicesStore);
    }

    public Class<? extends DirectoryService> shardDirectory() {
        return SmbSimpleFsDirectoryService.class;
    }

    @Override
    public DirectoryService newDirectoryService(ShardPath path) {
        return new SmbSimpleFsDirectoryService(indexSettings(), this, path);
    }
}

