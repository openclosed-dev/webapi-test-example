FROM eclipse-temurin:25-jdk-resolute

ARG USERNAME
ARG USER_UID
ARG USER_GID=$USER_UID

RUN userdel -r ubuntu \
  && groupadd -g ${USER_GID} ${USERNAME} \
  && useradd -u ${USER_UID} -g ${USER_GID} -m -s /bin/bash ${USERNAME}

RUN apt-get update \
  && DEBIAN_FRONTEND=noninteractiv apt-get install -y --no-install-recommends \
    ca-certificates \
    curl \
    git \
    locales \
    openssh-client \
    postgresql-client-18 \
    unzip \
  && apt-get clean && rm -rf /var/lib/apt/lists/*

ARG GRADLE_VERSION

RUN curl -fsSLO "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" \
  && mkdir /opt/gradle \
  && unzip -d /opt/gradle gradle-${GRADLE_VERSION}-bin.zip

ENV PATH=$PATH:/opt/gradle/gradle-${GRADLE_VERSION}/bin

USER ${USERNAME}

RUN mkdir -p /home/${USERNAME}/.gradle/caches
