// express server
var fs = require("fs");
var file ="/home/c/workbench/LateNightCravings/db/LateNightCravings";
var exists = fs.existsSync(file);

var sqlite3 = require("sqlite3").verbose();
var db = new sqlite3.Database(file);

db.serialize(function()  {
		if(!exists) {
			db.run(".read /home/c/workbench/LateNightCravings/createTables.sql");
			console.log("Created a new database");
		}
		
		var stmt = db.prepare("INSERT INTO Items VALUES(?,?,?,?)");
		stmt.run(9, 'Ice cream', 2.50, 'a frozen dairy treat');
		stmt.finalize();
		
		db.each("SELECT Name FROM Items", function(err,row) {
			console.log(row.Name + " " + row.Description);
		});
	});

console.log("Reached end");



db.close();
//npm install