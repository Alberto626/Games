displayMoves();//display the moves initially
var table = document.getElementById("tableID");
for (var i = 0; i < table.rows.length; i++) {
         for (var j = 0; j < table.rows[i].cells.length; j++)
         table.rows[i].cells[j].onclick = function () {
            tableText(this);
         };
   }
function tableText(tableCell) {//send a post request 
   let row = tableCell.parentNode.rowIndex + 1
   let col = tableCell.cellIndex + 1

   alert(tableCell.cellIndex + 1 +  "col + " + (tableCell.parentNode.rowIndex + 1) + "row");
   fetch(window.location.href, {
      method: 'POST',
      credentials: 'include',
      headers: {
         Accept: "application/json",
         "Content-type": "application/json; charset=UTF-8"

      },
      body: JSON.stringify({
         rowID: row,
         colID : col})
   })
   .then((response) => displayMoves())
}
async function displayMoves() {//fetch api and display to gameboard
   let value
   var gameID = window.location.pathname.split('/')[2];//this is the gameid
   var url = "http://localhost:8080/game/Moves?id=" + gameID;
   const response = await fetch(url)
   value = await response.json()
   for(let x = 0; x < value.length; x++) {//leg work of display
         let tableValue = x % 2 === 0 ? 'X': 'O'
         let row = value[x].boardRow - 1
         let col = value[x].boardColumn - 1
         table.rows[row].cells[col].innerHTML = tableValue;
   }

}