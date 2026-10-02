plugins {
    `java-library`
    alias(libs.plugins.publish)
}
repositories {
    mavenCentral()
}

dependencies {
    api(libs.gson)
    api(libs.adventure)
    api(libs.adventure.nbt)
}

group = "dev.minestom-united"
version = "0.1.0"
description = "A library to get Minestom codecs everywhere"

java {
    withSourcesJar()
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

val isSnapshot = version.toString().endsWith("-SNAPSHOT")

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    pom {
        name = project.name
        description = project.description
        url = "https://github.com/Minestom-United/codec"

        licenses {
            license {
                name = "MIT"
                url = "https://github.com/Minestom-United/codec/blob/master/LICENSE"
            }
        }

        developers {
            developer {
                id = "Foxikle"
                url = "https://github.com/Foxikle"
            }

            developer {
                id = "TropicalShadow"
                url = "https://github.com/TropicalShadow"
            }

            developer {
                id = "Webhead1104"
                url = "https://github.com/Webhead1104"
            }
        }

        issueManagement {
            system = "Github"
            url = "https://github.com/Minestom-United/codec/issues"
        }

        scm {
            url = "https://github.com/Minestom-United/codec"
            connection = "scm:git:git://github.com/Minestom-United/codec.git"
            developerConnection = "scm:git:git@github.com:Minestom-United/codec.git"
        }
    }
}

publishing {
    repositories {
        maven {
            name = "MinestomUnitedRepository"
            url = uri(
                if (version.toString().endsWith("-SNAPSHOT"))
                    "https://repo.minestom-united.dev/snapshots"
                else "https://repo.minestom-united.dev/releases"
            )
            credentials {
                username = providers.gradleProperty("MinestomUnitedRepositoryUsername")
                    .orElse(providers.environmentVariable("REPO_USERNAME")).orNull
                password = providers.gradleProperty("MinestomUnitedRepositoryPassword")
                    .orElse(providers.environmentVariable("REPO_PASSWORD")).orNull
            }
        }
    }
}
