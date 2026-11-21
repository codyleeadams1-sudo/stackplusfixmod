# StackPlus Bundle Fix

Companion Fabric mod for **StackPlus 3.1.2** (Minecraft 1.21.11). Prevents the crash caused by bundle
weight (`Fraction`) overflow when stack sizes are huge. Requires StackPlus, Fabric Loader and Java 21.

## Build the jar with GitHub
1. Create a new GitHub repository and upload everything in this folder (keep the `.github` folder).
2. Open the **Actions** tab, pick **Build jar**, wait for the green tick (or click *Run workflow*).
3. Open the finished run, download the **stackplus-bundlefix-jar** artifact, unzip it.
4. Put the jar that does NOT end in `-sources.jar` in your `mods` folder next to StackPlus.

If the build says a dependency version doesn't exist, update `loader_version` / `loom_version` in
`gradle.properties` using https://fabricmc.net/develop
