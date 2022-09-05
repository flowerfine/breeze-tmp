import {PageResponse, ResponseBody} from "@/app.d";
import {request} from "umi";
import {Resource, ResourceListParam} from "./typings";

const url: string = '/api/resource';

export async function supportedResourceTypes() {
  return request<ResponseBody<Array<string>>>(`${url}`, {
    method: 'GET'
  })
}

export async function list(param: ResourceListParam) {
  return request<PageResponse<Resource>>(`${url}/` + param.resourceType, {
    method: 'GET',
    params: param
  })
}
