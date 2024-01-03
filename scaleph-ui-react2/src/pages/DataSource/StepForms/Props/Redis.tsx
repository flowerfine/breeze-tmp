import {useIntl, useModel} from "@umijs/max";
import {Form} from "antd";
import {useEffect} from "react";
import {
  ProCard,
  ProFormDependency,
  ProFormDigit,
  ProFormGroup,
  ProFormList,
  ProFormSelect,
  ProFormText,
  ProFormTextArea
} from "@ant-design/pro-components";
import {DsCategoryService} from "@/services/datasource/category.service";
import {DictDataService} from "@/services/admin/dictData.service";
import {DICT_TYPE} from "@/constants/dictType";

const RedisForm: React.FC = () => {
  const intl = useIntl();
  const form = Form.useFormInstance()

  const {dsType} = useModel('dataSourceType', (model) => ({
    dsType: model.dsType
  }));

  useEffect(() => {
    form.setFieldValue("dsTypeId", dsType?.id)
  }, [dsType])

  return (
    <div>
      <ProCard
        headerBordered={true}
        style={{width: 1000}}>
        <ProFormSelect
          name="dsTypeId"
          label={intl.formatMessage({id: 'pages.dataSource.step.props.type'})}
          colProps={{span: 21, offset: 1}}
          disabled
          showSearch={false}
          request={() => {
            return DsCategoryService.listTypes({}).then((response) => {
              if (response.data) {
                return response.data.map((item) => {
                  return {label: item.type.label, value: item.id, item: item};
                });
              }
              return []
            })
          }}
        />
        <ProFormText
          name="version"
          label={intl.formatMessage({id: 'pages.dataSource.step.props.version'})}
          colProps={{span: 21, offset: 1}}
        />
        <ProFormText
          name="name"
          label={intl.formatMessage({id: 'pages.dataSource.step.props.name'})}
          colProps={{span: 21, offset: 1}}
          rules={[{required: true}]}
        />
        <ProFormTextArea
          name="remark"
          label={intl.formatMessage({id: 'pages.dataSource.remark'})}
          colProps={{span: 21, offset: 1}}
          fieldProps={{
            rows: 5
          }}
        />
        <ProFormText
          name="host"
          label={intl.formatMessage({id: 'pages.dataSource.step.props.redis.host'})}
          colProps={{span: 21, offset: 1}}
          rules={[{required: true}]}
          initialValue={"localhost"}
        />
        <ProFormDigit
          name="port"
          label={intl.formatMessage({id: 'pages.dataSource.step.props.redis.port'})}
          colProps={{span: 21, offset: 1}}
          rules={[{required: true}]}
          initialValue={6379}
          fieldProps={{
            min: 0,
            max: 65535
          }}
        />
        <ProFormText
          name="user"
          label={intl.formatMessage({id: 'pages.dataSource.step.props.redis.user'})}
          colProps={{span: 21, offset: 1}}
        />
        <ProFormText
          name="password"
          label={intl.formatMessage({id: 'pages.dataSource.step.props.redis.password'})}
          colProps={{span: 21, offset: 1}}
        />
        <ProFormSelect
          name={"mode"}
          label={intl.formatMessage({id: 'pages.dataSource.step.props.redis.mode'})}
          colProps={{span: 21, offset: 1}}
          request={() => DictDataService.listDictDataByType2(DICT_TYPE.redisMode)}
        />
        <ProFormDependency name={['mode']}>
          {({mode}) => {
            if (mode == 'cluster') {
              return (
                <ProFormGroup
                  label={intl.formatMessage({id: 'pages.dataSource.step.props.redis.nodes'})}
                  colProps={{span: 21, offset: 1}}
                >
                  <ProFormList
                    name={"nodes"}
                    copyIconProps={false}
                    creatorButtonProps={{
                      creatorButtonText: intl.formatMessage({id: 'pages.dataSource.step.props.redis.nodes.list'}),
                      type: 'text',
                    }}
                    colProps={{span: 24, offset: 3}}
                  >
                    <ProFormGroup>
                      <ProFormText
                        name={"node"}
                        colProps={{span: 16, offset: 4}}
                      />
                    </ProFormGroup>
                  </ProFormList>
                </ProFormGroup>
              );
            }
            return <ProFormGroup/>;
          }}
        </ProFormDependency>
      </ProCard>
    </div>
  );
}

export default RedisForm;

