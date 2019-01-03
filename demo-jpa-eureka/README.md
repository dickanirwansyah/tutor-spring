# build the docker image
docker build --no-cache=true -t repo/eureka-server .

#run the container
docker run -d repo/eureka-server

or

#build and orchestrate the container
docker-compose up -d


#Restart docker
sudo restart docker 