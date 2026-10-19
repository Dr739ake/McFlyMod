FROM mcr.microsoft.com/devcontainers/java:25

# The base devcontainer image includes an unrelated Yarn apt source that breaks
# apt updates in this environment. Remove it because this Java/Fabric project does
# not require Node or Yarn.
RUN rm -f /etc/apt/sources.list.d/yarn.list \
    && apt-get update \
    && apt-get install -y --no-install-recommends \
        git \
        curl \
        ca-certificates \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /workspaces

# Ensure Gradle cache directory exists for the default VS Code user.
RUN mkdir -p /home/vscode/.gradle \
    && chown -R vscode:vscode /home/vscode/.gradle /workspaces

USER vscode

# Pre-warm the wrapper by verifying Java and Gradle compatibility.
CMD ["bash", "-lc", "java -version && ./gradlew --version"]
