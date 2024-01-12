import React from 'react';
import {ProFormGroup, ProFormSelect} from "@ant-design/pro-components";
import {useIntl} from "@umijs/max";
import {DictDataService} from "@/services/admin/dictData.service";
import {DICT_TYPE} from "@/constants/dictType";
import {STEP_ATTR_TYPE} from "@/pages/Project/Workspace/Artifact/DI/DiJobFlow/Dag/node/steps/constant";
import {DsInfoParam} from "@/services/datasource/typings";
import {DsInfoService} from "@/services/datasource/info.service";

const DataSourceItem: React.FC<{ dataSource: string }> = ({dataSource}) => {
  const intl = useIntl();

  return (
    <ProFormGroup>
      <ProFormSelect
        name={"dataSourceType"}
        label={intl.formatMessage({id: 'pages.project.di.step.dataSourceType'})}
        colProps={{span: 6}}
        initialValue={dataSource}
        fieldProps={{
          disabled: true
        }}
        request={() => DictDataService.listDictDataByType2(DICT_TYPE.datasourceType)}
      />
      <ProFormSelect
        name={STEP_ATTR_TYPE.dataSource}
        label={intl.formatMessage({id: 'pages.project.di.step.dataSource'})}
        rules={[{required: true}]}
        colProps={{span: 18}}
        dependencies={["dataSourceType"]}
        request={((params, props) => {
          const param: DsInfoParam = {
            name: params.keyWords,
            dsType: params.dataSourceType
          };
          return DsInfoService.list(param).then((response) => {
            return response.data.map((item) => {
              return {label: item.name, value: item.id, item: item};
            });
          });
        })}
      />
    </ProFormGroup>
  );
}

export default DataSourceItem;
