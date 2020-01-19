module.exports = {

    stringToArray(string){
        return string.split(',').map(x => x.trim());
    }

}