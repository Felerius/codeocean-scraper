# codeocean-scraper
Batch download student submission from codeocean

## Setup

- Clone project
- Install [gradle](http://gradle.org)
- Run `gradle shadowJar`
- Project is now build as a self-contained jar file in `build/libs`

## Running

Execute `java -jar path/to/jar exerciseUrl targetDirectory deadline`

Parameter explanation:
- *exerciseUrl:* URL to the statistics page of the exercise you want to download.
  Should have the format `https://codeocean.url/exercises/<someId>/statistics`.
- *targetDirectory:* All downloaded submissions will be saved under this directory. The directory must already exist.
- *deadline:* The deadline of the exercise, if a submission was made after the deadline, a file called `DEADLINE_EXCEEDED` will be created in the output directory. Must be of the form `YYYY-MM-DDThh:mm+tz:tz` (in other words, an ISO 8601 date & time, as accepted by `java.time.OffsetDateTime.parse`).

### Authentication

The scraper uses session keys to authenticate with CodeOcean.
These are cached per CodeOcean installation in `~/.codeocean-scraper.json`.
If no cached session key exists or the cached one has been invalidated, you are prompted for an email and password to sign in.

## Result

After execution the target directory will contain a subdirectory for each user.

### Single file mode

If there was only one file associated with the exercises, these subdirectories can contain the following content:

- `name.java`: Last submitted version.
- `name - ASSESSED AFTER SUBMIT.java`: Version that was scored (but not submitted) after the last submitted version.
- `name - ASSESSED ONLY.java`: Last assessed version, if no version was ever submitted.
- `name - NO ASSESS OR SUBMIT.java`: No version was ever scored or submitted. Contains the last autosave.
- `name - TOP SCORE.java`: Version with the highest score. Only included if not matched by any of the other criteria. If multiple such versions exists, the last one is chosen.
- `error.txt`: Created if the server responded with an error when trying to download the submission page. Contains error details.

All `*.java` files will also contain an initial comment with the url to the submission page, and the score of the submission.

### Multi file mode

If more than one file are associated with the  exercise, the student directories may contain one or more subdirectories labeled `SUBMIT`, `ASSESSED AFTER SUBMIT`, `ASSESSED ONLY`, `NO ASSESS OR SUBMIT` or `TOP SCORE`. The meanings are the same as described above with `SUBMIT` being equivalent to no suffix (`name.java`). In this mode the metadata (url and score) will be placed in files called `url.txt` and `score.txt` withing the different subdirectories.
