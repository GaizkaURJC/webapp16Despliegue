docker run --rm -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=sala_DB -p 3306:3306 --name mySQL_sala -d mysql:8.0.33 
docker exec -it MYSQL_DATABASE=sala_DB mysql -u root -p
