const mongoose = require('mongoose');
const DevSchema = new mongoose.Schema({
    name: String,
    github: String,
    bio: String,
    avatar: String,
    techs: [String]
});

module.exports = mongoose.model('Dev', DevSchema);