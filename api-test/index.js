const express = require('express');
const bodyParser = require('body-parser');
const level = require('level');

const db = level('db');

const app = express();

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.post('/qrcodes/parse', (req, res) => {
    let { data } = req.body;

    if (data == "123") {
        return res.status(400).json({
            error: {
                code: "E1000",
                message: "QRCode không hợp lệ"
            }
        });
    }

    if (data == "abc") {
        return res.status(400).json({
            error: {
                code: "E1001",
                message: "QRCode đã được thanh toán"
            }
        });
    }

    let objRes = {
        countryCode: "VN",
        merchantCode: "0000102",
        payType: "03",
        productId: "",
        txnId: "",
        billNumber: "123abc",
        amount: "123",
        ccy: "704",
        expDate: "211228100000",
        desc: "",
        tipAndFee: "",
        consumerId: "",
        purpose: ""
    };
    return res.status(200).json(objRes);

});

app.post('/transactions', async (req, res) => {
    const input = req.body;
    try {
        let qrcode = await db.get(input.txnId);
        if (!qrcode) {
            let objRes = await payQRCode(input);
            return res.status(200).json(objRes);
        }
        return res.status(400).json({
            code: "03",
            message: "Đơn hàng đã được thanh toán",
            data: {
                txnId: input.txnId
            }
        })
    } catch (error) {
        let objRes = await payQRCode(input);
        return res.status(200).json(objRes);
    }
});

const payQRCode = async (input) => {
    await db.put(input.txnId, true);
    return {
        code: "00",
        message: "Thanh toán thành công",
        data: {
            txnId: input.txnId
        }
    };
}

app.listen(3000);