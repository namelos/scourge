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
    historyApiFallback: true,
    open: true,
    index: 'index.html',
    proxy: {
      '/api': 'http://localhost:9000'
    }
  }
}