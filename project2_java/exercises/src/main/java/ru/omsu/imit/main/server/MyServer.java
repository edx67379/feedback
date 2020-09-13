package ru.omsu.imit.main.server;

import org.eclipse.jetty.server.Server;
import ru.omsu.imit.main.server.config.Settings;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyServer.class);

	private static Server jettyServer;

	private static void attachShutDownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				stopServer();
			}
		});
	}

	public static void createServer() {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(Settings.getRestHTTPPort()).build();
        ru.omsu.imit.main.server.config.MyResourceConfig config = new ru.omsu.imit.main.server.config.MyResourceConfig();
        jettyServer = JettyHttpContainerFactory.createServer(baseUri, config);
		LOGGER.info("Server started at port " + Settings.getRestHTTPPort());
	}

	public static void stopServer() {
		LOGGER.info("Stopping server");
		try {
			jettyServer.stop();
			jettyServer.destroy();
		} catch (Exception e) {
			LOGGER.error("Error stopping server", e);
			System.exit(1);
		}
		LOGGER.info("Server stopped");
	}


	public static void main(String[] args) {
		attachShutDownHook();
		createServer();
	}
}
