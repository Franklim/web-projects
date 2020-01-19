const axios = require('axios')
const Dev = require('../models/Dev')
const FunctionsUtil = require('../utils/FunctionsUtil');

module.exports = {
    async index(request, response) {
        const devs = await Dev.find();
        return response.json(devs);
    },

    async store(request, response) {
        const url = 'https://api.github.com/users/'
        const { github, techs, latitude, longitude } = request.body;

        let dev = await Dev.findOne({
            github

        })

        if (!dev) {
            const techsArray = FunctionsUtil.stringToArray(techs);
            const apiResponse = await axios.get(url + github);
            const { name, avatar_url, bio } = apiResponse.data


            const location = {
                type: 'Point',
                coordinates: [longitude, latitude],
            }

            dev = await Dev.create({
                github,
                name,
                avatar: avatar_url,
                bio,
                techs: techsArray,
                location

            })
        }

        return response.json({
            dev
        });
    }
};