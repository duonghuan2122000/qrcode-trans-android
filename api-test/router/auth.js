const express = require('express');
const router = express.Router();
const jwt = require('jsonwebtoken');
const { v4: uuid } = require('uuid');

const { Config, Transaction } = require('../data/model');

router.use((req, res, next) => {
    console.log("middleware");
    let authorization = req.headers['authorization'];
    if (!authorization) {
        res.status(401).send();
    }
    try {
        let token = authorization.substring(7);
        if (!token) {
            res.status(401).send();
        }
        jwt.verify(token, 'dbhuan');
    } catch (error) {
        res.status(401).send();
    }
    next();
});

function camelize(str) {
    return str.replace(/(?:^\w|[A-Z]|\b\w|\s+)/g, function (match, index) {
        if (+match === 0) return ""; // or if (/\s+/.test(match)) for white spaces
        return index === 0 ? match.toLowerCase() : match.toUpperCase();
    });
}

function randomNumber(min, max) {
    return parseInt(Math.random() * (max - min) + min);
}

router.post('/config', async (req, res) => {
    let { urlTransactionQRCode, clientSecret, clientId, urlParseQRCode, urlGetToken } = req.body;
    let c1 = await Config.findOne({ where: { key: "UrlTransactionQRCode" } });
    c1.value = urlTransactionQRCode;
    c1.save();

    let c2 = await Config.findOne({ where: { key: "ClientSecret" } });
    c2.value = clientSecret;
    c2.save();

    let c3 = await Config.findOne({ where: { key: "ClientId" } });
    c3.value = clientId;
    c3.save();

    let c4 = await Config.findOne({ where: { key: "UrlParseQRCode" } });
    c4.value = urlParseQRCode;
    c4.save();

    let c5 = await Config.findOne({ where: { key: "UrlGetToken" } });
    c5.value = urlGetToken;
    c5.save();

    return res.status(200).json({
        isSuccess: true
    });
});

router.post('/qrcode/parse', (req, res) => {
    let { data } = req.body;
    console.log(data);

    if (data == "123") {
        return res.status(400).json({
            isSuccess: false,
            errorCode: "E1000"
        });
    }

    if (data == "abc") {
        return res.status(400).json({
            isSuccess: false,
            errorCode: "E1001"
        });
    }
    let objRes = {
        txnId: "123abc",
        mobile: "",
        accountNo: "",
        amount: randomNumber(2000, 100000).toString(),
        masterMerCode: "",
        merchantCode: "QR1234",
        merchantName: "CTCP Test QRCode",
        terminalId: "2122000",
        terminalName: "DBHuan",
        name: "",
        phone: "",
        provinceId: "",
        districtId: "",
        address: "",
        email: ""
    };
    return res.status(200).json(objRes);
});

router.post('/qrcode/transaction', async (req, res) => {
    let objReq = req.body;
    let trans = await Transaction.findOne({ where: { terminalId: objReq.terminalId, txnId: objReq.txnId } });
    if (trans) {
        return res.status(200).json({
            code: "03",
            message: "Đơn hàng đã được thanh toán"
        });
    }
    let newTrans = new Transaction();
    newTrans.id = uuid();
    newTrans.txnId = objReq.txnId;
    newTrans.terminalId = objReq.terminalId;
    newTrans.amount = objReq.amount;
    newTrans.masterMerCode = objReq.masterMerCode;
    newTrans.merchantCode = objReq.merchantCode;
    newTrans.createdDate = new Date().toISOString().slice(0, 19).replace('T', ' ');
    newTrans.save();
    return res.status(200).json({
        code: "00",
        message: "Đặt hàng thành công"
    });
});

module.exports = router;