const {defineConfig} = require('@vue/cli-service')
const path = require('path')
const ESLintWebpackPlugin = require("eslint-webpack-plugin");
// module.exports = defineConfig({
//   transpileDependencies: true
// })
const webpack = require("webpack");

module.exports = defineConfig({
    outputDir: 'dist',
    publicPath: './',
    devServer: {
        https:false,
        proxy: {
            '/api': {
                target: 'http://www.diseaselinks.com:8080', changeOrigin: true, pathRewrite: {
                    '^/api': '/Tissue/api/upload'
                }
            }
        }, port: 8081
    }, transpileDependencies: true
    , configureWebpack: {  //解决Invalid options in vue.config.js: "plugins" is not allowed的问题
        module: {
            rules: [{
                test: /\.(png|jpe?g|gif|webp)$/,
                type: "asset",
                parser: {
                    dataUrlCondition: {
                        maxSize: 200 * 1024, // 小于40kb的图片会被base64处理
                    },
                },
                generator: {
                    // 将图片文件输出到 static/imgs 目录中
                    // 将图片文件命名 [hash:8][ext][query]
                    // [hash:8]: hash值取8位
                    // [ext]: 使用之前的文件扩展名
                    // [query]: 添加之前的query参数
                    filename: "static/imgs/[hash:8][ext][query]",
                },
            }, {
                test: /\.js$/,
                exclude: /node_modules/, // 排除node_modules代码不编译
                loader: "babel-loader",
            },]
        },
        plugins: [// AutoImport({
            //   resolvers: [ElementPlusResolver()],
            // }),
            // Components({
            //   resolvers: [ElementPlusResolver()],
            // }),
            // 配置jQuery
            new webpack.ProvidePlugin({
                $: "jquery", jQuery: "jquery", "windows.jQuery": "jquery",
            }), new ESLintWebpackPlugin({
                // 指定检查文件的根目录
                context: path.resolve(__dirname, "src"),
            })],
    }
})

