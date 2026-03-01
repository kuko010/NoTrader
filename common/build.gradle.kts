plugins {
    id("multiloader-common")
    id("net.neoforged.moddev.legacyforge")
}

// Variables for cleaner access
val modId = property("mod_id").toString()
val mcVersion = property("minecraft_version").toString()

legacyForge {
    mcpVersion = mcVersion

    val at = file("src/main/resources/META-INF/accesstransformer.cfg")
    if (at.exists()) {
        // Kotlin uses .from() for FileCollections
        accessTransformers.from(at)
    }

    parchment {
        minecraftVersion = property("parchment_minecraft").toString()
        mappingsVersion = property("parchment_version").toString()
    }
}

dependencies {
    compileOnly("org.spongepowered:mixin:0.8.5")
    implementation("com.google.code.findbugs:jsr305:3.0.1")
}

configurations {
    create("commonJava") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
    create("commonResources") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
}

artifacts {
    // In Kotlin, we use the configuration name as a string or a variable
    add("commonJava", sourceSets.main.get().java.sourceDirectories.singleFile)
    add("commonResources", sourceSets.main.get().resources.sourceDirectories.singleFile)
}