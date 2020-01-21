module.exports = {

    stringToArray(valueToArray){
        return valueToArray.split(',').map(value => value.trim());
    }

}