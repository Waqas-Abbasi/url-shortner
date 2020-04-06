const HtmlWebPackPlugin = require('html-webpack-plugin');
const path = require('path');

const htmlWebpackPlugin = new HtmlWebPackPlugin({
    template: 'src/client/index.html',
    filename: 'index.html'
});

module.exports = {
    entry: path.resolve(__dirname, 'src') + '/client/index.js',
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader'
                }
            },
            {
                test: /\.css$/,
                use: [
                    {
                        loader: 'style-loader'
                    },
                    {
                        loader: 'css-loader',
                    }
                ]
            }
        ],
    },
    plugins: [htmlWebpackPlugin],
    output: {
        path: path.resolve(__dirname, './src/build'),
        filename: 'app-bundle.js'
    },
    resolve: {
        extensions: ['.js', '.jsx'],
    }
};
