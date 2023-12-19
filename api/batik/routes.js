const routes = (handler) => [
  {
    method: "GET",
    path: "/batik",
    handler: handler.getBatikHandler,
  },
  {
    method: "GET",
    path: "/batik/{id}",
    handler: handler.getBatikByIdHandler,
  },
  {
    method: "POST",
    path: "/batik/{id}/like",
    handler: handler.likeBatikHandler,
  },
  {
    method: "GET",
    path: "/batik/{id}/like",
    handler: handler.getLikedBatikHandler,
  },
  {
    method: "DELETE",
    path: "/batik/{id}/like",
    handler: handler.unLikeBatikHandler,
  },
];

module.exports = routes;
