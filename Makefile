# =================================================================================
# TESTS
# =================================================================================

run-unit-tests:
	./gradlew testDebugUnitTest --info

run-instrumented-tests: start-emulator disable-animations
	./gradlew connectedAndroidTest --info

# =================================================================================
# USEFUL COMMANDS
# =================================================================================

build:
	./gradlew clean build

download-dependencies:
	./gradlew androidDependencies

start-emulator:
	bash scripts/start_emulator.sh

clear-app-data:
	adb shell pm clear dev.diogocabral.webookmark

disable-animations:
	adb shell settings put global window_animation_scale 0
	adb shell settings put global transition_animation_scale 0
	adb shell settings put global animator_duration_scale 0