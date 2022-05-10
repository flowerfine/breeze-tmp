import { PRIVILEGE_CODE, USER_AUTH } from '../@core/data/app.data';
interface Menu {
  title: string;
  link: string;
  menuIcon?: string;
  pCode?: string;
  children?: Menu[];
}

function hasMenu(code: string, pCodes: string[]): boolean {
  if (pCodes != null && pCodes != undefined) {
    return pCodes.includes(USER_AUTH.roleSysAdmin) || pCodes.includes(code);
  } else {
    return false;
  }
}

export default function (values) {
  let menus: Menu[] = [];
  let pCodes: string[] = JSON.parse(localStorage.getItem(USER_AUTH.pCodes));
  let menuList: Menu[] = [
    {
      title: values['studio']['title'],
      link: '/breeze/studio',
      menuIcon: 'icon icon-build-with-tool',
      pCode: PRIVILEGE_CODE.studioShow,
      children: [],
    },
    {
      title: values['datadev']['title'],
      link: '/breeze/datadev',
      menuIcon: 'icon icon-classroom-post-answers-large',
      pCode: PRIVILEGE_CODE.datadevShow,
      children: [
        {
          title: values['datadev']['datasource'],
          link: '/breeze/datadev/datasource',
          pCode: PRIVILEGE_CODE.datasourceShow,
        },
        {
          title: values['datadev']['project'],
          link: '/breeze/datadev/project',
          pCode: PRIVILEGE_CODE.studioProject,
        },
        {
          title: values['datadev']['resource'],
          link: '/breeze/datadev/resource',
          pCode: PRIVILEGE_CODE.studioResource,
        },
        {
          title: values['datadev']['cluster'],
          link: '/breeze/datadev/cluster',
          pCode: PRIVILEGE_CODE.studioCluster,
        },
      ],
    },
    {
      title: values['stdata']['title'],
      link: '/breeze/stdata',
      menuIcon: 'icon icon-function-guide',
      pCode: PRIVILEGE_CODE.stdataShow,
      children: [
        { title: values['stdata']['dataElement'], link: '/breeze/stdata/dataElement', pCode: PRIVILEGE_CODE.stdataDataElementShow },
        { title: values['stdata']['refdata'], link: '/breeze/stdata/refdata', pCode: PRIVILEGE_CODE.stdataRefDataShow },
        {
          title: values['stdata']['refdataMap'],
          link: '/breeze/stdata/refdataMap',
          pCode: PRIVILEGE_CODE.stdataRefDataMapShow,
        },
        {
          title: values['stdata']['system'],
          link: '/breeze/stdata/system',
          pCode: PRIVILEGE_CODE.stdataSystemShow,
        },
      ],
    },
    {
      title: values['admin']['title'],
      link: '/breeze/admin',
      menuIcon: 'icon icon-setting',
      pCode: PRIVILEGE_CODE.adminShow,
      children: [
        {
          title: values['admin']['user'],
          link: '/breeze/admin/user',
          pCode: PRIVILEGE_CODE.userShow,
        },
        {
          title: values['admin']['privilege'],
          link: '/breeze/admin/privilege',
          pCode: PRIVILEGE_CODE.privilegeShow,
        },
        {
          title: values['admin']['dict'],
          link: '/breeze/admin/dict',
          pCode: PRIVILEGE_CODE.dictShow,
        },
        {
          title: values['admin']['setting'],
          link: '/breeze/admin/setting',
          pCode: PRIVILEGE_CODE.settingShow,
        },
      ],
    },
  ];
  menus = menuList.filter((pm) => {
    return hasMenu(pm.pCode, pCodes);
  });
  for (let x = 0; x < menus.length; x++) {
    const ms = menus[x].children.filter((m) => {
      return hasMenu(m.pCode, pCodes);
    });
    menus[x].children = ms;
  }

  return menus;
}
