require("dotenv").config();
const express = require("express");
const bodyParser = require("body-parser");
const userRoutes = require("./routes/userRoutes");
const { NacosNamingClient } = require("nacos");

const app = express();
const port = process.env.PORT || 3000;

app.use(bodyParser.json());
app.use("/api", userRoutes);

const nacosClient = new NacosNamingClient({
  serverList: ["127.0.0.1:8848"],
  namespace: "public",
  username: "nacos",
  password: "nacos",
  logger: console,
});

nacosClient.ready(() => {
  console.log("Nacos client is ready");
});

nacosClient.registerInstance("node-service", {
  ip: "127.0.0.1",
  port: port,
  healthy: true,
  enabled: true,
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
