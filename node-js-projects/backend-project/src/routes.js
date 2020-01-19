const { Router } = require('express');
const axios = require('axios');
const Dev = require('./models/Dev');

const routes = Router();
const url = 'https://api.github.com/users/'

routes.post('/devs', async (request, response) => {
    const { github, techs } = request.body;
    const techsArray = techs.split(',').map(tech => tech.trim());
    const apiResponse = await axios.get(url + github);
    const { name, avatar_url, bio } = apiResponse.data

    console.log("Github ok!");

   const dev = await Dev.create({
        github,
        name,
        avatar:avatar_url,
        bio,
        techs : techsArray

    })


    return response.json({
       dev
    });
})

module.exports = routes;