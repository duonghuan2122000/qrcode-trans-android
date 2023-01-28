const { Sequelize, Model, DataTypes } = require('sequelize');
const sequelize = new Sequelize('test_qrcode', 'dbhuan', '0866444202', {
    host: '103.173.254.82',
    dialect: 'mariadb'
});

module.exports = sequelize;