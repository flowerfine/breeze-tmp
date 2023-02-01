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

package cn.sliew.scaleph.api.controller.ws;

import cn.sliew.scaleph.api.annotation.Logging;
import cn.sliew.scaleph.engine.flink.kubernetes.service.WsFlinkKubernetesDeploymentService;
import cn.sliew.scaleph.engine.flink.kubernetes.service.dto.WsFlinkKubernetesDeploymentDTO;
import cn.sliew.scaleph.engine.flink.kubernetes.service.param.WsFlinkKubernetesDeploymentListParam;
import cn.sliew.scaleph.system.snowflake.exception.UidGenerateException;
import cn.sliew.scaleph.system.vo.ResponseVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Flink Kubernetes管理-FlinkDeployment管理")
@RestController
@RequestMapping(path = "/api/flink/kubernetes/deployment")
public class WsFlinkKubernetesDeploymentController {

    @Autowired
    private WsFlinkKubernetesDeploymentService wsFlinkKubernetesDeploymentService;

    @Logging
    @GetMapping
    @ApiOperation(value = "查询 Deployment 列表", notes = "分页查询 Deployment 列表")
    public ResponseEntity<Page<WsFlinkKubernetesDeploymentDTO>> list(@Valid WsFlinkKubernetesDeploymentListParam param) {
        Page<WsFlinkKubernetesDeploymentDTO> page = wsFlinkKubernetesDeploymentService.list(param);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @Logging
    @GetMapping("/{id}")
    @ApiOperation(value = "查询 Deployment", notes = "查询 Deployment")
    public ResponseEntity<ResponseVO<WsFlinkKubernetesDeploymentDTO>> selectOne(@PathVariable("id") Long id) {
        WsFlinkKubernetesDeploymentDTO dto = wsFlinkKubernetesDeploymentService.selectOne(id);
        return new ResponseEntity(ResponseVO.success(dto), HttpStatus.OK);
    }

    @Logging
    @PutMapping
    @ApiOperation(value = "新增 Deployment", notes = "新增 Deployment")
    public ResponseEntity<ResponseVO> insert(@Valid @RequestBody WsFlinkKubernetesDeploymentDTO param) throws UidGenerateException {
        wsFlinkKubernetesDeploymentService.insert(param);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @PostMapping
    @ApiOperation(value = "修改 Deployment", notes = "修改 Deployment")
    public ResponseEntity<ResponseVO> update(@Valid @RequestBody WsFlinkKubernetesDeploymentDTO param) {
        wsFlinkKubernetesDeploymentService.update(param);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除 Deployment", notes = "删除 Deployment")
    public ResponseEntity<ResponseVO> delete(@PathVariable("id") Long id) {
        wsFlinkKubernetesDeploymentService.deleteById(id);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @DeleteMapping("/batch")
    @ApiOperation(value = "批量删除 Deployment", notes = "批量删除 Deployment")
    public ResponseEntity<ResponseVO> deleteBatch(@RequestBody List<Long> ids) {
        wsFlinkKubernetesDeploymentService.deleteBatch(ids);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

}
