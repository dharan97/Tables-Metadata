# Tables-Metadata

This project helps in getting the metadata of a structured database and store the information in another database basically in mongo db.

### To run the project
Checkout the development branch as it is the stable branch.
<hr>
<p>1. Ensure you have an mysql instance running on server or local system. </p>
<p>2. Ensure you have an mongodb 4.2+ version instance running on server or local system. </p>
<p>3. Provide the properties file path as vm argument, like specifying the string in vm arguments "-Dconfig=path to propertes file" by excluding the file name. Ex : -Dconfig=/home/directory/.</p>
<p>4.The properties file name should be saved as app.properties. If you want to change the file name you must also change it in AppConfiguration class @PropertySource annotation</p>
<p>5. After succesfully running the project, call the api "http://localhost:8080/getMetadata/" with "get" request mapping to get the metadata of a database.
