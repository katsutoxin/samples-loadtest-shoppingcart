Cloudstate shopping cart example loadtest
=========================================

# Getting started

This project allows you to run a load test against the Cloudstate shopping cart example.

# Running the shopping cart load test

In sbt

`gatling-it:testOnly shoppingcart.ShoppingCartSimulation1`

# Running a load test against your own service

1. Add the protobuf files for your service API in `src/main/protobuf`
2. Copy the folder `src/it/scala/shoppingcart` and rename it to match your service
3. Make a gatling test copying the example from `ShoppingCartSimulation1.scala` that calls your endpoints

You may need to consult the Gatling documumentation to explore its full range of functionality.

# Technical notes

This project uses Gatling as a plugin along with grpc to handle the protobuf compilation and gatling-grpc so that we can write Gatling load tests over gRPC instead of HTTP.

Run all simulations
-------------------

```bash
> gatling:test
```

Run the shopping cart simulation from sbt
-----------------------------------------

```bash
sbt "gatling-it:testOnly shoppingcart.ShoppingCartSimulation1"
```

Run the shopping cart simulation at the command line and direct to a particular server. Note that if you are running the shopping cart service in Kubernetes cluster, you need to port forward from the akka-sidecar container so you can access it directly with the load test. (The load test uses gRPC not grpc-web protocol).

```bash
GATLING_PORT=[PORT] GATLING_USERS=[NUM USERS] GATLING_HOST=[YOUR HOST] sbt gatling-it:testOnly shoppingcart.ShoppingCartSimulation1
```

List all tasks
--------------------

```bash
> tasks gatling -v
```

## References

https://github.com/gatling/gatling-sbt-plugin-demo
https://github.com/phiSgr/gatling-grpc
https://medium.com/@georgeleung_7777/a-demo-of-gatling-grpc-bc92158ca808
