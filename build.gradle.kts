plugins {
	java
	`maven-publish`
	id("com.github.johnrengelman.shadow") version "6.1.0"
	kotlin("jvm") version "1.6.10"
}

group = "dev.reeve"
version = "1.0.4"

val user = properties["user"]
val key = properties["key"]

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
	repositories {
		maven {
			name = "GithubPackages"
			url = uri("https://maven.pkg.github.com/reeve567/mazelib")
			
			credentials {
				username = user.toString()
				password = key.toString()
			}
		}
	}
	
	publications {
		create<MavenPublication>("main") {
			groupId = project.group.toString()
			artifactId = "mazelib"
			version = project.version.toString()
			
			from(components["kotlin"])
		}
	}
}