Cloudstate shopping cart example smoketest/loadtest
===================================================

# Getting started

This project allows you to run a load test against the Cloudstate shopping cart example. A configurable number of users will make 30 requests each to the shopping cart service, adding items, getting the cart then removing items. For the test to pass all the requests must succeed within 3 seconds and none must fail.

# Running the shopping cart load test

Using the Docker image (assumes you build the Docker image or use the public one)

`docker run -it --env GATLING_NUM_USERS=1 --env GATLING_HOST=host.docker.internal samples-loadtest-shoppingcart:0.1.0-SNAPSHOT`

Config notes:

GATLING_HOST is the hostname your shopping cart service is running on
GATLING_PORT is the port number of your cart service (default is 9000)
GATLING_NUM_USERS is the number of users to simulate. They will be simulated in parallel and ramped up over 30 seconds

Using sbt:

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
