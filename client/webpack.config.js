const path = require('path')

module.exports = {
  entry: './src/index.js',
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'bundle.js',
    publicPath: '/assets/',
  },
  module: {
    rules: [
      { test: /.jsx?$/, use: 'babel-loader' }
    ]
  },
  devServer: {
    contentBase: path.join(__dirname, 'public'),
    open: true,
    index: 'index.html',
    proxy: {
      '/': {
        target: 'http://localhost:9000',
        bypass: (req, res, proxyOptions) => {
          if (req.headers.accept && req.headers.accept.indexOf('html') !== -1) {
            return '/index.html'
          }
        }
      }
    }
  }
}