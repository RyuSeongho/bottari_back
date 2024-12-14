import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "software"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-web-services")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("org.postgresql:postgresql")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	//테스트
	// Mockk 라이브러리
	testImplementation("io.mockk:mockk:1.13.7")
	// Spring과 Mockk 통합을 위한 SpringMockk
	testImplementation("com.ninja-squad:springmockk:4.0.2")
	//mockito
	testImplementation("org.mockito:mockito-core:5.6.0")
	testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
	testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
	// Spring WebFlux 의존성
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	// Reactor Core (비동기 스트림 처리)
	implementation("io.projectreactor:reactor-core")

	// Reactor Test (리액티브 테스트를 위한 의존성)
	testImplementation("io.projectreactor:reactor-test")
//	// Kotest 의존성
//	testImplementation("io.kotest:kotest-runner-junit5:5.5.1")  // Kotest 실행자
//	testImplementation("io.kotest:kotest-assertions-core:5.5.1")  // Kotest Assertions (기본)
//	testImplementation("io.kotest:kotest-framework-api:5.5.1") // Kotest API

	// security (비밀번호 해시 암호화)
	implementation("org.springframework.boot:spring-boot-starter-security")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.test {
	testLogging {
		events("passed", "failed", "skipped")
		showStandardStreams = true
	}
}
tasks.withType<Test> {
	useJUnitPlatform()
}
