const BatikHandler = require("./handler");
const routes = require("./routes");

module.exports = {
  name: "batik",
  version: "1.0.0",
  register: async (server) => {
    const batikHandler = new BatikHandler();
    server.route(routes(batikHandler));
  },
};
