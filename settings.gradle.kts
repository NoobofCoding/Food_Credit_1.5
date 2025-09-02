pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

// Only ONE dependencyResolutionManagement block
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral() // This is where gotrue-kt should be found
        // You can add more repositories if needed, e.g.:
        // maven { url = uri("https://some-other-repo.com/maven") }
    }
}

// Only ONE rootProject.name and include block
rootProject.name = "Food Credit 1.5" // Choose the correct name
include(":app")
