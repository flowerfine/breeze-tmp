import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {PageResponse, ResponseBody} from '../../data/app.data';
import {FlinkClusterConfig, FlinkDeployConfig} from '../../data/flink.data';

@Injectable({
  providedIn: 'root',
})
export class ClusterConfigService {
  private url = 'api/flink/cluster-config';

  constructor(private http: HttpClient) {
  }

  list(queryParam): Observable<PageResponse<FlinkClusterConfig>> {
    const params: HttpParams = new HttpParams({fromObject: queryParam});
    return this.http.get<PageResponse<FlinkClusterConfig>>(`${this.url}`, {params});
  }

  add(row: FlinkClusterConfig): Observable<ResponseBody<any>> {
    return this.http.put<ResponseBody<any>>(this.url, row);
  }
}
