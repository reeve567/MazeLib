plugins {
	`maven-publish`
	id("com.github.johnrengelman.shadow") version "6.1.0"
	kotlin("jvm") version "1.7.0"
}

group = "dev.reeve"
version = "1.0.5"

repositories {
	mavenCentral()
}

dependencies {
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.getByName<Test>("test") {
	useJUnitPlatform()
}

publishing {
	publications {
		create<MavenPublication>("main") {
			from(components["kotlin"])
			
			repositories {
				maven("https://repo.reeve.dev/repository/maven-releases/") {
					name = "Nexus"
					credentials {
						username = System.getenv("NEXUS_REPO_USERNAME")
						password = System.getenv("NEXUS_REPO_PASSWORD")
					}
				}
			}
		}
	}
}