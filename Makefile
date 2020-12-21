build:
	docker build -t trello-creator -f trello-bridge/src/docker/Dockerfile .

run:
	docker run --env BOARD=${BOARD} --env SECURITY_KEY=${SECURITY_KEY} --env SECURITY_TOKEN=${SECURITY_TOKEN}  --rm -it -p 3000:3000 trello-creator:latest trello-creator

package:
	mvn clean package

native:
	java -DBOARD=${BOARD} -DSECURITY_KEY=${SECURITY_KEY} -DSECURITY_TOKEN=${SECURITY_TOKEN} -jar trello-bridge/target/trello-bridge-1.0-SNAPSHOT.jar