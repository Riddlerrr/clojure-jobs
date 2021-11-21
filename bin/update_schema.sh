#!/bin/sh

pg_dump --schema-only --format=plain --no-owner --file=resources/schema.sql clojure_jobs_luminus_dev
pg_dump --data-only --table=schema_migrations --format=plain --no-comments clojure_jobs_luminus_dev >> resources/schema.sql
