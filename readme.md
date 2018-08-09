to run the application:

1) update ProductListings\Docker\ProductListing\application.properties replace {host} placeholder with correct host (check 1st point in below section for more info)
2) go to folder ProductListings\Docker
3) run "docker-compose build --force-rm --no-cache"
4) run "docker-compose up"
	your database will be up and running on port: 5433
	your databse User: 'postgres' and password: 'password'
	schema needed for product listing will ber ready in database: raku11 and under schema raku11.
	your application should start running on port http://{host}:8080/product-mapping/

please Read documentation file to know more about the module.

Possibles error/hurdles:
1) if you are using docker on windows using Docker quick start terminal you will need to use IP 192.168.99.100 to access services running on docker.
2) fresh war can be created by maven clean build command.
3) if fresh war needs to be deployed. put that war at ProductListings\Docker\ProductListing\ with name application.war	
	
readymade Postman collection is available at : https://www.getpostman.com/collections/68f97a84fb96a2c86e52
you can import this collection into your postman to run recipies.


