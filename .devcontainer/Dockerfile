FROM eclipse-temurin:8-jdk-jammy

# Forge 1.7.10 requires Java 8 and a compatible Gradle toolchain.
# Create the devcontainer user expected by VS Code and install the minimal
# toolchain we need for source checkout and build steps.
RUN apt-get update \
    && DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends \
        git \
        curl \
        ca-certificates \
        unzip \
        procps \
        sudo \
    && rm -rf /var/lib/apt/lists/* \
    && useradd --create-home --shell /bin/bash vscode \
    && usermod -aG sudo vscode \
    && echo 'vscode ALL=(ALL) NOPASSWD:ALL' > /etc/sudoers.d/vscode \
    && chmod 0440 /etc/sudoers.d/vscode

WORKDIR /workspaces

# Ensure Gradle cache directory exists for the default VS Code user.
RUN mkdir -p /home/vscode/.gradle \
    && chown -R vscode:vscode /home/vscode/.gradle /workspaces

USER vscode

# Verify the Java 8 toolchain and Gradle wrapper before starting a Forge build.
CMD ["bash", "-lc", "java -version && ./gradlew --version"]
