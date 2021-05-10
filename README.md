# Tables-Metadata

This project helps in getting the metadata of a structured database and store the information in another database basically in mongo db.

### To run the project
<hr>
<p>1. Ensure you have an mysql instance running on server or local system. </p>
<p>2. Ensure you have an mongodb 4.2+ version instance running on server or local system. </p>
<p>3. Provide the properties file path as vm argument, like specifying the string in vm arguments "-Dconfig=path to propertes file" by excluding the file name. Ex : -Dconfig=/home/directory/.</p>
<p>4.The properties file name should be saved as app.properties. If you want to change the file name you must also change it in AppConfiguration class @PropertySource annotation</p>
<p>5. After succesfully running the project, call the api "http://localhost:8080/getMetadata/" with "get" request mapping to get the metadata of a database.
<hr>

### app.properties
<p>mysql.user= </p>
<p>mysql.password= </p>
<p>mysql.db= </p>
<p>mysql.host= </p>
<p>mysql.port=3306 </p>

<p>mongo.db= </p>
<p>mongo.host= </p>
<p>mongo.port= </p>
<p>mongo.max_connections=10 </p>
<p>mongo.user= </p>
<p>mongo.password= </p>

The above mentioned properties are mandatory for the application
