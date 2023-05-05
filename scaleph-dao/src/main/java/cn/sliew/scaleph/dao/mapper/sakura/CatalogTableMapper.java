/*
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

package cn.sliew.scaleph.dao.mapper.sakura;

import cn.sliew.scaleph.common.dict.catalog.CatalogTableKind;
import cn.sliew.scaleph.dao.entity.sakura.CatalogTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogTableMapper extends BaseMapper<CatalogTable> {

    List<CatalogTable> selectByDatabase(@Param("catalog") String catalog, @Param("database") String database, @Param("kind") CatalogTableKind kind);

    int countByDatabase(@Param("catalog") String catalog, @Param("database") String database, @Param("kind") CatalogTableKind kind);

    Optional<CatalogTable> selectByName(@Param("catalog") String catalog, @Param("database") String database, @Param("kind") CatalogTableKind kind, @Param("name") String name);

    int deleteByName(@Param("catalog") String catalog, @Param("database") String database, @Param("kind") CatalogTableKind kind, @Param("name") String name);
}
