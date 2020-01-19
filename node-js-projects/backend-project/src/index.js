const express = require('express');
const mongoose = require('mongoose');
const routes = require('./routers/routes');


const app = express();
mongoose.connect("mongodb+srv://root:root@cluster0-pka7f.gcp.mongodb.net/db?retryWrites=true&w=majority", {
    useNewUrlParser: true,
    useUnifiedTopology: true,
});

app.use(express.json());
app.use(routes);
app.listen(3333);