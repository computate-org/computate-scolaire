package org.computate.scolaire.enUS.vertx;

import java.util.function.Consumer;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;

/**
 * A Java class to run the main Vert.x application as a main method. 
 * CanonicalName: org.computate.scolaire.frFR.vertx.CoureurVertx
 **/
public class RunnerVertx {

	protected static final Logger LOGGER = LoggerFactory.getLogger(RunnerVertx.class);

	public static void  run(Class<?> c) {
		JsonObject zkConfig = new JsonObject();
		String zookeeperHostName = System.getenv("zookeeperHostName");
		Integer zookeeperPort = Integer.parseInt(System.getenv("zookeeperPort"));
		Integer clusterPort = System.getenv("clusterPort") == null ? null : Integer.parseInt(System.getenv("clusterPort"));
		String clusterHost = System.getenv("clusterHost");
		Integer clusterPublicPort = System.getenv("clusterPublicPort") == null ? null : Integer.parseInt(System.getenv("clusterPublicPort"));
		String clusterPublicHost = System.getenv("clusterPublicHost");
		String zookeeperHosts = zookeeperHostName + ":" + zookeeperPort;
		zkConfig.put("zookeeperHosts", zookeeperHosts);
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
		EventBusOptions eventBusOptions = new EventBusOptions();
		String hostname = System.getenv("HOSTNAME");
		String openshiftService = System.getenv("openshiftService");
		if(clusterHost == null) {
			clusterHost = hostname;
		}
		if(clusterPublicHost == null) {
			if(hostname != null && openshiftService != null) {
				clusterPublicHost = hostname + "." + openshiftService;
			}
		}
		if(clusterHost != null) {
			LOGGER.info(String.format("clusterHost: %s", clusterHost));
			eventBusOptions.setHost(clusterHost);
		}
		if(clusterPort != null) {
			LOGGER.info(String.format("clusterPort: %s", clusterPort));
			eventBusOptions.setPort(clusterPort);
		}
		if(clusterPublicHost != null) {
			LOGGER.info(String.format("clusterPublicHost: %s", clusterPublicHost));
			eventBusOptions.setClusterPublicHost(clusterPublicHost);
		}
		if(clusterPublicPort != null) {
			LOGGER.info(String.format("clusterPublicPort: %s", clusterPublicPort));
			eventBusOptions.setClusterPublicPort(clusterPublicPort);
		}
		eventBusOptions.setClustered(true);
		optionsVertx.setEventBusOptions(eventBusOptions);
		optionsVertx.setClusterManager(gestionnaireCluster);
		DeploymentOptions deploymentOptions = new DeploymentOptions();
		deploymentOptions.setInstances(10);

		run(c, optionsVertx, deploymentOptions);
	}

	public static void  run(Class<?> c, VertxOptions options, DeploymentOptions deploymentOptions) {
		String verticleID = c.getName();

		Consumer<Vertx> runner = vertx -> {
			if (deploymentOptions != null) {
				vertx.deployVerticle(verticleID, deploymentOptions);
			} else {
				vertx.deployVerticle(verticleID);
			}
		};
		Vertx.clusteredVertx(options, res -> {
			if (res.succeeded()) {
				Vertx vertx = res.result();
				EventBus eventBus = vertx.eventBus();
				LOGGER.info("We now have a clustered event bus: {}", eventBus);
				runner.accept(vertx);
			} else {
				res.cause().printStackTrace();
			}
		});
	}
}
