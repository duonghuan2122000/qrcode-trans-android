const { Model, DataTypes } = require('sequelize');
const sequelize = require('./db');
// bảng config
class Config extends Model {}

Config.init({
    id: {
        type: DataTypes.UUID,
        primaryKey: true
    },
    key: {
        type: DataTypes.STRING(255)
    },
    value: {
        type: DataTypes.STRING(255)
    }
}, {
    sequelize,
    modelName: "config",
    primaryKey: "id",
    timestamps: false,
    tableName: "config"
});

class Transaction extends Model {}

Transaction.init({
    id: {
        type: DataTypes.UUID,
        primaryKey: true
    },
    txnId: {
        type: DataTypes.STRING(20)
    },
    terminalId: {
        type: DataTypes.STRING(50)
    },
    amount: {
        type: DataTypes.DECIMAL(18, 0)
    },
    masterMerCode: {
        type: DataTypes.STRING(50)
    },
    createdDate: {
        type: DataTypes.DATE
    }
}, {
    sequelize,
    modelName: "transaction",
    timestamps: false,
    primaryKey: "id",
    tableName: "transaction"
})

module.exports = {
    Config,
    Transaction
}