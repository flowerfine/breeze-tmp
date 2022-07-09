/**验证码 */
export type AuthCode = {
  uuid: string;
  img: string;
};
/**通用response结构 */
export type ResponseBody<T> = {
  success: boolean;
  data?: T;
  errorCode?: string;
  errorMessage: string;
  showType?: string;
};
export type PageResponse<T> = {
  size: number;
  current: number;
  total: number;
  records: T[];
};

export type TransferData = {
  name: string;
  value: string;
};

export type RegisterInfo = {
  userName: string;
  email: string;
  password: string;
  confirmPassword: string;
  authCode: string;
  uuid: string;
};

/**登录参数 */
export type LoginInfo = {
  userName: string;
  password: string;
  authCode: string;
  uuid: string;
  remember: boolean;
};

export type OnlineUserInfo = {
  userName: string;
  email: string;
  token: string;
  privileges: string[];
  roles: string[];
  expireTime: bigint;
};

export type SoterField = {
  field: string;
  direction: string;
};

/**分页参数 */
export type QueryParam = {
  pageSize?: number;
  current?: number;
  sorter?: SoterField[];
};
/**
 * 枚举的key和value
 */
export type Dict = {
  label?: string;
  value?: string;
};
