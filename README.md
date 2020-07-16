Cloudstate shopping cart example smoketest/loadtest
===================================================

# Getting started

This project allows you to run a load test against the Cloudstate shopping cart example. A configurable number of users will make 30 requests each to the shopping cart service, adding items, getting the cart then removing items. For the test to pass all the requests must succeed within 3 seconds and none must fail.

# Running the shopping cart smoke test

Using the Docker image (assumes you build the Docker image or use the public one)

Run a smoke test

Be sure to change GATLING_HOST and GATLING_PORT if you are not testing against a local Docker service...

`docker run --rm --name shoppingcart-load-test --env JAVA_OPTS="-Dgatling.charting.noReports=true" -it --volume $PWD/results:/opt/docker/results --env GATLING_NUM_USERS=1 --env GATLING_NUM_REPS=10 --env GATLING_HOST=host.docker.internal cloudstateio/samples-loadtest-shoppingcart:0.1.0-SNAPSHOT`

Config options:

* _GATLING_HOST_ is the hostname your shopping cart service is running on
* _GATLING_PORT_ is the port number of your cart service (default is 9000)
* _GATLING_NUM_USERS_ is the number of users to simulate. They will be simulated in parallel and ramped up over 30 seconds
* _GATLING_NUM_REPS_ is the number of reps per user to simulate. Each user will add, remove and get the cart contents this number of times

# Running the shopping cart load test and gathering reports

Run the load test:

`docker run --rm --name shoppingcart-load-test --env JAVA_OPTS="-Dgatling.charting.noReports=false" -it --volume $PWD/results:/opt/docker/results --env GATLING_NUM_USERS=10 --env GATLING_NUM_REPS=10 --env GATLING_HOST=host.docker.internal cloudstateio/samples-loadtest-shoppingcart:0.1.0-SNAPSHOT`

Note that you can greatly increase GATLING_NUM_REPS and GATLING_NUM_USERS which will make the load test take minutes instead of milliseconds to complete. Additionally set `noReports` to false, so we will now output a report in HTML format to display detailed data about the load test.

# Running with sbt

Using sbt, you can use the same environment variables as above and run like this:

`sbt run`

# Technical notes

This project uses Gatling as a plugin along with grpc to handle the protobuf compilation and gatling-grpc so that we can write Gatling load tests over gRPC instead of HTTP.

Run the shopping cart simulation at the command line and direct to a particular server. Note that if you are running the shopping cart service in Kubernetes cluster, you need to port forward from the akka-sidecar container so you can access it directly with the load test. (The load test uses gRPC not grpc-web protocol).

# Building the Docker image

`sbt docker:publishLocal`

# Pushing the Docker image

Note to push to your own respository set the `dockerUsername` in `build.sbt` to your Docker hub username.

If you want to push to a different repository then add a custom `dockerRepository`.

`sbt docker:publish`

## References

https://github.com/gatling/gatling-sbt-plugin-demo
https://github.com/phiSgr/gatling-grpc
https://medium.com/@georgeleung_7777/a-demo-of-gatling-grpc-bc92158ca808
