var math= {
    double: function(num){
        try{
            var result = num * 2
            BlackJin.getDoubleNum(result)
        }catch(err){
            console.log(">> [math.double()] " + err)
        }
    },

    triple: function(num){
        try{
            var result = num * 3
            BlackJin.getTripleNum(result)
        }catch(err){
            console.log(">> [math.double()] " + err)
        }
    }
}

function showAndroidToast(toast) {
    BlackJin.showToast(toast)
}