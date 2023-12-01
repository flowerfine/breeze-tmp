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

package cn.sliew.scaleph.engine.doris.service.resource.template;

import cn.sliew.scaleph.config.resource.ResourceLabels;
import cn.sliew.scaleph.engine.doris.operator.spec.DorisClusterSpec;
import cn.sliew.scaleph.engine.doris.service.dto.WsDorisTemplateDTO;
import cn.sliew.scaleph.kubernetes.resource.ResourceConverter;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import org.springframework.util.StringUtils;

import java.util.Map;

public enum DorisTemplateConverter implements ResourceConverter<WsDorisTemplateDTO, DorisTemplate> {
    INSTANCE;

    @Override
    public DorisTemplate convertTo(WsDorisTemplateDTO source) {
        DorisTemplate template = new DorisTemplate();
        ObjectMetaBuilder builder = new ObjectMetaBuilder(true);
        String name = StringUtils.hasText(source.getTemplateId()) ? source.getTemplateId() : source.getName();
        builder.withName(name);
        builder.withNamespace(source.getNamespace());
        builder.withLabels(Map.of(ResourceLabels.SCALEPH_LABEL_NAME, source.getName()));
        template.setMetadata(builder.build());
        DorisClusterSpec spec = new DorisClusterSpec();
        spec.setFeSpec(source.getFeSpec());
        spec.setBeSpec(source.getBeSpec());
        spec.setCnSpec(source.getCnSpec());
        spec.setBrokerSpec(source.getBrokerSpec());
        spec.setAdminUser(source.getAdmin());
        template.setSpec(spec);
        return template;
    }

    @Override
    public WsDorisTemplateDTO convertFrom(DorisTemplate target) {
        WsDorisTemplateDTO dto = new WsDorisTemplateDTO();
        String name = target.getMetadata().getName();
        if (target.getMetadata().getLabels() != null) {
            Map<String, String> labels = target.getMetadata().getLabels();
            name = labels.computeIfAbsent(ResourceLabels.SCALEPH_LABEL_NAME, key -> target.getMetadata().getName());
        }
        dto.setName(name);
        dto.setTemplateId(target.getMetadata().getName());
        dto.setNamespace(target.getMetadata().getNamespace());

        DorisClusterSpec spec = target.getSpec();
        dto.setFeSpec(spec.getFeSpec());
        dto.setBeSpec(spec.getBeSpec());
        dto.setCnSpec(spec.getCnSpec());
        dto.setBrokerSpec(spec.getBrokerSpec());
        dto.setAdmin(spec.getAdminUser());
        return dto;
    }
}