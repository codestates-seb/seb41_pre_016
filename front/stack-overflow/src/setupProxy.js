const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    '/test',
    createProxyMiddleware({
      target: 'https://0c21-121-145-24-124.jp.ngrok.io',
      changeOrigin: true,
    })
  );
};