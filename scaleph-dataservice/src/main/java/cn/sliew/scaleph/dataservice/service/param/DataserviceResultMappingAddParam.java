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

package cn.sliew.scaleph.dataservice.service.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DataserviceResultMappingAddParam {

    @NotNull
    @Schema(description = "请求参数集id")
    private Long resultMapId;

    @NotNull
    @Schema(description = "属性")
    private String property;

    @Schema(description = "java 类型")
    private String javaType;

    @NotNull
    @Schema(description = "列")
    private String column;

    @Schema(description = "jdbc 类型")
    private String jdbcType;

    @Schema(description = "类型转换器")
    private String typeHandler;
}
