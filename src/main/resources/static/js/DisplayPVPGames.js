displayPVPGames();
async function displayPVPGames() {
    var url = "http://localhost:8080/game/OpenGames"; //TODO hardcoded for now
    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            addBlock(data);
            });
}
function addBlock(data) {//this will create the blocks
    const right = document.getElementById("ExistingGames");
    //TODO add a removal for right since this only appends and doesnt replace
    for(var x = 0; x < data.length; x++) {
        const newDiv = document.createElement("div");
        console.log(data[x].id);

        var userNameBlock = document.createElement("span");
        var userName = data[x].username;
        userNameBlock.innerHTML = userName + " ";

        var created = data[x].created;
        var createdBlock = document.createElement("span");
        createdBlock.innerHTML = created + " ";

        var gameStatus = data[x].gameStatus;
        var gameStatusBlock = document.createElement("span");
        gameStatusBlock.innerHTML = gameStatus + " ";

        var gameIDInput = document.createElement("button");
        gameIDInput.innerHTML = "Join";
        gameIDInput.setAttribute("value", data[x].id)
        gameIDInput.onclick = async function() { postRequest(this.value)};


        newDiv.append(userNameBlock, createdBlock, gameStatusBlock, gameIDInput);
        right.append(newDiv);
    }
}
function postRequest(gameID) {
    var url = "http://localhost:8080/join";
    var token = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
    fetch(url,  {
        method: 'POST',
        credentials: 'include',
        headers: {
            Accept: "application/json",
            "Content-type": "application/json; charset=UTF-8",
            'X-XSRF-TOKEN': token
   
         },
         body: JSON.stringify({
            id: gameID}),
         redirect: "follow"
    });
}

