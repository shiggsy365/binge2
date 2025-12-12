#!/bin/bash
# Script to fix JDK image transformation errors in Android/Gradle builds

echo "=========================================="
echo "Gradle Build Error Fix Script"
echo "=========================================="
echo ""

echo "Step 1: Stopping all Gradle daemons..."
./gradlew --stop 2>/dev/null || gradle --stop 2>/dev/null || true
echo "✓ Gradle daemons stopped"
echo ""

echo "Step 2: Cleaning build directories..."
rm -rf app/build
rm -rf build
rm -rf .gradle
echo "✓ Local build directories cleaned"
echo ""

echo "Step 3: Cleaning Gradle caches (this may take a moment)..."
rm -rf ~/.gradle/caches/transforms-3
rm -rf ~/.gradle/caches/9.0*
rm -rf ~/.gradle/caches/8.*/transforms
echo "✓ Gradle transform caches cleaned"
echo ""

echo "Step 4: Cleaning Android build cache..."
rm -rf ~/.android/build-cache
echo "✓ Android build cache cleaned"
echo ""

echo "=========================================="
echo "Cleanup complete!"
echo "=========================================="
echo ""
echo "Next steps:"
echo "1. In Android Studio, go to File → Invalidate Caches → Invalidate and Restart"
echo "2. After restart, try File → Sync Project with Gradle Files"
echo "3. Then try Build → Rebuild Project"
echo ""
echo "If the issue persists, check that:"
echo "  - File → Project Structure → SDK Location → Gradle JDK is set to 'Embedded JDK'"
echo "  - You're using a stable Gradle version (check gradle/wrapper/gradle-wrapper.properties)"
echo ""
