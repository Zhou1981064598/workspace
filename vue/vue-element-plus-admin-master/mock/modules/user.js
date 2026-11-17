let users = [
  {
    id: 1,
    isDeleted: false,
    createBy: "admin",
    updateBy: "admin",
    username: "admin",
    nickname: "系统管理员",
    gender: "男",
    phone: "13888888888",
    email: "88888888@qq.com",
    avatarPath: "/src/assets/logo.svg",
    status: 1,
    token: "admin",
  },
  {
    id: 2,
    isDeleted: false,
    createBy: "admin",
    updateBy: "admin",
    updateTime: "2022-11-02T03:47:50",
    username: "guest",
    nickname: "访客",
    gender: "女",
    email: "starzhangkiss@qq.com",
    avatarPath: "/src/assets/logo.svg",
    status: 1,
    token: "guest",
  },
  {
    id: 3,
    isDeleted: false,
    createBy: "admin",
    updateBy: "admin",
    username: "dufu",
    nickname: "杜甫",
    gender: "男",
    avatarPath: "/src/assets/logo.svg",
    status: 1,
    token: "dufu",
  },
  {
    id: 4,
    isDeleted: false,
    createBy: "admin",
    updateBy: "admin",
    username: "libai",
    nickname: "李白",
    gender: "女",
    avatarPath: "/src/assets/logo.svg",
    status: 1,
    token: "libai",
  },
  {
    id: 5,
    isDeleted: false,
    createBy: "admin",
    updateBy: "admin",
    username: "wanghaoran",
    nickname: "王浩然",
    gender: "男",
    avatarPath: "/src/assets/logo.svg",
    status: 1,
    token: "wanghaoran",
  },
];

export default [
  {
    url: "/login",
    method: "post",
    rawResponse: async (req, res) => {
      let rawData = "";
      let data = null;
      await new Promise((resolve) => {
        req.on("data", (chunk) => {
          rawData += chunk;
        });
        req.on("end", () => {
          for (const user of users) {
            if (rawData.includes(user.username)) {
              data = user;
            }
          }
          resolve();
        });
      });

      res.statusCode = 200;
      res.setHeader("Content-Type", "application/json;charset=utf-8");
      if (data) {
        res.end(
          JSON.stringify({
            code: 2000,
            message: "登录成功",
            data: data.token,
          })
        );
      } else {
        res.end(
          JSON.stringify({
            code: 1101,
            message: "登录失败",
            data: null,
          })
        );
      }
    },
  },

  {
    url: "/logout",
    method: "post",
    response: {
      code: 2000,
      data: null,
      message: "退出成功",
    },
  },

  {
    url: "/rbac/user/info",
    method: "get",
    response: ({ headers }) => {
      for (const user of users) {
        if (user.token == headers.authorization) {
          return {
            code: 2000,
            data: user,
            message: "获取用户信息成功",
          };
        }
      }
      return {
        code: 1001,
        data: null,
        message: "获取用户信息失败",
      };
    },
  },

  {
    url: "/rbac/user/page",
    method: "get",
    response: ({ query }) => {
      return {
        code: 2000,
        data: {
          total: 8,
          currentPage: 1,
          pageSize: 5,
          list: users,
        },
        message: "获取分页成功",
        query,
      };
    },
  },

  {
    url: "/rbac/user/del",
    method: "delete",
    response: () => {
      return {
        code: 2000,
        data: null,
        message: "删除成功",
      };
    },
  },

  {
    url: "/rbac/user/edit",
    method: "put",
    response: () => {
      return {
        code: 2000,
        data: null,
        message: "修改成功",
      };
    },
  },

  {
    url: "/rbac/user/plus",
    method: "post",
    response: () => {
      return {
        code: 2000,
        data: null,
        message: "添加成功",
      };
    },
  },

  {
    url: "/rbac/user/recovery",
    method: "put",
    response: () => {
      return {
        code: 2000,
        data: null,
        message: "恢复成功",
      };
    },
  },

  {
    url: "/rbac/user/chpswd",
    method: "post",
    response: () => {
      return {
        code: 2000,
        data: null,
        message: "修改密码成功",
      };
    },
  },
];
