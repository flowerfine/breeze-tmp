package cn.sliew.scaleph.system.dict.job;

import cn.sliew.scaleph.system.dict.DictDefinition;
import cn.sliew.scaleph.system.dict.DictInstance;
import cn.sliew.scaleph.system.dict.DictType;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DataSourceType implements DictInstance {

    JDBC("JDBC", "JDBC"),
    MYSQL("Mysql", "Mysql"),
    ORACLE("Oracle", "Oracle"),
    POSTGRESQL("PostGreSQL", "PostGreSQL"),
    KAFKA("Kafka", "Kafka"),
    DORIS("Doris", "Doris"),
    CLICKHOUSE("ClickHouse", "ClickHouse"),
    ELASTICSEARCH("Elasticsearch", "Elasticsearch"),
    DRUID("Druid", "Druid"),
    ;

    @JsonValue
    @EnumValue
    private String code;
    private String value;

    DataSourceType(String code, String value) {
        this.code = code;
        this.value = value;
    }


    @Override
    public DictDefinition getDefinition() {
        return DictType.DATASOURCE_TYPE;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
