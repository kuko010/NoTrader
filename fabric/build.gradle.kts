plugins {
    id("multiloader-loader")
    id("fabric-loom")
}

// Variables for cleaner access
val modId = property("mod_id").toString()
val mcVersion = property("minecraft_version").toString()

dependencies {
    minecraft("com.mojang:minecraft:$mcVersion")

    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${property("parchment_minecraft")}:${property("parchment_version")}@zip")
    })

    "modImplementation"("net.fabricmc:fabric-loader:${property("fabric_loader_version")}")
//    "modImplementation"("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")

    implementation("com.google.code.findbugs:jsr305:3.0.1")
    implementation(project(":common"))
}

loom {
    val awFile = project(":common").file("src/main/resources/$modId.accesswidener")
    if (awFile.exists()) {
        accessWidenerPath.set(awFile)
    }

    mixin {
        defaultRefmapName.set("$modId.refmap.json")
    }

    runs {
        named("client") {
            client()
            setConfigName("Fabric Client")
            ideConfigGenerated(true)
            runDir("run")
        }
        named("server") {
            server()
            setConfigName("Fabric Server")
            ideConfigGenerated(true)
            runDir("run")
        }
    }
}