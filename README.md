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

# NoJpa Internal data presentation

## MySQL tables

### relations
- 1..n with aux table - automatic or manually (weight of a reference?)
- 1..n with back reference
- n..n with manual aux table

### Table and field names
- shot in the foot with sql priv words !!!

## Solr documents
- flat documents
- "schemaless" solr schema
- field name generation


# NoJpa in a cluster

## Invalidating others caches

## `synchronized` across multiple jvms