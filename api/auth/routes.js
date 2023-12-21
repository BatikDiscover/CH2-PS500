const routes = (handler) => [
  {
    method: "POST",
    path: "/register",
    handler: handler.registerHandler,
  },
  {
    method: "POST",
    path: "/login",
    handler: handler.loginHandler,
  },
  {
    method: "POST",
    path: "/logout",
    handler: handler.logoutHandler,
  },
  {
    method: "GET",
    path: "/user/{id}",
    handler: handler.getUserById,
  },
];

module.exports = routes;
