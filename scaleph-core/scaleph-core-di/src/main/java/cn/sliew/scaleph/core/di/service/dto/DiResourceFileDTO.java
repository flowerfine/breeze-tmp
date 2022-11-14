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

package cn.sliew.scaleph.core.di.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cn.hutool.core.util.StrUtil;
import cn.sliew.scaleph.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 数据集成-资源
 * </p>
 *
 * @author liyu
 * @since 2022-04-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DiResourceFile对象", description = "数据集成-资源")
public class DiResourceFileDTO extends BaseDTO {

    private static final long serialVersionUID = 7190312674832816172L;

    @NotNull
    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @NotBlank
    @Length(max = 128)
    @ApiModelProperty(value = "资源名称")
    private String fileName;

    @ApiModelProperty(value = "资源类型")
    private String fileType;

    @ApiModelProperty(value = "资源路径")
    private String filePath;

    @ApiModelProperty(value = "文件大小")
    private Long fileSize;

    public void resolveFileType(String fileName) {
        if (StrUtil.isNotEmpty(fileName)) {
            if (fileName.lastIndexOf('.') != -1) {
                this.fileType = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
            }
        }
    }


}