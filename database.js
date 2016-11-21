var fs = require("fs");
	var file ="./workbench/LateNightCravings/db/LateNightCravings";
	var exists = fs.existsSync(file);

	var sqlite3 = require("sqlite3").verbose();
	var db = new sqlite3.Database(file);

	db.serialize(function()  {
		if(!exists) {
			db.run(".read ./workbench/LateNightCravings/createTables.sql");
		}
		
		var stmt = db.prepare("INSERT INTO Items VALUES(12,'Hot Dog',12.12,'A tasty hot dog')");
		stmt.finalize();
		
		db.each("SELECT Name FROM Items", function(err,row) {
			console.log(row.Name);
		});
	});

	db.close();