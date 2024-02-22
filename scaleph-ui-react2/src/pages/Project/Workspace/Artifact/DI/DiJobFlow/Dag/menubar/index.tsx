import React, {useState} from 'react';
import {Button, Space, Tooltip} from 'antd';
import {CalculatorOutlined, EyeOutlined, SaveOutlined} from '@ant-design/icons';
import {useIntl} from "@umijs/max";
import type {Edge} from '@antv/xflow';
import {useGraphEvent, useGraphInstance, useGraphStore,} from '@antv/xflow';
import {Menubar} from "@antv/x6-react-components";
import {JSONDebugModal} from "@/pages/Project/Workspace/Artifact/DI/DiJobFlow/Dag/menubar/json";
import {SeaTunnelConfModal} from "@/pages/Project/Workspace/Artifact/DI/DiJobFlow/Dag/menubar/seatunnel";
import {Props} from "@/typings";
import {WsArtifactSeaTunnel, WsArtifactSeaTunnelGraphParam, WsDiJob} from "@/services/project/typings";
import {WsArtifactSeaTunnelService} from "@/services/project/WsArtifactSeaTunnelService";

const CustomMenubar: React.FC<Props<WsArtifactSeaTunnel>> = ({data}) => {
  const intl = useIntl();
  const graph = useGraphInstance();
  const nodes = useGraphStore((state) => state.nodes);
  const updateEdge = useGraphStore((state) => state.updateEdge);
  const removeNodes = useGraphStore((state) => state.removeNodes);
  const [jsonDebugDrawerSwitch, setJsonDebugDrawerSwitch] = useState<{ visible: boolean; data: null }>({
    visible: false,
    data: null,
  });
  const [seatunnelConfDrawerSwitch, setSeatunnelConfDrawerSwitch] = useState<{ visible: boolean; data: WsDiJob }>({
    visible: false,
    data: data,
  });

  useGraphEvent('node:change:data', ({node}) => {
    if (graph) {
      const edges = graph.getIncomingEdges(node);
      const {status} = node.data;
      edges?.forEach((edge: Edge) => {
        if (status === 'running') {
          updateEdge(edge.id, {
            animated: true,
          });
        } else {
          updateEdge(edge.id, {
            animated: false,
          });
        }
      });
    }
  });

  const onPreview = () => {
    setSeatunnelConfDrawerSwitch({visible: true, data: data})
  };

  const onDebug = () => {
    setJsonDebugDrawerSwitch({visible: true, data: null})
  };

  const onSave = () => {
    const edges: Edge[] = nodes.flatMap((node) => {
      const result: Edge[] = []
      const incomingEdges = graph?.getIncomingEdges(node.id || '');
      if (incomingEdges) {
        result.concat(incomingEdges)
      }
      const outgoingEdges = graph?.getOutgoingEdges(node.id || '');
      if (outgoingEdges) {
        result.concat(outgoingEdges)
      }
      return result
    });

    let param: WsArtifactSeaTunnelGraphParam = {
      id: data.id,
      jobGraph: {
        nodes: nodes,
        edges: edges,
      }
    }
    WsArtifactSeaTunnelService.updateGraph(param)
  };

  return (
    <>
      <Menubar extra={<Space>
        <Tooltip title={intl.formatMessage({id: 'pages.project.di.flow.dag.debug'})}>
          <Button
            size="small"
            icon={<CalculatorOutlined/>}
            onClick={onDebug}
          />
        </Tooltip>
        <Tooltip title={intl.formatMessage({id: 'pages.project.di.flow.dag.preview'})}>
          <Button
            size="small"
            icon={<EyeOutlined/>}
            onClick={onPreview}
          />
        </Tooltip>
        <Tooltip title={intl.formatMessage({id: 'pages.project.di.flow.dag.save'})}>
          <Button
            size="small"
            icon={<SaveOutlined/>}
            onClick={onSave}
          />
        </Tooltip>
      </Space>}/>
      {jsonDebugDrawerSwitch && (
        <JSONDebugModal
          visible={jsonDebugDrawerSwitch.visible}
          onCancel={() => {
            setJsonDebugDrawerSwitch({visible: false, data: null});
          }}
          onVisibleChange={(visible: boolean) => {
            setJsonDebugDrawerSwitch({visible: visible, data: null});
          }}
          data={jsonDebugDrawerSwitch.data}
        />
      )}
      {seatunnelConfDrawerSwitch && (
        <SeaTunnelConfModal
          visible={seatunnelConfDrawerSwitch.visible}
          onCancel={() => {
            setSeatunnelConfDrawerSwitch({visible: false, data: data});
          }}
          onVisibleChange={(visible: boolean) => {
            setSeatunnelConfDrawerSwitch({visible: visible, data: data});
          }}
          data={seatunnelConfDrawerSwitch.data}
        />
      )}
    </>
  );
};

export {CustomMenubar};
