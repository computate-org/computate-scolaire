package org.computate.scolaire.enUS.vertx;

import java.util.function.Consumer;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;

public class CoureurVertx {

	public static void  run(Class<?> c) {
		JsonObject zkConfig = new JsonObject();
		zkConfig.put("zookeeperHosts", "localhost:10281");
		zkConfig.put("sessionTimeout", 20000);
		zkConfig.put("connectTimeout", 3000);
		zkConfig.put("rootPath", "io.vertx");
		zkConfig.put("retry", new JsonObject() {
			{
				put("initialSleepTime", 100);
				put("intervalTimes", 10000);
				put("maxTimes", 3);
			}
		});
		ClusterManager gestionnaireCluster = new ZookeeperClusterManager(zkConfig);
		VertxOptions optionsVertx = new VertxOptions();
		// For OpenShift
		optionsVertx
				.setEventBusOptions(new EventBusOptions().setClusterPublicHost("whatever").setClusterPublicPort(1234));
		optionsVertx.setClusterManager(gestionnaireCluster);
		optionsVertx.setClustered(true);
		// also somehow: java -jar my-fat.jar vertx.cacheDirBase=/tmp/vertx-cache

		run(c, optionsVertx, null);
	}

	public static void  run(Class<?> c, VertxOptions options, DeploymentOptions deploymentOptions) {
		String verticleID = c.getName();

		System.setProperty("vertx.cwd", "/");
		Consumer<Vertx> runner = vertx -> {
			if (deploymentOptions != null) {
				vertx.deployVerticle(verticleID, deploymentOptions);
			} else {
				vertx.deployVerticle(verticleID);
			}
		};
		if (options.isClustered()) {
			Vertx.clusteredVertx(options, res -> {
				if (res.succeeded()) {
					Vertx vertx = res.result();
					EventBus eventBus = vertx.eventBus();
					System.out.println("We now have a clustered event bus: " + eventBus);
					runner.accept(vertx);
				} else {
					res.cause().printStackTrace();
				}
			});
		} else {
			Vertx vertx = Vertx.vertx(options);
			runner.accept(vertx);
		}
	}
}
