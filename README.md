# codeocean-scraper
Batch download student submission from codeocean

## Setup

- Clone project
- Install [gradle](http://gradle.org)
- Run `gradle shadowJar`
- Project is now build as a self-contained jar file in `build/libs`

## Running

Set the environment variable `CO_COOKIE` to your session key (see below) and execute with `java -jar path/to/jar targetDirectory statisticsPageUrl`.

Parameter explanation:
- *targetDirectory:* All downloaded submissions will be saved under this directory. The directory must already exist.
- *statisticsPageUrl:* Url to the statistics page of the exercise you want to download the submissions for.
- *session key:* Session key for authentication. Obtained by logging into CodeOcean in your browser, then copying the content of the `_hands-on-programming_session` cookie.

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
