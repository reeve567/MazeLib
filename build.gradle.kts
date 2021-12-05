plugins {
    java
    `maven-publish`
}

group = "dev.reeve"
version = "latest"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/dapper-fox-studios/bdungeons")
        }
    }
    
    publications {
        create<MavenPublication>("main") {
            groupId = group as String
            artifactId = "MazeLib"
            version = "1.0"
            
            from(components["java"])
        }
    }
}