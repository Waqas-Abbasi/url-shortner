const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const cors = require('cors');
const path = require('path');

app.use(bodyParser.json());
app.use(cors());

app.use(express.static(path.join(__dirname, 'build')));

const grpc = require('grpc');

const protoLoader = require('@grpc/proto-loader');
const PROTO_PATH = __dirname + '/urlshortner.proto';
const packageDef = protoLoader.loadSync(PROTO_PATH,
    {
        keepCase: true,
        longs: String,
        enums: String,
        defaults: true,
        oneofs: true
    });

const grpcObject = grpc.loadPackageDefinition(packageDef);
const urlShortnerPackage = grpcObject.com.waqasabbasi.helloworld;


const port = 8080;
const backendAddress = process.env.URL_SHORTNER_SERVICE_SERVICE_HOST + ":" + process.env.URL_SHORTNER_SERVICE_SERVICE_PORT;

//Serve Static Files
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname + '/build/index.html'));
});

app.post('/getShortUrl', async (req, res) => {
    const longUrl = req.body.longUrl;

    //Get ShortUrl from backend
    const client = new urlShortnerPackage.URLShortnerService(backendAddress, grpc.credentials.createInsecure());
    //
    let shortUrl = '';
    await client.GetShortURL({
        longUrl,
    }, (err, response) => {
        shortUrl = response === undefined ? '' : response.shortUrl;
        const resp = {shortUrl};
        res.json(resp);
    });
});

//If a Url is Accessed
app.get('/*', async (req, res) => {
    const shortUrl = req.url.substr(1, req.url.length);

    //Get LongUrl from backend
    const client = new urlShortnerPackage.URLShortnerService(backendAddress, grpc.credentials.createInsecure());

    let longUrl = '';
    await client.GetLongUrl({
        shortUrl,
    }, (err, response) => {
        longUrl = response === undefined ? '' : response.longUrl;
        if (longUrl) {
            res.redirect('https://' + longUrl);
        } else {
            res.redirect('/');
        }
    });
});

app.listen(port, () => console.log(`listening at http://localhost:${port}`));