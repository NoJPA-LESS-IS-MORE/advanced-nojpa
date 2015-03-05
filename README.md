# NoJpa Spring integration

## Spring configurations

### Formatters

- Parser (Locator)
- Printer

### Json Serialization

- all `curl localhost:8080/people`
- a user `curl localhost:8080/people/Writer%20a`
- the books: `curl localhost:8080/people/Writer%20a/books` -> see the writer, not annotated reference
- assign an address `curl --data "street=Sesame" localhost:8080/people/Writer%20a/address`
- see the street (@JsonInclude) a user `curl localhost:8080/people/Writer%20a`
- assign a book to another author `curl --data "book=Title aa" localhost:8080/people/Writer%20a/books`


# NoJpa Internal data presentation

## MySQL tables

### very model much relationship

- 1..n with aux table - automatic or manually (weight of a reference?)
- 1..n with back reference
- n..n with manual aux table

### Table and field names

- shot in the foot with sql keywords

## Solr documents

- flat documents `curl http://localhost:8080/solr-debug`
- "schemaless" solr schema
- field name generation `curl http://localhost:8080/solr-log-fields`


# NoJpa in a cluster

## Invalidating others caches

- ObjectCacheRemote
- cluster configuration
- nojpas will communicate with each other directly

## `synchronized` across multiple jvms

- GlobalLockService - locking on a String representation of a model object or a random string
- GlobalLockService.LockedExecutor<T>
- `curl localhost:6061/cluster-lock/Writer%20a`
- `curl localhost:6062/cluster-lock/Writer%20a`