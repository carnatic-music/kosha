# kosha

An open carnatic music database in the making.

## Why
Given how much carnatic music revolves around a (more or less) standard list of ragas, compositions, and composers, it is quite surprising that there isn't an authoritative source for this information on this internet today. The information however, is out there in the wild. Needles in the haystack of the internet.

This project exists to mine, categorize and curate that information into a strict database, thereby giving it the power of usability.

## Parts
There are scrapers (that mine for data from select websites), there is the relationship-maker (that connects data from different sources), and there is the database (adheres to schema, stores information, reports). Most of this codebase is in clojure, but there's no reason the scrapers can't be in another language.

## Usage

#### During Development
To run the API server:
`lein run -m kosha.main`
The server runs by default on port 3000. To specify a port, pass a port number using the -p option:
`lein run -m kosha.main -p 8080`

#### Deployment
To deploy, after changing the configuration in `resources/config.edn`, run `bin/deploy` with two enviroment variables `LEINPATH` and `SERVER_PORT` passed to it. For example, run the following in a terminal from the project directory:
```
LEINPATH=/home/deploy/lein SERVER_PORT=8080 bin/deploy
```
This starts the API server on port 8080 and logs to `kosha_server.log`.

## Installation

#### Prerequisites
You should have the following installed on your system:
1. [Clojure](https://clojure.org/guides/getting_started) (>= 1.7.0)
2. [Leiningen](https://leiningen.org/#install) (>= 2.7.0)
3. [Postgres](https://www.postgresql.org/download/) (>= 9.0)

#### Setup (from database dump)

Clone this repo and from the project directory, run:
```
> lein deps
> createdb kosha
> pg_restore -d kosha resources/db.dump
```
You can use a custom database name by configuring it in `resources/config.edn`.
#### Configuration
The _config map_ can be found in [`resources/config.edn`](resources/config.edn).
* __Database__: Configure the database access credentials by setting the values in the `:database` key of the _config map_.
* __Front-end__: To configure the CORS policy in order to allow the frontend to query the backend, set the URL of your frontend in the `:cors` key of the _config map_.
* __Logging Level__: Set the logging level to `:debug` OR `:error` in the `:logging` key of the _config map_.

## Documentation
1. [schema.org](doc/schema.org): Describes the schema of scraped data and the domain entities.
2. [stitching.org](doc/stitching.org): Describes the problem of stitching data from 3 different sources in Kosha, and ways to use the scoring functions.
3. [API.md](doc/API.md): Documents the API endpoints for Kosha.

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
