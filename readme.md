to run the application:

run docker for postgres DB creation:
	go to ProductListings\Docker\postgresqlDocker
	run docker-compose build --force-rm --no-cache
	then run docker-compose up -docker
	your database is up and running on port: 5433
	your databse User: 'postgres' nad password: 'password'
	schema needed for product listing will ber ready in database: raku11 and under schema raku11.

once PostgresDB is up 
	go to ProductListings\Docker\ProductListing
	update application.properties present provide correct hostIP for database.
	docker will pick the default application.war to deploy
	run docker-compose build --force-rm --no-cache
	then run docker-compose up -docker
	your application should start running on port host:8080/product-mapping/

readymade Postman collection is available at : https://www.getpostman.com/collections/68f97a84fb96a2c86e52
you can import this collection into your postman to run recipies.
