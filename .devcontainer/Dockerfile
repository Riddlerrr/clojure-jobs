ARG VARIANT
FROM clojure:${VARIANT}

RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends \
    git \
    curl

# workaround to run clojure-lsp on apple m1
RUN curl -sL https://github.com/clojure-lsp/clojure-lsp/releases/download/2021.12.01-12.28.16/clojure-lsp -o /usr/local/bin/clojure-lsp
RUN chmod +x /usr/local/bin/clojure-lsp
