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
    path: "/batik/{id}/save",
    handler: handler.saveBatikHandler,
  },
  {
    method: "GET",
    path: "/batik/save",
    handler: handler.getSavedBatikHandler,
  },
  {
    method: "DELETE",
    path: "/batik/{id}/save",
    handler: handler.unSaveBatikHandler,
  },
];

module.exports = routes;
