const path = require("path");

const routes = (handler) => [
  {
    method: "POST",
    path: "/upload",
    handler: handler.postUploadImageHandler,
    options: {
      payload: {
        allow: "multipart/form-data",
        multipart: true,
        output: "stream",
        maxBytes: 1000000,
      },
    },
  },
  // {
  //   method: "GET",
  //   path: "/uploads/images/{param*}",
  //   handler: {
  //     directory: {
  //       path: path.resolve(__dirname, "images"),
  //     },
  //   },
  // },
];

module.exports = routes;
