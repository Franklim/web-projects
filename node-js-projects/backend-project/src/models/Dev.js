const mongoose = require('mongoose');
const PointSchema = require('./PointSchema');

const DevSchema = new mongoose.Schema({
    name: String,
    github: String,
    bio: String,
    avatar: String,
    techs: [String],
    location: {
        type: PointSchema,
        index : '2dsphere'
    }
});

module.exports = mongoose.model('Dev', DevSchema);