plugins {
	java
	`maven-publish`
	id("com.github.johnrengelman.shadow") version "6.1.0"
	kotlin("jvm") version "1.6.10"
}

group = "dev.reeve"
version = "1.0.2"

val user = properties["user"]
val key = properties["key"]

repositories {
	mavenCentral()
}

dependencies {
	testImplementation("junit:junit:4.13.2")
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
			//groupId = project.group.toString()
			//artifactId = "MazeLib"
			//version = project.version.toString()
			
			//println("$groupId/$artifactId/$version")
			
			from(components["kotlin"])
		}
	}
}