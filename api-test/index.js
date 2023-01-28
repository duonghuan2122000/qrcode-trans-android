const express = require('express');
const bodyParser = require('body-parser');
var jwt = require('jsonwebtoken');

const app = express();

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

const db = require('./data/db');

const authRoute = require('./router/auth');
const { Config, Transaction } = require('./data/model');

db.authenticate({
    logging: false
});

function camelize(str) {
    return str.replace(/(?:^\w|[A-Z]|\b\w|\s+)/g, function (match, index) {
        if (+match === 0) return ""; // or if (/\s+/.test(match)) for white spaces
        return index === 0 ? match.toLowerCase() : match.toUpperCase();
    });
}

app.get('/config', async (req, res) => {
    let configs = await Config.findAll();
    let confObj = {};
    for (let config of configs) {
        confObj[camelize(config.key)] = config.value;
    }
    return res.status(200).json(confObj);
});

app.post('/auth/token', (req, res) => {
    let { client_id, client_secret } = req.body;
    if (client_id == "dbhuan-client" && client_secret == "abc") {
        var token = jwt.sign({ client_id, client_secret }, "dbhuan", {
            expiresIn: "1 day"
        });
        return res.status(200).json({
            access_token: token
        });
    }
    return res.status(200).json({
        invalid_token: true
    });
});

app.use('/api', authRoute);

app.listen(3000);