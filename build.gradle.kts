import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.7"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// developmentOnly
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// validation
	implementation("org.springframework.boot:spring-boot-starter-validation:2.7.3")

	// jwt
	implementation("io.jsonwebtoken:jjwt:0.9.0")

	// security
	implementation("org.springframework.boot:spring-boot-starter-security")

	// web
	implementation("org.springframework.boot:spring-boot-starter-web")

	// lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// database
	implementation("mysql:mysql-connector-java:8.0.28")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	runtimeOnly("com.mysql:mysql-connector-j")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	// jpa
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// coolSms
	implementation("net.nurigo:sdk:4.3.0")
	implementation("net.nurigo:javaSDK:2.2.0")

	// S3
	implementation ("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")

	implementation("software.amazon.awssdk:s3:2.17.43")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
