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

package cn.sliew.scaleph.plugin.seatunnel.flink.connectors.iotdb.sink;

import cn.sliew.scaleph.plugin.framework.property.*;
import com.fasterxml.jackson.databind.JsonNode;

public enum IoTDBSinkProperties {
    ;

    public static final PropertyDescriptor<String> KEY_DEVICE = new PropertyDescriptor.Builder()
            .name("key_device")
            .description("Specify field name of the IoTDB deviceId in SeaTunnelRow")
            .type(PropertyType.STRING)
            .parser(Parsers.STRING_PARSER)
            .properties(Property.Required)
            .addValidator(Validators.NON_BLANK_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<String> KEY_TIMESTAMP = new PropertyDescriptor.Builder()
            .name("key_timestamp")
            .description("Specify field-name of the IoTDB timestamp in SeaTunnelRow. If not specified, use processing-time as timestamp")
            .type(PropertyType.STRING)
            .parser(Parsers.STRING_PARSER)
            .addValidator(Validators.NON_BLANK_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<JsonNode> KEY_MEASUREMENT_FIELDS = new PropertyDescriptor.Builder()
            .name("key_measurement_fields")
            .description("Specify field-name of the IoTDB measurement list in SeaTunnelRow. If not specified, include all fields but exclude device & timestamp")
            .type(PropertyType.OBJECT)
            .parser(Parsers.JSON_PARSER)
            .addValidator(Validators.NON_BLANK_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<String> STORAGE_GROUP = new PropertyDescriptor.Builder()
            .name("storage_group")
            .description("Specify device storage group(path prefix)")
            .type(PropertyType.STRING)
            .parser(Parsers.STRING_PARSER)
            .addValidator(Validators.NON_BLANK_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Integer> BATCH_SIZE = new PropertyDescriptor.Builder()
            .name("batch_size")
            .description("the batch size for writing data")
            .type(PropertyType.INT)
            .parser(Parsers.INTEGER_PARSER)
            .defaultValue(1024)
            .addValidator(Validators.POSITIVE_INTEGER_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Integer> MAX_RETRIES = new PropertyDescriptor.Builder<Integer>()
            .name("max_retries")
            .description("The number of retries to flush failed")
            .type(PropertyType.INT)
            .parser(Parsers.INTEGER_PARSER)
            .addValidator(Validators.POSITIVE_INTEGER_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Integer> RETRY_BACKOFF_MULTIPLIER_MS = new PropertyDescriptor.Builder<Integer>()
            .name("retry_backoff_multiplier_ms")
            .description("Using as a multiplier for generating the next delay for backoff")
            .type(PropertyType.INT)
            .parser(Parsers.INTEGER_PARSER)
            .addValidator(Validators.POSITIVE_INTEGER_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Integer> MAX_RETRY_BACKOFF_MS = new PropertyDescriptor.Builder<Integer>()
            .name("max_retry_backoff_ms")
            .description("The amount of time to wait before attempting to retry a request to IoTDB")
            .type(PropertyType.INT)
            .parser(Parsers.INTEGER_PARSER)
            .addValidator(Validators.POSITIVE_INTEGER_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Integer> DEFAULT_THRIFT_BUFFER_SIZE = new PropertyDescriptor.Builder()
            .name("default_thrift_buffer_size")
            .description("Thrift init buffer size in IoTDB client")
            .type(PropertyType.INT)
            .parser(Parsers.INTEGER_PARSER)
            .addValidator(Validators.POSITIVE_INTEGER_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Integer> MAX_THRIFT_FRAME_SIZE = new PropertyDescriptor.Builder()
            .name("max_thrift_frame_size")
            .description("thrift max frame size")
            .type(PropertyType.INT)
            .parser(Parsers.INTEGER_PARSER)
            .addValidator(Validators.POSITIVE_INTEGER_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<String> ZONE_ID = new PropertyDescriptor.Builder()
            .name("zone_id")
            .description("java.time.ZoneId in IoTDB client")
            .type(PropertyType.STRING)
            .parser(Parsers.STRING_PARSER)
            .addValidator(Validators.NON_BLANK_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Boolean> ENABLE_RPC_COMPRESSION = new PropertyDescriptor.Builder()
            .name("enable_rpc_compression")
            .description("Enable rpc compression in IoTDB client")
            .type(PropertyType.BOOLEAN)
            .parser(Parsers.BOOLEAN_PARSER)
            .addValidator(Validators.BOOLEAN_VALIDATOR)
            .validateAndBuild();
    
}
